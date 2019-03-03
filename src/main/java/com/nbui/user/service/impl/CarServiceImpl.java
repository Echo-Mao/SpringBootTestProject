package com.nbui.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbui.entity.CarInfo;
import com.nbui.user.dao.ICarDao;
import com.nbui.user.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{

	@Autowired
	private ICarDao carDao;

	@Override
	public PageInfo<CarInfo> findCarById(Integer pageIndex, Integer pageSize,Integer id) {
		PageHelper.startPage(pageIndex, pageSize);
		List<CarInfo> carInfo=carDao.findCarById(id);
		PageInfo<CarInfo> pageInfo=new PageInfo<>(carInfo);
		return pageInfo;
	}
}
