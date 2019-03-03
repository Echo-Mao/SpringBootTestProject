/*
 *资料上传页面
 *
 */

$(function() {
	var protocol = window.location.protocol;
	var host = window.location.hostname;
	var port = window.location.port;
	var policyId = $.query.get("policyId");
	var uploadVue = new Vue(
			{
				el : "#uploadResource",
				data : {
					policyId:policyId
				}
			});
});
