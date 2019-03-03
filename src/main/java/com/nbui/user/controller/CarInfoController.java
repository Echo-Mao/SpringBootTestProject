package com.nbui.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.CarInfo;
import com.nbui.user.service.ICarService;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

/**
 * @author Ding
 * @date 2019年1月19日 上午10:58:50
 */

@RestController
@RequestMapping("/car")
public class CarInfoController {
	@Autowired
	private ICarService carService;
	
	/*
	 * 根据条件查询所有的客户信息，并且分页
	 * 用于分页显示
	 */
	@RequestMapping("/findCarById.action")
	public RetResult<PageInfo<CarInfo>>  findAll(@RequestParam(defaultValue = "1") Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize,Integer id) {
		// 使用pagehelper帮助分页
		PageInfo<CarInfo> page=carService.findCarById(pageIndex,pageSize,id);
		System.err.println(page);
		return RetResponse.makeOKRsp(page);
	}
}
