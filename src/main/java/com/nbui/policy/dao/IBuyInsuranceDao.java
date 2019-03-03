package com.nbui.policy.dao;

import org.apache.ibatis.annotations.Param;
import com.nbui.entity.CarInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyInsurance;
import com.nbui.entity.RelatedPersonnel;

public interface IBuyInsuranceDao {

	void insertRelatedPersonnel(RelatedPersonnel carOwner);

	void insertCar(CarInfo car);

	void insertPolicy(PolicyInfo policy);

	CarInfo queryCarInfoByCarId(@Param("carId")Integer carId);

	void insertPolicyInsurance(PolicyInsurance policyInsurance);

	RelatedPersonnel queryCarOwnerInfoByCarOwnerId(@Param("carOwnerId")Integer carOwnerId);

	void updateCarOwner(RelatedPersonnel carOwner1);

	void updatePolicyPersonnel(PolicyInfo policy);

	String getPolicyNumerByPolicyId(@Param("policyId")Integer policyId);

	void deletePolicyInsuranceByPolicyId(@Param("policyId")Integer policyId);

	CarInfo queryCarInfoNolinkedlistByCarId(@Param("carId")Integer carId);

	void updateCarInfo(CarInfo car);

	void updatePolicyInfoByAddInsuranceAmount(PolicyInfo policy);
	
}
