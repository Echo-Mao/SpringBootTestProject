package com.nbui.provinceCity.service;

import java.util.List;

import com.nbui.entity.City;

public interface ICityService {

	List<City> findAllByProviceId(String proviceId);

}
