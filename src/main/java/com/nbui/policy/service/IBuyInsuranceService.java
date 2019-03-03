package com.nbui.policy.service;

import java.util.List;

import com.nbui.entity.CarInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyInsurance;
import com.nbui.entity.RelatedPersonnel;

public interface IBuyInsuranceService {

	void addRelatedPersonnel(RelatedPersonnel carOwner);

	void addCarInfo(CarInfo car);

	void addPolicy(PolicyInfo policy);

	CarInfo getCarInfoByCarId(Integer carId);

	void addPolicyInsurances(List<PolicyInsurance> policyInsurances);

	RelatedPersonnel getCarOwnerInfoByCarOwnerId(Integer carOwnerId);

	void updateCarOwner(RelatedPersonnel carOwner1);

	void addApplicant(RelatedPersonnel applicant1);

	void addInsuredperson(RelatedPersonnel insuredperson1);

	void updatePolicyPersonnel(PolicyInfo policy);

	String getPolicyNumerByPolicyId(Integer policyId);

	void deletePolicyInsuranceByPolicyId(Integer policyId);

	CarInfo getCarInfoNolinkedlistByCarId(Integer carId);

	void updateCarInfo(CarInfo car);

	void updatePolicyInfoByAddInsuranceAmount(PolicyInfo policy);

}
