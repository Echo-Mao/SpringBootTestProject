package com.nbui.policy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.nbui.entity.CarInfo;
import com.nbui.entity.LoginInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyInsurance;
import com.nbui.entity.RelatedPersonnel;
import com.nbui.policy.service.IBuyInsuranceService;
import com.nbui.policy.service.MyInsuranceService;
import com.nbui.user.service.IUserService;
import com.nbui.util.DateUtils;

/**
 * 购买车险
 * @author 何循政
 *
 */
@RestController
@RequestMapping("/buyInsurance")
public class BuyInsuranceController {
	@Autowired
	IBuyInsuranceService iBuyInsuranceService ;
	
	@Autowired
	IUserService IUserService;
	
	@Autowired
	MyInsuranceService MyInsuranceService;
	
	/*@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	ICityService cityService ;
	
	@RequestMapping("/findProvince.action")
	public HashMap<String,Object> findProvince() {
		List<Province> provinceList = provinceService.findAll();
		HashMap<String,Object> map = new HashMap<>();
		map.put("provinceList", provinceList);
        return map;
	} 
	
	@RequestMapping("/getCityByProvinceId.action")
	public HashMap<String,Object> getCityByProvinceId(String provinceId) {
		List<City> cityList = cityService.findAllByProviceId(provinceId) ;
		HashMap<String,Object> map = new HashMap<>();
		map.put("cityList", cityList);
        return map;
	} */
	
	
	@RequestMapping("/addCarInfoAndCarOwnerInfo.action")
	public HashMap<String,Object> addCarInfoAndCarOwnerInfo(String relatedPersonnel, String carInfo,
															Integer score, Integer carOffer,HttpServletRequest req) {
		RelatedPersonnel carOwner = JSONObject.parseObject(relatedPersonnel,RelatedPersonnel.class);	
		iBuyInsuranceService.addRelatedPersonnel(carOwner) ;
		Integer carOwnerId = carOwner.getPersonnelId() ;

		CarInfo car = JSONObject.parseObject(carInfo,CarInfo.class);
		car.setCarOwnerId(carOwnerId);
		iBuyInsuranceService.addCarInfo(car) ;
		Integer carId = car.getCarId() ;

		PolicyInfo policy = new PolicyInfo() ;
		policy.setCarId(carId) ;
		policy.setCarOwnerId(carOwnerId) ;
		policy.setPolicyNum(DateUtils.getCurrentDateStr());
		policy.setEmpId(1);   //设置跟单员ID
		
		//获取当前登录用户的ID
		HttpSession session=req.getSession();
		LoginInfo user = (LoginInfo)session.getAttribute("loginInfo");
		Integer UserId=IUserService.getUserId(user.getLoginId());
		policy.setUserId(UserId);  //设置用户ID
		iBuyInsuranceService.addPolicy(policy) ;	
		Integer policyId = policy.getPolicyId() ;			
		HashMap<String,Object> map = new HashMap<>();
		map.put("carId", carId) ;
		map.put("carOwnerId", carOwnerId) ;
		map.put("policyId", policyId) ;
        return map;
	} 
	
	@RequestMapping("/getCarInfoByCarId.action")
	public HashMap<String,Object> getCarInfoByCarId(Integer carId) {
		CarInfo carInfo = iBuyInsuranceService.getCarInfoByCarId(carId) ;
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("carInfo", carInfo) ;
        return map;
	}
	
