package com.nbui.provinceCity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.City;
import com.nbui.provinceCity.dao.ICityDao;
import com.nbui.provinceCity.service.ICityService;
@Service
public class CityServiceImpl implements ICityService{
	@Autowired
	private ICityDao cityQueryDao;
	
	@Override
	public List<City> findAllByProviceId(String proviceId) {
		List<City> cityList = cityQueryDao.findCityByProvinceId(proviceId);
		return cityList;
	}

}
