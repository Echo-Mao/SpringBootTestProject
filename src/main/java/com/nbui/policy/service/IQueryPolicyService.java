package com.nbui.policy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.CheckInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyStatus;
import com.nbui.policy.condition.PolicyCondition;

public interface IQueryPolicyService {

	PageInfo<PolicyInfo> queryPolicyByEmp(PolicyCondition condition, Integer page, Integer size);

	CheckInfo queryAuditData(PolicyCondition condition);
	
	Integer auditResource(PolicyCondition condition);
	
	void exportPocliy(HttpServletResponse response,PolicyCondition condition);
	
	Integer saveUploadFile(CheckInfo checkInfo);
	
	List<PolicyStatus> queryPolicyStatus();
}
