package com.nbui.policy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.policy.entity.InsuranceTypeBrief;
import com.nbui.policy.entity.PolicyBrief;
import com.nbui.policy.entity.PolicyDetail;
import com.nbui.policy.service.MyInsuranceService;
import com.nbui.policy.condition.PolicyBriefCondition;
import com.nbui.policy.dao.*;
@Service
public class MyInsuranceServiceImpl implements MyInsuranceService {

@Autowired
private IPolicyDao IPolicyDao;
	
	@Override
	public List<PolicyBrief> listPolicyBriefById(PolicyBriefCondition policyBriefCondition) {
		List<PolicyBrief> ListPolicyBrief= IPolicyDao.listPolicyBriefById(policyBriefCondition);
		return ListPolicyBrief;
	}

	@Override
	public List<PolicyBrief> listOrderBriefById(PolicyBriefCondition policyBriefCondition) {
		List<PolicyBrief> ListOrderBrief= IPolicyDao.listOrderBriefById(policyBriefCondition);
		return ListOrderBrief;
	}

	@Override
	public void deleteBypolicyNumber(String policyNumber) {
		IPolicyDao.deleteBypolicyNumber(policyNumber);
		
	}

	@Override
	public PolicyDetail showPolicyDetail(String policyNumber) {
		PolicyDetail policyDetail = new PolicyDetail();
		//基本信息
		PolicyDetail PolicyDetailBase=IPolicyDao.getPolicyBase(policyNumber);
		//投保人
		PolicyDetail PolicyDetailApplicant=IPolicyDao.getPolicyApplicant(policyNumber);
		//被保人
		PolicyDetail PolicyDetailInsuredPerson=IPolicyDao.getPolicyInsuredPerson(policyNumber);
		//车主
		PolicyDetail PolicyDetailCarOwner=IPolicyDao.getPolicyCarOwner(policyNumber);
		//车险信息
		List<InsuranceTypeBrief> PolicyDetailInsuranceList=IPolicyDao.getPolicyInsuranceList(policyNumber);
		policyDetail.setPolicyNumber(policyNumber);
		policyDetail.setBillStartDate(PolicyDetailBase.getBillStartDate());
		policyDetail.setBillEndDate(PolicyDetailBase.getBillEndDate());
		policyDetail.setBillLong(PolicyDetailBase.getBillLong());
		policyDetail.setApplicantName(PolicyDetailApplicant.getApplicantName());
		policyDetail.setApplicantPhone(PolicyDetailApplicant.getApplicantPhone());
		policyDetail.setApplicantPaperType(PolicyDetailApplicant.getApplicantPaperType());
		policyDetail.setApplicantPaperNum(PolicyDetailApplicant.getApplicantPaperNum());
		policyDetail.setInsuredPersonName(PolicyDetailInsuredPerson.getInsuredPersonName());
		policyDetail.setInsuredPersonPhone(PolicyDetailInsuredPerson.getInsuredPersonPhone());
		policyDetail.setInsuredPersonPaperType(PolicyDetailInsuredPerson.getInsuredPersonPaperType());
		policyDetail.setInsuredPersonPaperNum(PolicyDetailInsuredPerson.getInsuredPersonPaperNum());
		policyDetail.setCarOwnerName(PolicyDetailCarOwner.getCarOwnerName());
		policyDetail.setCarOwnerPhone(PolicyDetailCarOwner.getCarOwnerPhone());
		policyDetail.setCarOwnerPaperType(PolicyDetailCarOwner.getCarOwnerPaperType());
		policyDetail.setCarOwnerPaperNum(PolicyDetailCarOwner.getCarOwnerPaperNum());
		policyDetail.setInsuranceList(PolicyDetailInsuranceList);
		policyDetail.setInsuranceAmount(PolicyDetailBase.getInsuranceAmount());
		return policyDetail;
	}

	@Override
	public void changePolicyStatuSid(String policyNumber) {
		IPolicyDao.changePolicyStatuSid(policyNumber);		
	}

	@Override
	public void addCheckInfo(Integer policyId) {
		IPolicyDao.insertCheckInfo(policyId) ;
		
	}

	@Override
	public Integer getPolicyIdByPolicyNumber(String policyNumber) {
		return IPolicyDao.getPolicyIdByPolicyNumber(policyNumber);
	}

}
