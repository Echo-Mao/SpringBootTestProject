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


$(function() {
  var protocol = window.location.protocol;
  var host = window.location.hostname;
  var port = window.location.port;
  
  var policyVue = new Vue({
    el: "#policyPage",
    created: function() {
      //1判断登录以及页面跳转并且拿登录用户Id？
      this.judgeLogin();
      //2查询第一页数据
      this.getProvinces();
      this.getPolicyStatus();
    },
    data: {
      /* 页码信息数据 */
      page: {
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        totalCount: 0
      },
      demand:{
    	  role:'',
    	  empName:''
      },
      /* 条件查询数据 */
      condition: {
    	  policyNum: '', //模糊查询保单编号
    	  carOwnerName: '', //投保人姓名
    	  brand: '', //车辆品牌型号
    	  empName: '', //跟单员姓名模糊查询
    	  empId:'',
    	  provinceId: -1, //省id
    	  cityId:-1,//市id
    	  insuranceAmount:'',//最低保费
    	  startDate:'',//生效时间
    	  endDate:'',//失效时间
    	  policyStatus:-1,//保单状态
    	  highPrice:'',//车辆最大价格
    	  lowPrice:''//车辆最低价格
      },
      
      /* 获取的保单集合 */
      dataList: [],
	  /* 省份、城市集合  用于选择省市 */
      provinceList:[],
      cityList:[],
      policyStatusList:[]
    },
    methods: {
      getPolicyList: function(pageIndex, pageSize) {
        var _this = this;
        _this.page.pageIndex = pageIndex;
        _this.page.pageSize = pageSize;
        _this.checkData = [];
        var params = {
          "page": _this.page.pageIndex,
          "size": _this.page.pageSize,
          "policyNum": _this.condition.policyNum,
          "carOwnerName": _this.condition.carOwnerName,
          "brand": _this.condition.brand,
    	  "empName": _this.condition.empName,
    	  "empId":_this.condition.empId,
    	  "provinceId": _this.condition.provinceId,
    	  "cityId":_this.condition.cityId,
    	  "insuranceAmount":_this.condition.insuranceAmount,
    	  "startDate":_this.condition.startDate,
    	  "endDate":_this.condition.endDate,
    	  "policyStatus":_this.condition.policyStatus,
    	  "highPrice":_this.condition.highPrice,
    	  "lowPrice":_this.condition.lowPrice,
        };
        this.$http.post(protocol+"//"+host+":"+port+"/policy/queryPolicy", params, {
          emulateJSON: true
        }).then(
          function(res) {
            _this.dataList = res.data.data.list; //返回的是RetResult
            _this.page.totalCount = res.data.data.total;
            _this.page.totalPage = res.data.data.pages;
          },
          function(res) {
            alert("您输入的参数有误！");
            console.log(res);
          });
      },
      judgeLogin:function(){
			var _this = this;
			this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
				function(res){
					this.loginEmp = res.data.loginEmp;
					if(this.loginEmp == null){//没有登录
						window.parent.location.href="/background/background-login.html";
					}else{
						this.demand.role = this.loginEmp.roleId;
						if(this.demand.role==2){
							this.condition.policyStatus = 3;
						}else if(this.demand.role==1){
							this.condition.empId = this.loginEmp.empId;
							this.condition.empName = this.loginEmp.empName;
						}else if(this.demand.role==3){
							this.condition.cityId = this.loginEmp.cityId;
							this.condition.provinceId= this.loginEmp.provinceId;
						}else  if(this.demand.role==4){
							this.condition.provinceId= this.loginEmp.provinceId;
							this.getCityByProvinceId();
						}
		                this.getPolicyList(1, this.page.pageSize);
					}
	            },
				function(res){
					alert("错了");
					console.log(res);
				});
	    	
		},
      toAuditPage: function(policyId){
        location.href = 'artificial-check.html?policyId='+ policyId;
      },
      toUploadFile: function(resourceId){
        location.href = 'upload-data.html?resourceId=' + resourceId;
      },
      importExcel:function(){
			var _this = this;
			location.href = protocol+"//"+host+":"+port+"/policy/exportPolicys?page=0&size=0"
		          +"&policyNum="+ _this.condition.policyNum
		          +"&carOwnerName="+ _this.condition.carOwnerName
		          +"&brand="+ _this.condition.brand
		    	  +"&empId="+ _this.condition.empId
		    	  +"&empName="+ _this.condition.empName
		    	  +"&provinceId="+ _this.condition.provinceId
		    	  +"&cityId="+_this.condition.cityId
		    	  +"&insuranceAmount="+_this.condition.insuranceAmount
		    	  +"&startDate="+_this.condition.startDate
		    	  +"&endDate="+_this.condition.endDate
		    	  +"&policyStatus="+_this.condition.policyStatus
		    	  +"&highPrice="+_this.condition.highPrice
		    	  +"&lowPrice="+_this.condition.lowPrice;
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
		getCityByProvinceId:function(){
			if (this.condition.provinceId<0) {
				this.cityList=[];
			}else{
				var params = {"provinceId":this.condition.provinceId};
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
		//查询所有保单状态
		getPolicyStatus:function(){
			var _this = this;
			this.$http.post(protocol+"//"+host+":"+port+"/policy/queryPolicyStatus").then(
					function(res){
						_this.policyStatusList = res.data.data
					},
					function(res){
						alert("系统正忙，请稍后！");
						console.log(res);
					});
		},/* 查看详细信息页面跳转传参  */
		goDetail:function(empId){
			location.href = "/background/view-employee-details.html?empId="+empId;
		}
    }
  });
});
