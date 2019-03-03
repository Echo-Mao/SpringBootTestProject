package com.nbui.policy.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbui.policy.condition.PolicyBriefCondition;
import com.nbui.policy.entity.PolicyBrief;
import com.nbui.policy.entity.PolicyDetail;
import com.nbui.policy.service.MyInsuranceService;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/myInsurance")
public class MyInsuranceController {
	
@Autowired
private MyInsuranceService  MyInsuranceService;


@RequestMapping("/showPolicyList.action")
private HashMap<Object,Object> ReturnAll(PolicyBriefCondition policyBriefCondition) {
	System.out.println(policyBriefCondition.getId()+".."+policyBriefCondition.getPolicystatus1()+".."+policyBriefCondition.getPolicystatus2()+".."
+policyBriefCondition.getPolicyCreateDate()+".."+policyBriefCondition.getSearch1()+".."+policyBriefCondition.getSearch2());
	List<PolicyBrief> listPolicyBrief=MyInsuranceService.listPolicyBriefById(policyBriefCondition);
	List<PolicyBrief> listOrderBrief=MyInsuranceService.listOrderBriefById(policyBriefCondition);
	System.out.println(listPolicyBrief+"..."+listOrderBrief);
	HashMap<Object, Object> hashMap = new HashMap<Object,Object>();
	hashMap.put("list1", listOrderBrief);//显示订单
	hashMap.put("list2", listPolicyBrief);//显示保单
	return hashMap;
}
@RequestMapping("/deletePolicyBypolicyNumber.action")
private String deletePolicyBypolicyNumber(String policyNumber) {
	System.out.println("要删除的订单号为1:"+policyNumber);
	MyInsuranceService.deleteBypolicyNumber(policyNumber);
	return null;
}
@RequestMapping("/deletePolicyBypolicyNumbers.action")
private String deletePolicyBypolicyNumbers(String policyNumbers) {
	System.out.println("要删除的订单号为2:"+policyNumbers);
	JSONArray jArray= JSONArray.fromObject(policyNumbers);
	for (Object policyNumber : jArray) {
		MyInsuranceService.deleteBypolicyNumber((String)policyNumber);
	}
	return null;
}
@RequestMapping("/showPolicyDetail.action")
private PolicyDetail showPolicyDetail(String policyNumber) {
	System.out.println("要删除的保单号为3:"+policyNumber);
	PolicyDetail PolicyDetail=MyInsuranceService.showPolicyDetail(policyNumber);
	return PolicyDetail;
}


}
