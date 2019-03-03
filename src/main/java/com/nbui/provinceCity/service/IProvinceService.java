package com.nbui.provinceCity.service;

import java.util.List;

import com.nbui.entity.Province;

public interface IProvinceService {

	List<Province> findAll();

	Province findById(String provinceId);

}
