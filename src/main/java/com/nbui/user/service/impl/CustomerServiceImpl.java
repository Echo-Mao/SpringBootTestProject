package com.nbui.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.user.condition.CustomerCondition;
import com.nbui.user.dao.ICustomerDao;
import com.nbui.user.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public PageInfo<PolicyInfo> findAllByCondition(CustomerCondition customerCondition, Integer pageIndex,
			Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<PolicyInfo> policyInfo=customerDao.findAllByCondition(customerCondition);
		PageInfo<PolicyInfo> pageInfo=new PageInfo<>(policyInfo);
		return pageInfo;
	}

	@Override
	public PolicyInfo findCarOwnerInfoById(Integer id) {
		System.err.println(id);
		return customerDao.findCarOwnerInfoById(id);
	}

	@Override
	public List<PolicyInfo> findAll(CustomerCondition customerCondition) {
		return customerDao.findAllByCondition(customerCondition);
	}

}
