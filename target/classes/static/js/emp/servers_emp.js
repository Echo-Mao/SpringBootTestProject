/*
 * 后台需要编写的内容：
 * 1judgeLogin判断是否登录，到请求中的cookie中获取登录empId值，没有则返回失败状态。
 * 2getEmpList查询数据
 * 
 * 
 *前台页面的流程和跳转
 *1页面加载以及实例创建完成，去后台判断有没有登录，没登录就跳转到登录页面
 *2到后台去查询第一页的数据，
 *
 *
 *
*/
$(function(){
	var protocol = window.location.protocol;
	var host = window.location.hostname;
	var port = window.location.port;	

var vueEmp= new Vue({
	el:"#base",
	created:function(){
		//1判断登录以及页面跳转并且拿登录用户Id
		//2查询第一页数据
		this.judgeLogin();
		
	},
	data:commonData,
	watch: { // 监视双向绑定的数据数组
   		checkData: {
   				handler(){ // 数据数组有变化将触发此函数
   					this.pageChange();
   					if(this.checkData.length == this.allLength && this.allLength!=0){
   						document.querySelector('#allId').checked = true;//注意将全选框id修改
   					}else {
   						document.querySelector('#allId').checked = false;
   					}
   				},
   				deep: true // 深度监视
   			}	
   		},
	methods:{
		getEmpList:function(pageIndex,pageSize){  //获取报价按键按下触发方法，将填写信息验证以及保存到后台
			var _this = this;
			_this.page.pageIndex = pageIndex;
			_this.page.pageSize = pageSize;
			_this.checkData=[];
			
			
			/* 测试输入是否合法  不合法的数据直接查不到 不再往后台查询  避免数据转换错误   */
			var flag = this.oprationTest();
			if (flag) {
				var params={"pageIndex":_this.page.pageIndex,"pageSize":_this.page.pageSize,
						"empCondition":JSON.stringify(_this.demand)};
				this.$http.post(protocol+"//"+host+":"+port+"/emp/findAllEmpAndPage.action",params,{emulateJSON:true}).then(
					function(res){
						 _this.dataList = res.data.data.list; //返回的是RetResult
						 _this.page.totalCount = res.data.data.total; 
						 _this.page.totalPage = res.data.data.pages; 
						 _this.flag=2;
					},
					function(res){
						alert("错了");
						console.log(res);
					});
			}else{
				_this.dataList=[];
			}
			
		},
		judgeLogin:function(){
			this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
				function(res){
					this.loginEmp = res.data.loginEmp;
					if(this.loginEmp == null){//没有登录
						window.parent.location.href="/background/background-login.html";
					}else{
						this.demand.loginEmpId = this.loginEmp.empId;
						//注意：这里由于使用两个ajax会异步进行，所以要使其有先后顺序，在这里调用
						this.getEmpList(1,this.page.pageSize);
						this.getProvinces();
						this.getRoles();
					}
	            },
				function(res){
					alert("错了");
					console.log(res);
				});
	    	
		},
		getProvinces:function(){
			var _this = this;
			this.$http.post(protocol+"//"+host+":"+port+"/privince/findAllProvince.action").then(
					function(res){
						_this.provinceList = res.data.provinceList//返回的是map对象，名字为provinceList
					},
					function(res){
						alert("系统正忙，请稍后！");
						console.log(res);
					});
		},
		deleteOne:function(deleteId){
			var delFlag  = confirm("小主，您确定要删除该员工纪录吗？");
			if (delFlag) {
				var params={"deleteId":deleteId};
				this.$http.post(protocol+"//"+host+":"+port+"/emp/deleteOneEmp.action",params,{emulateJSON:true}).then(
						function(res){
							var deleteStatus =res.data.deleteStatus;
							if (deleteStatus=="success") {
								alert("小主，恭喜您删除成功了");
								//this.getEmpList(1,this.page.pageSize);
								history.go(0);
							}else{
								alert("小主，很遗憾，您删除失败了");
							}
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
			}
			
		},
		deleteBatch:function(){
			var _this = this;
			var delFlag  = confirm("小主，您确定要删除选中员工纪录吗？");
			if (delFlag) {
				if (_this.checkData.length>0) {
					var params = {"empIdArrStr":JSON.stringify(_this.checkData)};
					this.$http.post(protocol+"//"+host+":"+port+"/emp/deleteBatchEmp.action",params,{emulateJSON:true}).then(
							function(res){
								var deleteStatus =res.data.deleteStatus;
								if (deleteStatus=="success") {
									alert("小主，恭喜您删除成功了");
									history.go(0);
									/*this.getEmpList(1,this.page.pageSize);*/
								}else{
									alert("小主，很遗憾，您删除失败了");
								}
							},
							function(res){
								alert("系统正忙，请稍后！");
								console.log(res);
							});
				}else{
					alert("小主，请选中要删除的记录");
				}
			}
		},
/*	点击下载报表，将员工信息全部查出，不分页*/  
		loadTable:function(){
			var _this = this;
			location.href = "/emp/empExcelDownload.action?empName="+this.demand.empName+"&loginId="+this.demand.loginId+
					"&age="+_this.demand.age+
					"&provinceId="+_this.demand.provinceId+"&cityId="+_this.demand.cityId+
					"&phoneNum="+_this.demand.phoneNum+"&roleId="+_this.demand.roleId+
					"&incumbency="+_this.demand.incumbency+"&loginEmpId="+_this.demand.loginEmpId;	
		},
		
		queryEmpByEmpId:function(empId){
			var _this = this;
/*   触发模态框之前将数据先查询再跳出模态框显示查出的数据，模态框填写后再查询数据		*/
			_this.updateRole.empId = empId;
			var params = {"empId":empId};
			this.$http.post(protocol+"//"+host+":"+port+"/emp/findByEmpId.action",params,{emulateJSON:true}).then(
					function(res){
						var findStatus = res.data.findStatus;
						if (findStatus=="success") {
							_this.updateRole = res.data.empInfo;//返回的是map对象，名字为addStatus
						}else{
							alert("小主，很遗憾！查询失败了，请稍后刷新再查！");
							location.href="/background/view-employee-information.html";
						}
					},
					function(res){
						alert("系统正忙，请稍后！");
						console.log(res);
					});
			
		},
		getCityByProvinceId:function(){
			this.demand.cityId=-1; /*  省变化，是归位    */
			if (this.demand.provinceId<0) {
				this.cityList=[];
			}else{
				var params = {"provinceId":this.demand.provinceId};
				this.$http.post(protocol+"//"+host+":"+port+"/city/findAllCityByProinceId.action",params,{emulateJSON:true}).then(
						function(res){
							this.cityList = res.data.cityList;//返回的是map对象，名字为cityList
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
			}
			
		},
		getRoles:function(){
			var params = {"loginEmpId":this.demand.loginEmpId};
			this.$http.post(protocol+"//"+host+":"+port+"/role/findAllRoleByEmpId.action",params,{emulateJSON:true}).then(
					function(res){
						var findStatus = res.data.findStatus;
						if (findStatus=="fail") {
							alert("小主，很遗憾！查询失败了，请稍后刷新再查！");
							location.href="/background/view-employee-information.html";
						}else{
							this.roleList = res.data.roleList;//返回的是map对象，名字为roleList
						}
						
					},
					function(res){
						alert("系统正忙，请稍后！");
						console.log(res);
					});
		},
		 /* 全选的方法  */
       	checkAll:function(e){ // 点击全选事件函数
			var checkObj = document.querySelectorAll('.signalClass'); // 获取所有checkbox项
			this.allLength = checkObj.length;
			
			if(e.target.checked){ // 判定全选checkbox的勾选状态
				for(var i=0;i<checkObj.length;i++){
					if(!checkObj[i].checked){ // 将未勾选的checkbox选项push到绑定数组中
						this.checkData.push(checkObj[i].value);
					}
				}
			}else { // 如果是去掉全选则清空checkbox选项绑定数组
				this.checkData = [];
			}
		},
		/* 重新获取当前页的总数  监控需要用到 */
		pageChange:function(){
			var checkObj = document.querySelectorAll('.signalClass'); // 获取所有checkbox项
			this.allLength = checkObj.length;
		},
		/* 修改权限 */
		updateRoleId:function(empId){
			var _this = this;
			var params = {"empId":empId,"roleId":_this.updateRole.roleId};
			this.$http.post(protocol+"//"+host+":"+port+"/emp/updateRoleId.action",params,{emulateJSON:true}).then(
					function(res){
						var updateStatus = res.data.updateStatus;
						if (updateStatus=="success") {
							_this.getEmpList(1,_this.page.pageSize);
						}else{
							alert("小主，很遗憾！修改权限失败了，请刷新页面重试");
						}
					},
					function(res){
						alert("系统正忙，请稍后！");
						console.log(res);
					});
			
		},
		
		/* 查看详细信息页面跳转传参  */
		goDetail:function(empId){
			location.href = "/background/view-employee-details.html?empId="+empId;
		},
		
		
		
		 /* 未完待续。。。。。。。。。。。。。。。。。。。 */
		oprationTest:function(){
			var _this = this;
			var regLoginId = /^[0-9]{1,6}$/;
			var regAge = /^[0-9]{1,3}$/;
			var regPhoneNum = /^[0-9]{1,11}$/;
			if (_this.demand.loginId!="" && !regLoginId.test(_this.demand.loginId)) {
				return false;
			}
			if (_this.demand.age!="" && !regAge.test(_this.demand.age)) {
				return false;
			}
			if (_this.demand.phoneNum!="" && !regPhoneNum.test(_this.demand.phoneNum)) {
				return false;
			}
			return true;
		}
		
	}
	
});

});

