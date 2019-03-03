package com.nbui.user.dao;

import java.util.List;

import com.nbui.entity.PolicyInfo;
import com.nbui.user.condition.CustomerCondition;
/**
 * 
 * @author Ding
 * @date 2019年1月19日 上午11:40:04
 */
public interface ICustomerDao {
	
	/*
	 * 根据条件查询带分页
	 */
	List<PolicyInfo> findAllByCondition(CustomerCondition customerCondition);

	PolicyInfo findCarOwnerInfoById(Integer id);
}
