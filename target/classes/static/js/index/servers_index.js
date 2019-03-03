	

function createURL(url){
		return "<iframe name='sonIframe' src='"+url+"' style='border:0px;width:100%;height:95%;'></iframe>";
	}
	function fun(){
		$('#aaa').modal({show:true,backdrop:'static'});
	}
	function existFun(){
        $.ajax({
			url : "/empEixt.action",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			type : "get",
			success : function(rs) {
				alert("小主！您退出了七武海后台管理系统！");
			}
		});
	}
	

$(function(){
		var protocol = window.location.protocol;
		var host = window.location.hostname;
		var port = window.location.port;
		
	$(function(){
		$(".btn").click(function(){
			
			var textContent = this.innerHTML;  //获得超链接中的文本内容
			//if($(this).attr("linkUrl"))
				//var url = $(this).attr("linkUrl");  //获得请求路径
			var url = this.href;
			//alert(url);
			//var url = this.href;
			//获取index关闭
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$('#tt').tabs('close',index);
			
			var flag = $("#tt").tabs("exists",textContent);
			if(flag){
				$('#tt').tabs("select",textContent);
			}else{
				// add a new tab panel    
				$('#tt').tabs('add',{    
				    title:textContent,    
				    selected:true,
				    content:createURL(url),    //页面内容，可以是已经写好的页面的路径
				    closable:true,    
				    tools:[{    
				        iconCls:'icon-mini-refresh',    
				        handler:function(){    
				            alert('refresh');    
				        }
				    }]    
				}); 
			} 
			return false;
		});
		
		
	});
	
		
		
	
	
	
	var vueIndex= new Vue({
		el:".base",
		data:{
			loginEmp:{
				empId:"",
				empName:"",
				birthday:"",
				gender:"",
				phoneNum:"",
				email:"",
				preperNum:"",
				provinceId:-1,
				province:{
					provinceId:-1,
					province:""
				},
				cityId:-1,
				city:{
					cityId:-1,
					city:""
				},
				roleId:"",
				role:{
					roleId:"",
					roleName:""
				},
				incumbency:-1
			},
			styleShow:{
				'display':'block'
			},
			styleHidden:{
				'display':'none'
			}
		},
		created:function(){
			//1先去后台获取的登录员工信息,,若是没有则跳到登录页面
			this.getLoginEmp();
		},
		methods:{
			//  先查询登录信息  用于进来进入首页的信息显示   
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
							if(_this.loginEmp == null){//没有登录
								location.href="/background/background-login.html";
							}
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
				
			},
			exitMethod:function(){
				var _this = this;
				var flag = confirm(_this.loginEmp.empName+",您确定要退出七武海后台管理系统吗？ ");
				if (flag) {
					_this.$http.post(protocol+"//"+host+":"+port+"/empEixt.action").then(
							function(res){
								location.href="/background/background-login.html";
				            },
							function(res){
								alert("退出失败！请稍后再试！");
								console.log(res);
							});
				}
				
			}
		}
	});
	
	/* 保单管理 */
	var policyManager = new Vue({
		el:"#policyManager",
		data:{
			roleId:-1,
			loginEmp:{}
		},
		created:function(){
			this.getLoginEmp();
		},
		methods:{
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
			}
		}
	});
	
	/* 员工管理 */
	var empManager = new Vue({
		el:"#empManager",
		data:{
			loginEmp:{}
		},
		created:function(){
			this.getLoginEmp();
		},
		methods:{
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
			}
		}
	});
	  
	
	
	/* 客户信息管理  */
	var customerManager = new Vue({
		el:"#customerManager",
		data:{
			loginEmp:{}
		},
		created:function(){
			this.getLoginEmp();
		},
		methods:{
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
			}
		}
	});
	
	
	
	/* 险种信息管理  */
	var insuranceManager = new Vue({
		el:"#insuranceManager",
		data:{
			loginEmp:{}
		},
		created:function(){
			this.getLoginEmp();
		},
		methods:{
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
			}
		}
	});
	
	
	
	/* 报价信息管理  */
	var priceManager = new Vue({
		el:"#priceManager",
		data:{
			loginEmp:{}
		},
		created:function(){
			this.getLoginEmp();
		},
		methods:{
			getLoginEmp:function(){
				var _this = this;
				_this.$http.post(protocol+"//"+host+":"+port+"/getLoginEmp.action").then(
						function(res){
							_this.loginEmp = res.data.loginEmp;
			            },
						function(res){
							alert("错了");
							console.log(res);
						});
			}
		}
	});
	
	
});	
	
	









