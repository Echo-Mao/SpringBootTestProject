package com.nbui.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nbui.entity.UserInfo;
import com.nbui.policy.entity.PolicyDetail;
import com.nbui.policy.entity.UserInfoBrief;
import com.nbui.policy.service.MyImformationService;



@RestController
@RequestMapping("/myImformation")
public class MyInformationController {

@Autowired
private MyImformationService MyImformationService;

	@RequestMapping("/showUserByUserId.action")
	private UserInfoBrief showPolicyDetail(Integer loginId) {
		System.out.println("loginId:"+loginId);
		UserInfoBrief UserInfoBrief=MyImformationService.showUserByLoginId(loginId);
		System.out.println(UserInfoBrief);
		return UserInfoBrief;
	}
	
	@RequestMapping("/UpdateUserInfo.action")
	private PolicyDetail showPolicyDetail(String UserInfo) {
		System.out.println("UserInfo:"+UserInfo);
		//PolicyDetail PolicyDetail=MyInsuranceService.showPolicyDetail(policyNumber);
			UserInfo userInfo = JSONObject.parseObject(UserInfo,UserInfo.class);
			MyImformationService.UpdateUserInfo(userInfo);
		return null;
	}
}
