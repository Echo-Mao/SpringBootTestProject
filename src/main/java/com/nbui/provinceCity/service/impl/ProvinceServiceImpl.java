package com.nbui.provinceCity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.Province;
import com.nbui.provinceCity.dao.IProvinceDao;
import com.nbui.provinceCity.service.IProvinceService;
@Service
public class ProvinceServiceImpl implements IProvinceService{
	@Autowired
	private IProvinceDao provinceDao;
	
	@Override
	public List<Province> findAll() {
		List<Province> ProvinceList = provinceDao.queryAllProvince();
		return ProvinceList;
	}

	@Override
	public Province findById(String provinceId) {
		Province province = provinceDao.queryProvinceById(provinceId);
		return province;
	}

}
