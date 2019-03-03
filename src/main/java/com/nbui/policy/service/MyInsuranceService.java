package com.nbui.policy.service;

import java.util.List;

import com.nbui.policy.condition.PolicyBriefCondition;
import com.nbui.policy.entity.PolicyBrief;
import com.nbui.policy.entity.PolicyDetail;

public interface MyInsuranceService {
	/**
	 * 查询保单信息简版
	 * @param policyBriefCondition
	 * @return
	 */
	List<PolicyBrief> listPolicyBriefById(PolicyBriefCondition policyBriefCondition);
	/**
	 * 查询订单信息简版
	 * @param policyBriefCondition
	 * @return
	 */
	List<PolicyBrief> listOrderBriefById(PolicyBriefCondition policyBriefCondition);
	/**
	 * 更具订单号删除订单
	 * @param policyNumber
	 */	
	void deleteBypolicyNumber(String policyNumber);
	/**
	 * 根据保单号查询保单详细信息
	 * @param policyNumber
	 * @return
	 */
	PolicyDetail showPolicyDetail(String policyNumber);
	/**
	 * 根据已经支付的保单号将保单号的状态修改为待审核状态
	 * @param policyNumber
	 */
	void changePolicyStatuSid(String policyNumber);
	
	/**
	 * 保单支付完成变成待审核状态时生成审核记录，在审核表添加一条记录
	 * @param policyNumber
	 */
	void addCheckInfo(Integer policyId);
	
	/**
	 * 根据保单编号查保单id
	 * @param policyNumber
	 * @return
	 */
	Integer getPolicyIdByPolicyNumber(String policyNumber);
}
