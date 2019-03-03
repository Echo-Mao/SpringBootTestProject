
$(function(){
	var protocol = window.location.protocol;
	  var host = window.location.hostname;
	  var port = window.location.port;
	
	
	var vueEmp= new Vue({
		el:"#tttt",
		data:{
			personEmp:{
				empId:"",
				empName:"",
				birthday:"",
				gender:"",
				phoneNum:"",
				email:"",
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
			},
			updateEmp:{
				empId:"",
				birthday:"",
				gender:"",
				phoneNum:"",
				email:""
			},
			errorMsg:""
		},
		created:function(){
			//1先去后台获取的登录员工的Id号,若是没有则跳到登录页面
			//this.judgeLogin()
				//用empId查询详细信息
			this.getPersonEmp();
			
		},
		methods:{
			 /* 先查询个人信息  用于进来时个人信息的显示  */
			getPersonEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.personEmp = res.data.loginEmp;
							if(_this.personEmp == null){//没有登录
								window.parent.location.href="/background/background-login.html";
							}
						},
						function(res){
							alert("系统正忙，请稍后！");
							console.log(res);
						});
				
			},
			
			/* 要更新之前先将数据进行传送   注意只能一一赋值，使用对象赋值会使得是同一对象 一起变化 */
			toUpdate:function(){
				var _this = this;
				_this.updateEmp.birthday = _this.personEmp.birthday;
				_this.updateEmp.gender = _this.personEmp.gender;
				_this.updateEmp.phoneNum = _this.personEmp.phoneNum;
				_this.updateEmp.email = _this.personEmp.email;
			},
			
			/* 填写好更新信息之后进行更新 修改 */
			updatePerson:function(){
				var _this = this;
				var testFlag = _this.testMsg();
				if (testFlag) {
					_this.updateEmp.empId = _this.personEmp.empId;
					var params={
							"empInfo":JSON.stringify(_this.updateEmp)
							};
					_this.$http.post(protocol+"//"+host+":"+port+"/emp/updateEmpByEmpId.action",params,{emulateJSON:true}).then(
							function(res){
								var updateStatus = res.data.updateStatus;
								if (updateStatus=="success") {
									alert("修改成功！");
									location.href="/background/personal-center.html";
								}else{
									alert("小主，很遗憾！您的信息修改失败了！");
								}
							},
							function(res){
								alert("系统正忙，请稍后！");
								console.log(res);
							});
				}
				
			},
			testMsg:function(){
				var _this = this;
				var regPhoneNum = /^1[34578]\d{9}$/;
				var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				if (_this.updateEmp.birthday=="") {
					_this.errorMsg = "请输入生日";
					return false;
				}
				if (_this.updateEmp.gender=="") {
					_this.errorMsg = "请选择性别";
					return false;
				}
				if (_this.updateEmp.email=="") {
					_this.errorMsg = "请输入邮箱";
					return false;
				}else if (!regEmail.test(_this.updateEmp.email)) {
					_this.errorMsg = "邮箱格式不符合要求";
					return false;
				}
				if (_this.updateEmp.phoneNum=="") {
					_this.errorMsg = "请输入手机号";
					return false;
				}else if (!regPhoneNum.test(_this.updateEmp.phoneNum)) {
					_this.errorMsg = "手机号不符合要求";
					return false;
				}
				_this.errorMsg = "";
				return true;
			}
		}
	});
	
});	
	
	