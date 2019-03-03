

$(function(){

	  var protocol = window.location.protocol;
	  var host = window.location.hostname;
	  var port = window.location.port;
	
	var empId = $.query.get("empId");
	if (empId=="" || empId==null || empId==undefined) {
		empId = $.session.get("empId"); 
		if (empId=="" || empId==null || empId==undefined) {
			window.parent.location.href="/background/background-login.html";
		}
	}
	var regEmpId = /^[0-9]*$/;
	if (!regEmpId.test(empId)) {
		alert("有黑客攻击，请稍后再试！以防信息泄露！")
		location.href="/background/view-employee-information.html";
	}
	
	var vueEmp= new Vue({
		el:"#base",
		data:{
			detailEmpId:"",
			detailEmp:{
				empId:"",
				empName:"",
				birthday:"",
				gender:"",
				phoneNum:"",
				preperNum:"",
				province:{
					provinceId:-1,
					province:""
				},
				city:{
					cityId:-1,
					city:""
				},
				role:{
					roleId:"",
					roleName:""
				},
				incumbency:-1
			}
		},
		created:function(){
			//用empId查询详细信息
			this.judgeLogin();
			
		},
		methods:{
			getEmpDetail:function(){
				var _this = this;
				_this.detailEmpId = empId;
				/* 测试用的，尝试使用vue-router解决页面传参问题   var _this.detailEmpId = _this.$route.query.empId;*/
				var params = {"empId":+_this.detailEmpId};
				_this.$http.post(protocol+"//"+host+":"+port+"/emp/findByEmpId.action",params,{emulateJSON:true}).then(
						function(res){
							var findStatus = res.data.findStatus;
							if (findStatus=="success") {
								_this.detailEmp = res.data.empInfo;
							}else{
								alert("小主，很遗憾！查询失败了，请稍后刷新再查！");
							}
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
				
			},
			judgeLogin:function(){
				this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
					function(res){
						var loginEmp = res.data.loginEmp;
						if(loginEmp == null){//没有登录
							window.parent.location.href="/background/background-login.html";
							
						}else{
							this.getEmpDetail();
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
	
	