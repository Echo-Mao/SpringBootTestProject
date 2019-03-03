package com.nbui.policy.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nbui.entity.VersionInfo;
import com.nbui.policy.service.IVersionService;

@RestController
@RequestMapping(value="/version")
public class VersionController {
	@Autowired
	IVersionService iVersionService ;
	
	@RequestMapping("/getVersionByBrandId.action")
	public HashMap<String,Object> getVersionByBrandId(String brandId) {
		List<VersionInfo> versionList = iVersionService.getVersionByBrandId(brandId);
		HashMap<String,Object> map = new HashMap<>();
		map.put("versionList", versionList);
        return map;
	} 
	
	@RequestMapping("/getCarOfferByVersion.action")
	public HashMap<String,Object> getCarOfferByVersion(String versionId) {
		Integer carOffer = iVersionService.getCarOfferByVersion(versionId);
		HashMap<String,Object> map = new HashMap<>();
		map.put("carOffer", carOffer);
        return map;
	} 
}