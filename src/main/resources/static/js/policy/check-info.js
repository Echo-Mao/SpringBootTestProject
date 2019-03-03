$(function() {
	var protocol = window.location.protocol;
	var host = window.location.hostname;
	var port = window.location.port;
	var policyId = $.query.get("policyId");
	var checkVue = new Vue({
		el : "#checkPage",
		created : function() {
			this.getAuditData();
		},
		data : {
			policyId : policyId,
			/* 获取的保单集合 */
			dataList : []
		},
		methods : {
			getAuditData : function() {
				var _this = this;
				var params = {
					"policyId" : _this.policyId
				};
				_this.$http.post(
						protocol + "//" + host + ":" + port
								+ "/policy/queryAuditData", params, {
							emulateJSON : true
						}).then(function(res) {
					_this.dataList = res.data.data;
				}, function(res) {
					alert("您输入的参数有误！");
					console.log(res);
				});
			},
			audit : function(status) {
				var _this = this;
				var flag;
				if (status == 4) {
					flag = confirm("您确定要通过吗？");
				} else {
					flag = confirm("您确定不通过吗？");
				}
				if (flag) {
					var params = {
						"policyId" : _this.policyId,
						"policyStatus" : status
					};
					_this.$http.post(
							protocol + "//" + host + ":" + port
									+ "/policy/auditResource", params, {
								emulateJSON : true
							}).then(function(res) {
						_this.dataList = res.data.data;
						location.href = "/background/view-policy.html";
					});
				}

			},
			lookResource: function(resName){
				window.open(protocol + "//" + host + ":" + port+"//"+resName);
			}
		}
	});
});
