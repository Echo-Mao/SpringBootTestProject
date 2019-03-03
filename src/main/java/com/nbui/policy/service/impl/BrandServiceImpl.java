package com.nbui.policy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.BrandInfo;
import com.nbui.policy.dao.IBrandDao;
import com.nbui.policy.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandDao iBrandDao ;
	
	@Override
	public List<BrandInfo> findAll() {
		return iBrandDao.queryAll();
	}

}
