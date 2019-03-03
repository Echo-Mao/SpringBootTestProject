package com.nbui.policy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.CarInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyInsurance;
import com.nbui.entity.RelatedPersonnel;
import com.nbui.policy.dao.IBuyInsuranceDao;
import com.nbui.policy.service.IBuyInsuranceService;

@Service
public class BuyInsuranceServiceImpl implements IBuyInsuranceService {
	@Autowired
	private IBuyInsuranceDao iBuyInsuranceDao ;
	
	@Override
	public void addRelatedPersonnel(RelatedPersonnel carOwner) {	
		iBuyInsuranceDao.insertRelatedPersonnel(carOwner) ;
	}

	@Override
	public void addCarInfo(CarInfo car) {	
		iBuyInsuranceDao.insertCar(car) ;
	}

	@Override
	public void addPolicy(PolicyInfo policy) {
		iBuyInsuranceDao.insertPolicy(policy) ;
	}

	@Override
	public CarInfo getCarInfoByCarId(Integer carId) {
		return iBuyInsuranceDao.queryCarInfoByCarId(carId);
	}

	@Override
	public void addPolicyInsurances(List<PolicyInsurance> policyInsurances) {
		for (PolicyInsurance policyInsurance : policyInsurances) {
			iBuyInsuranceDao.insertPolicyInsurance(policyInsurance) ; 
		}	
	}

	@Override
	public RelatedPersonnel getCarOwnerInfoByCarOwnerId(Integer carOwnerId) {
		return iBuyInsuranceDao.queryCarOwnerInfoByCarOwnerId(carOwnerId);
	}

	@Override
	public void updateCarOwner(RelatedPersonnel carOwner1) {
		iBuyInsuranceDao.updateCarOwner(carOwner1) ;
	}

	@Override
	public void addApplicant(RelatedPersonnel applicant1) {
		iBuyInsuranceDao.insertRelatedPersonnel(applicant1) ;
	}

	@Override
	public void addInsuredperson(RelatedPersonnel insuredperson1) {
		iBuyInsuranceDao.insertRelatedPersonnel(insuredperson1) ;
	}

	@Override
	public void updatePolicyPersonnel(PolicyInfo policy) {
		iBuyInsuranceDao.updatePolicyPersonnel(policy) ;
	}

	@Override
	public String getPolicyNumerByPolicyId(Integer policyId) {
		return iBuyInsuranceDao.getPolicyNumerByPolicyId(policyId);
	}

	@Override
	public void deletePolicyInsuranceByPolicyId(Integer policyId) {
		iBuyInsuranceDao.deletePolicyInsuranceByPolicyId(policyId) ;
	}

	@Override
	public CarInfo getCarInfoNolinkedlistByCarId(Integer carId) {
		return iBuyInsuranceDao.queryCarInfoNolinkedlistByCarId(carId);
	}

	@Override
	public void updateCarInfo(CarInfo car) {
		iBuyInsuranceDao.updateCarInfo(car) ;
		
	}

	@Override
	public void updatePolicyInfoByAddInsuranceAmount(PolicyInfo policy) {
		iBuyInsuranceDao.updatePolicyInfoByAddInsuranceAmount(policy) ;
		
	}

}