	@RequestMapping("/getCarInfoNolinkedlistByCarId.action")
	public HashMap<String,Object> getCarInfoNolinkedlistByCarId(Integer carId) {
		CarInfo carInfo = iBuyInsuranceService.getCarInfoNolinkedlistByCarId(carId) ;	
		HashMap<String,Object> map = new HashMap<>();
		map.put("carInfo", carInfo) ;
        return map;
	}
	
	
	@RequestMapping("/highCostPerformanceRatio.action")
	public void highCostPerformanceRatio(String vehicleLossInsurance,
									 String commercialThirdPartyLiabilityInsurance,
									 String driverSeatLiabilityInsurance,
									 String passengerSeatLiabilityInsurance,
									 String spontaneousCombustionLossInsurance,
									 String excludingSpecialInsurance,
									 String extraPremium,
									 String compulsoryInsurance,
									 Integer policyId,
									 Double insuranceAmount
									) {
		PolicyInfo policy = new PolicyInfo() ;
		policy.setPolicyId(policyId);
		policy.setInsuranceAmount(insuranceAmount);
		iBuyInsuranceService.updatePolicyInfoByAddInsuranceAmount(policy) ;
		iBuyInsuranceService.deletePolicyInsuranceByPolicyId(policyId) ;
		
		List<PolicyInsurance> policyInsurances = new ArrayList<PolicyInsurance>() ;
		PolicyInsurance vehicleLossInsurance1 = JSONObject.parseObject(vehicleLossInsurance,PolicyInsurance.class);		
		PolicyInsurance commercialThirdPartyLiabilityInsurance1 = JSONObject.parseObject(commercialThirdPartyLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance driverSeatLiabilityInsurance1 = JSONObject.parseObject(driverSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance passengerSeatLiabilityInsurance1 = JSONObject.parseObject(passengerSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance spontaneousCombustionLossInsurance1 = JSONObject.parseObject(spontaneousCombustionLossInsurance,PolicyInsurance.class);
		PolicyInsurance excludingSpecialInsurance1 = JSONObject.parseObject(excludingSpecialInsurance,PolicyInsurance.class);
		PolicyInsurance extraPremium1 = JSONObject.parseObject(extraPremium,PolicyInsurance.class);
		PolicyInsurance compulsoryInsurance1 = JSONObject.parseObject(compulsoryInsurance,PolicyInsurance.class);

		policyInsurances.add(vehicleLossInsurance1) ;
		policyInsurances.add(commercialThirdPartyLiabilityInsurance1) ;
		policyInsurances.add(driverSeatLiabilityInsurance1) ;
		policyInsurances.add(passengerSeatLiabilityInsurance1) ;
		policyInsurances.add(spontaneousCombustionLossInsurance1) ;
		policyInsurances.add(excludingSpecialInsurance1) ;
		if(extraPremium1.getInsurancePrice() > 0) {
			policyInsurances.add(extraPremium1) ;
		}
		policyInsurances.add(compulsoryInsurance1) ;
		
		iBuyInsuranceService.addPolicyInsurances(policyInsurances) ;
	}
	
	@RequestMapping("/basicProtection.action")
	public void basicProtection(String vehicleLossInsurance,
									 String commercialThirdPartyLiabilityInsurance,
									 String driverSeatLiabilityInsurance,
									 String passengerSeatLiabilityInsurance,
									 String spontaneousCombustionLossInsurance,
									 String extraPremium,
									 String compulsoryInsurance,
									 Integer policyId,
									 Double insuranceAmount
									) {
		PolicyInfo policy = new PolicyInfo() ;
		policy.setPolicyId(policyId);
		policy.setInsuranceAmount(insuranceAmount);
		iBuyInsuranceService.updatePolicyInfoByAddInsuranceAmount(policy) ;
		iBuyInsuranceService.deletePolicyInsuranceByPolicyId(policyId) ;
		
		List<PolicyInsurance> policyInsurances = new ArrayList<PolicyInsurance>() ;
		PolicyInsurance vehicleLossInsurance1 = JSONObject.parseObject(vehicleLossInsurance,PolicyInsurance.class);		
		PolicyInsurance commercialThirdPartyLiabilityInsurance1 = JSONObject.parseObject(commercialThirdPartyLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance driverSeatLiabilityInsurance1 = JSONObject.parseObject(driverSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance passengerSeatLiabilityInsurance1 = JSONObject.parseObject(passengerSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance spontaneousCombustionLossInsurance1 = JSONObject.parseObject(spontaneousCombustionLossInsurance,PolicyInsurance.class);
		PolicyInsurance extraPremium1 = JSONObject.parseObject(extraPremium,PolicyInsurance.class);
		PolicyInsurance compulsoryInsurance1 = JSONObject.parseObject(compulsoryInsurance,PolicyInsurance.class);

		policyInsurances.add(vehicleLossInsurance1) ;
		policyInsurances.add(commercialThirdPartyLiabilityInsurance1) ;
		policyInsurances.add(driverSeatLiabilityInsurance1) ;
		policyInsurances.add(passengerSeatLiabilityInsurance1) ;
		policyInsurances.add(spontaneousCombustionLossInsurance1) ;
		if(extraPremium1.getInsurancePrice() > 0) {
			policyInsurances.add(extraPremium1) ;
		}
		policyInsurances.add(compulsoryInsurance1) ;
		
		iBuyInsuranceService.addPolicyInsurances(policyInsurances) ;
	}
	
	@RequestMapping("/comprehensiveGuarantee.action")
	public void comprehensiveGuarantee(String vehicleLossInsurance,
									 String commercialThirdPartyLiabilityInsurance,
									 String driverSeatLiabilityInsurance,
									 String passengerSeatLiabilityInsurance,
									 String spontaneousCombustionLossInsurance,
									 String excludingSpecialInsurance,
									 String fullVehicleEmbezzlementInsurance,
									 String	wadingRisk,
									 String	bodyScratchDamageRisk,
									 String	breakageRiskofGlass,
									 String extraPremium,
									 String compulsoryInsurance,
									 Integer policyId,
									 Double insuranceAmount
									) {
		PolicyInfo policy = new PolicyInfo() ;
		policy.setPolicyId(policyId);
		policy.setInsuranceAmount(insuranceAmount);
		iBuyInsuranceService.updatePolicyInfoByAddInsuranceAmount(policy) ;
		iBuyInsuranceService.deletePolicyInsuranceByPolicyId(policyId) ;
		
		List<PolicyInsurance> policyInsurances = new ArrayList<PolicyInsurance>() ;
		PolicyInsurance vehicleLossInsurance1 = JSONObject.parseObject(vehicleLossInsurance,PolicyInsurance.class);		
		PolicyInsurance commercialThirdPartyLiabilityInsurance1 = JSONObject.parseObject(commercialThirdPartyLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance driverSeatLiabilityInsurance1 = JSONObject.parseObject(driverSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance passengerSeatLiabilityInsurance1 = JSONObject.parseObject(passengerSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance spontaneousCombustionLossInsurance1 = JSONObject.parseObject(spontaneousCombustionLossInsurance,PolicyInsurance.class);		
		PolicyInsurance excludingSpecialInsurance1 = JSONObject.parseObject(excludingSpecialInsurance,PolicyInsurance.class);		
		PolicyInsurance fullVehicleEmbezzlementInsurance1 = JSONObject.parseObject(fullVehicleEmbezzlementInsurance,PolicyInsurance.class);
		PolicyInsurance wadingRisk1 = JSONObject.parseObject(wadingRisk,PolicyInsurance.class);
		PolicyInsurance bodyScratchDamageRisk1 = JSONObject.parseObject(bodyScratchDamageRisk,PolicyInsurance.class);
		PolicyInsurance breakageRiskofGlass1 = JSONObject.parseObject(breakageRiskofGlass,PolicyInsurance.class);
		PolicyInsurance extraPremium1 = JSONObject.parseObject(extraPremium,PolicyInsurance.class);
		PolicyInsurance compulsoryInsurance1 = JSONObject.parseObject(compulsoryInsurance,PolicyInsurance.class);

		policyInsurances.add(vehicleLossInsurance1) ;
		policyInsurances.add(commercialThirdPartyLiabilityInsurance1) ;
		policyInsurances.add(driverSeatLiabilityInsurance1) ;
		policyInsurances.add(passengerSeatLiabilityInsurance1) ;
		policyInsurances.add(spontaneousCombustionLossInsurance1) ;
		policyInsurances.add(excludingSpecialInsurance1) ;
		policyInsurances.add(fullVehicleEmbezzlementInsurance1) ;
		policyInsurances.add(wadingRisk1) ;
		policyInsurances.add(bodyScratchDamageRisk1) ;
		policyInsurances.add(breakageRiskofGlass1) ;
		if(extraPremium1.getInsurancePrice() > 0) {
			policyInsurances.add(extraPremium1) ;
		}
		policyInsurances.add(compulsoryInsurance1) ;
		
		iBuyInsuranceService.addPolicyInsurances(policyInsurances) ;
	}		
	
	@RequestMapping("/optionalScheme.action")
	public void optionalScheme(String vehicleLossInsurance,
									 String commercialThirdPartyLiabilityInsurance,
									 String driverSeatLiabilityInsurance,
									 String passengerSeatLiabilityInsurance,
									 String spontaneousCombustionLossInsurance,
									 String excludingSpecialInsurance,
									 String fullVehicleEmbezzlementInsurance,
									 String	wadingRisk,
									 String	bodyScratchDamageRisk,
									 String	breakageRiskofGlass,
									 String extraPremium,
									 String compulsoryInsurance,
									 Integer policyId,
									 Double insuranceAmount
									) {
		PolicyInfo policy = new PolicyInfo() ;
		policy.setPolicyId(policyId);
		policy.setInsuranceAmount(insuranceAmount);
		iBuyInsuranceService.updatePolicyInfoByAddInsuranceAmount(policy) ;
		iBuyInsuranceService.deletePolicyInsuranceByPolicyId(policyId) ;
		List<PolicyInsurance> policyInsurances = new ArrayList<PolicyInsurance>() ;
		PolicyInsurance vehicleLossInsurance1 = JSONObject.parseObject(vehicleLossInsurance,PolicyInsurance.class);		
		PolicyInsurance commercialThirdPartyLiabilityInsurance1 = JSONObject.parseObject(commercialThirdPartyLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance driverSeatLiabilityInsurance1 = JSONObject.parseObject(driverSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance passengerSeatLiabilityInsurance1 = JSONObject.parseObject(passengerSeatLiabilityInsurance,PolicyInsurance.class);
		PolicyInsurance spontaneousCombustionLossInsurance1 = JSONObject.parseObject(spontaneousCombustionLossInsurance,PolicyInsurance.class);		
		PolicyInsurance excludingSpecialInsurance1 = JSONObject.parseObject(excludingSpecialInsurance,PolicyInsurance.class);		
		PolicyInsurance fullVehicleEmbezzlementInsurance1 = JSONObject.parseObject(fullVehicleEmbezzlementInsurance,PolicyInsurance.class);
		PolicyInsurance wadingRisk1 = JSONObject.parseObject(wadingRisk,PolicyInsurance.class);
		PolicyInsurance bodyScratchDamageRisk1 = JSONObject.parseObject(bodyScratchDamageRisk,PolicyInsurance.class);
		PolicyInsurance breakageRiskofGlass1 = JSONObject.parseObject(breakageRiskofGlass,PolicyInsurance.class);
		PolicyInsurance extraPremium1 = JSONObject.parseObject(extraPremium,PolicyInsurance.class);
		PolicyInsurance compulsoryInsurance1 = JSONObject.parseObject(compulsoryInsurance,PolicyInsurance.class);
		
		if(vehicleLossInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(vehicleLossInsurance1) ;
		}
		if(commercialThirdPartyLiabilityInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(commercialThirdPartyLiabilityInsurance1) ;
		}
		if(driverSeatLiabilityInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(driverSeatLiabilityInsurance1) ;
		}
		if(passengerSeatLiabilityInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(passengerSeatLiabilityInsurance1) ;
		}
		if(spontaneousCombustionLossInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(spontaneousCombustionLossInsurance1) ;
		}
		if(excludingSpecialInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(excludingSpecialInsurance1) ;
		}
		if(fullVehicleEmbezzlementInsurance1.getInsurancePrice() > 0) {
			policyInsurances.add(fullVehicleEmbezzlementInsurance1) ;
		}
		if(wadingRisk1.getInsurancePrice() > 0) {
			policyInsurances.add(wadingRisk1) ;
		}
		if(bodyScratchDamageRisk1.getInsurancePrice() > 0) {
		policyInsurances.add(bodyScratchDamageRisk1) ;
		}
		if(breakageRiskofGlass1.getInsurancePrice() > 0) {
		policyInsurances.add(breakageRiskofGlass1) ;
		}
		if(extraPremium1.getInsurancePrice() > 0) {
		policyInsurances.add(extraPremium1) ;
		}
		
		policyInsurances.add(compulsoryInsurance1) ;
		iBuyInsuranceService.addPolicyInsurances(policyInsurances) ;
	}
	
	@RequestMapping("/getCarOwnerInfoByCarOwnerId.action")
	public HashMap<String,Object> getCarOwnerInfoByCarOwnerId(Integer carOwnerId) {
		RelatedPersonnel carOwner = iBuyInsuranceService.getCarOwnerInfoByCarOwnerId(carOwnerId) ;
		HashMap<String,Object> map = new HashMap<>();
		map.put("carOwner", carOwner) ;
        return map;
	}
	
	@RequestMapping("/UpdateAllRelatedPersonnelInfo.action")
	public void UpdateAllRelatedPersonnelInfo(String carOwner, String applicant, String insuredperson,
												Integer policyId, Integer carOwnerId) {
		RelatedPersonnel carOwner1 = JSONObject.parseObject(carOwner,RelatedPersonnel.class);
		RelatedPersonnel applicant1 = JSONObject.parseObject(applicant,RelatedPersonnel.class);
		RelatedPersonnel insuredperson1 = JSONObject.parseObject(insuredperson,RelatedPersonnel.class);
		carOwner1.setPersonnelId(carOwnerId);
		iBuyInsuranceService.updateCarOwner(carOwner1) ;
		iBuyInsuranceService.addApplicant(applicant1) ;
		iBuyInsuranceService.addInsuredperson(insuredperson1) ;
		Integer policyHolderId = applicant1.getPersonnelId() ;
		Integer insurantId = insuredperson1.getPersonnelId() ;
		PolicyInfo policy = new PolicyInfo() ;
		policy.setPolicyId(policyId);
		policy.setPolicyHolderId(policyHolderId);
		policy.setInsurantId(insurantId);
		iBuyInsuranceService.updatePolicyPersonnel(policy) ;
	}
	
	
	@RequestMapping("/getPolicyNumerByPolicyId.action")
	public HashMap<String,Object> getPolicyNumerByPolicyId(Integer policyId) {
		String policyNumber = iBuyInsuranceService.getPolicyNumerByPolicyId(policyId) ;
		HashMap<String,Object> map = new HashMap<>();
		map.put("policyNumber", policyNumber) ;
        return map;
	}
	
	
	@RequestMapping("/updateCarInfoAndCarOwnerInfo.action")
	public void updateCarInfoAndCarOwnerInfo(String carOwner, String carInfo, Integer carOwnerId, Integer carId) {
		RelatedPersonnel carOwner1 = JSONObject.parseObject(carOwner,RelatedPersonnel.class);
		CarInfo car = JSONObject.parseObject(carInfo,CarInfo.class);
		carOwner1.setPersonnelId(carOwnerId);
		car.setCarId(carId);

		iBuyInsuranceService.updateCarInfo(car) ;
		iBuyInsuranceService.updateCarOwner(carOwner1);
	}
	
	@RequestMapping("/goToMyInsurance.action")
	public void goToMyInsurance(Integer policyId) {
System.err.println("准备跳往个人中心11111111111111111111111111111111111");
		if(policyId != null){
			MyInsuranceService.addCheckInfo(policyId) ;
		}
	}
	
}
