$(function(){
	var protocol = window.location.protocol;
	  var host = window.location.hostname;
	  var port = window.location.port;
	  var vueEmp= new Vue({
		  
		el:"#base",
		data:{
			provinceList:[],
			cityList:[],
			roleList:[],
/*  后台拿的登录员工的Id	*/		loginEmpId:24,
			/* 用于添加的员工信息 */
			empMsgToAdd:{ 
				empName:"",
				gender:"",
				birthday:"",
				phoneNum:"",
				email:"",
				paperNum:"",
				provinceId:"-1",//所在地区的省份
				cityId:"-1",//所在地区的城市
				roleId:"-1"  //职务
			},
			errorMsg:""
		},
		created:function(){
			//1判断登录以及页面跳转并且拿登录用户Id
			this.judgeLogin();
				
			
			
		},
		methods:{
			addEmp:function(){
				var _this = this;
				var addFlag = _this.testMst();
				if (addFlag) {
					/*  这里需要将要添加的项在data中定义，并从中获取	*/		
					var params={
							"empInfo":JSON.stringify(_this.empMsgToAdd)
							};
					this.$http.post(protocol+"//"+host+":"+port+"/emp/addEmp.action",params,{emulateJSON:true}).then(
							function(res){
								var addStatus = res.data.addStatus;//返回的是map对象，名字为addStatus
								if (addStatus=="success") {
									alert("小主，恭喜您！注册员工成功！");
									_this.goEmpList();
								}else{
									alert("小主，很遗憾！注册失败了，请检查是否重复注册");
								}
							},
							function(res){
								alert("系统正忙，请稍后！");
								console.log(res);
							});
					
				}else{
					return ;
				}
			},
			getProvinces:function(){
				var _this = this;
				this.$http.post(protocol+"//"+host+":"+port+"/privince/findAllProvince.action").then(
						function(res){
							_this.provinceList = res.data.provinceList;//返回的是map对象，名字为provinceList
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
				});
				
			},
			getCityByProvinceId:function(){
				var _this = this;
				var params = {"provinceId":_this.empMsgToAdd.provinceId};
				_this.$http.post(protocol+"//"+host+":"+port+"/city/findAllCityByProinceId.action",params,{emulateJSON:true}).then(
						function(res){
							_this.cityList = res.data.cityList;//返回的是map对象，名字为cityList
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
			},
			/* 获取角色选项 */
			getRoles:function(){
				var _this = this;
				var params = {"loginEmpId":_this.loginEmpId};
				_this.$http.post(protocol+"//"+host+":"+port+"/role/findAllRoleByEmpId.action",params,{emulateJSON:true}).then(
						function(res){
							_this.roleList = res.data.roleList;//返回的是map对象，名字为roleList
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
			},
			/* 验证填写的添加信息 */
			testMst:function(){
				var _this = this;
				var regEmpName = /^[a-zA-Z]{4,40}|[\u2E80-\u9FFF]{2,20}$/;
				var regPhoneNum = /^1[34578]\d{9}$/;
				var regPaperNum = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
				var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				if (_this.empMsgToAdd.empName=="") {
					_this.errorMsg = "请输入员工姓名";
					return false;
				}else if (!regEmpName.test(_this.empMsgToAdd.empName)) {
					_this.errorMsg = "员工姓名为4-40个英文字符或2-20个中文字符";
					return false;
				}
				if (_this.empMsgToAdd.gender=="") {
					_this.errorMsg = "请选择性别";
					return false;
				}
				if (_this.empMsgToAdd.birthday=="") {
					_this.errorMsg = "请输入生日";
					return false;
				}
				if (_this.empMsgToAdd.phoneNum=="") {
					_this.errorMsg = "请输入手机号";
					return false;
				}else if (!regPhoneNum.test(_this.empMsgToAdd.phoneNum)) {
					_this.errorMsg = "手机号不符合要求";
					return false;
				}
				if (_this.empMsgToAdd.email=="") {
					_this.errorMsg = "请输入邮箱";
					return false;
				}else if (!regEmail.test(_this.empMsgToAdd.email)) {
					_this.errorMsg = "邮箱格式不符合要求";
					return false;
				}
				if (_this.empMsgToAdd.paperNum=="") {
					_this.errorMsg = "请输入身份证号";
					return false;
				}else if (!regPaperNum.test(_this.empMsgToAdd.paperNum)) {
					_this.errorMsg = "身份证号不符合要求";
					return false;
				}
				if (_this.empMsgToAdd.provinceId<0) {
					_this.errorMsg = "请选择所在地区";
					return false;
				}
				if (_this.empMsgToAdd.provinceId<0) {
					_this.errorMsg = "请选择所在地区";
					return false;
				}
				if (_this.empMsgToAdd.roleId<0) {
					_this.errorMsg = "请选择职位";
					return false;
				}
				
				_this.errorMsg = "";
				return true;
			},
			goEmpList:function(){
				location.href="/background/view-employee-information.html";
				
			},
			judgeLogin:function(){
				this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
					function(res){
						var loginEmp = res.data.loginEmp;
						if(loginEmp == null){//没有登录
							window.parent.location.href="/background/background-login.html";
							
						}else{
							//2查询省份，用于是省份的选择
							this.getProvinces();
							//3查角色getRoles
							this.getRoles();
						}
						
		            },
					function(res){
						alert("错了");
						console.log(res);
					});
		    	
			}
			
		}
		
	});

});	
	
	