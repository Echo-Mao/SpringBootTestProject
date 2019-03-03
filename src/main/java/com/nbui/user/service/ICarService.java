package com.nbui.user.service;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.CarInfo;

public interface ICarService {

	PageInfo<CarInfo> findCarById(Integer pageIndex, Integer pageSize,Integer id);

}
