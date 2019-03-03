package com.nbui.user.dao;

import java.util.List;

import com.nbui.entity.CarInfo;

public interface ICarDao {

	List<CarInfo> findCarById(Integer id);

}
