package com.nbui.policy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nbui.entity.CheckInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.policy.condition.PolicyBriefCondition;
import com.nbui.policy.condition.PolicyCondition;
import com.nbui.policy.entity.InsuranceTypeBrief;
import com.nbui.policy.entity.PolicyBrief;
import com.nbui.policy.entity.PolicyDetail;

/**
 * @author EchoMao
 * @date 2019年1月15日上午10:25:11
 * 
 */
public interface IPolicyDao {

    /**
     * >添加保单信息,在service层接口中改名,例:addPolicyInfo();
     * 
     * @param policyInfo
     * @return
     */
    int addPolicyInfo(PolicyInfo policyInfo);

    /**
     * >修改方法调用这个
     * 
     * @param userInfo
     * @return
     */
    int updatePolicyInfo(PolicyInfo policyInfo);

    /**
     * >删除方法,修改可查看状态为0
     * 
     * @param id
     * @return
     */
    int deletePolicyInfo(int id);

    /**
     * >查看所有保单信息
     */
    List<PolicyInfo> findAllPolicyInfo();

    /**
     * >根据保单id查找对象
     */
    PolicyInfo findPolicyInfoById(int id);

    /**
     * >根据用户id查看保单
     * 
     * @param id
     * @return
     */
    List<PolicyInfo> findAllPolicyByUserId(int id);
    /**
     * >根据用户id查看订单的简要多表信息
     * @return
     */
	List<PolicyBrief> listPolicyBriefById(PolicyBriefCondition policyBriefCondition);
	 /**
     * >根据用户id查看保单的简要多表信息
     * @return
     */
	List<PolicyBrief> listOrderBriefById(PolicyBriefCondition policyBriefCondition);
	/**
	 * 根据保单号删除订单
	 * @param policyNumber
	 */
	void deleteBypolicyNumber(String policyNumber);
	/**
	 * 根据保单号查询保单基本信息
	 * @param policyNumber
	 * @return
	 */
	PolicyDetail getPolicyBase(String policyNumber);
	/**
	 * 根据保单号查询投保人的基本信息
	 * @param policyNumber
	 * @return
	 */
	PolicyDetail getPolicyApplicant(String policyNumber);
	/**
	 * 根据保单号查询被保人的基本信息
	 * @param policyNumber
	 * @return
	 */
	PolicyDetail getPolicyInsuredPerson(String policyNumber);
	/**
	 * 根据保单号查询车主的基本信息
	 * @param policyNumber
	 * @return
	 */
	PolicyDetail getPolicyCarOwner(String policyNumber);
	/**
	 * 根据保单号查询车险信息
	 * @param policyNumber
	 * @return
	 */
	List<InsuranceTypeBrief> getPolicyInsuranceList(String policyNumber);
    // TODO 分页查询
    
    /**
     * 	后台查询保单
     */
    List<PolicyInfo> queryPolicyByEmp(PolicyCondition condition);
    
    /**
     * 
     * 	后台查询审核信息
     */
    CheckInfo queryAuditData(PolicyCondition condition);
    
    /**
     * 	后台审核
     */
    Integer audit(PolicyCondition condition);
    
    /**
     * 	更新上传资料
     */
    Integer uploadResource(CheckInfo checkInfo);
    /**
     * 修改保单的状态
     * @param policyNumber
     */
	void changePolicyStatuSid(String policyNumber);
	
	/**
	 * 插入一条待审核记录到审核表
	 * @param policyId
	 */
	void insertCheckInfo(@Param("policyId")Integer policyId);

	/**
	 * 根据保单号查询保单ID
	 * @param policyNumber
	 * @return
	 */
	Integer getPolicyIdByPolicyNumber(@Param("policyNumber")String policyNumber);

	/**
     * 修改保单的状态
     * @param policyNumber
     */
	void changePolicyStatus(@Param("policyId")Integer policyId);
}
