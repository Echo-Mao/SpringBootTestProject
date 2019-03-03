package com.nbui.policy.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbui.entity.BrandInfo;
import com.nbui.policy.service.IBrandService;

@RestController
@RequestMapping(value="/brand")
public class BrandController {
	@Autowired
	IBrandService iBrandService ;
	
	@RequestMapping("/findAllBrand.action")
	public HashMap<String,Object> findBrand() {
		List<BrandInfo> brandList = iBrandService.findAll();

		HashMap<String,Object> map = new HashMap<>();
		map.put("brandList", brandList);
        return map;
	} 
}
