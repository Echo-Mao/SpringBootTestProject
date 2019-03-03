package com.nbui.user.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.user.condition.CustomerCondition;

public interface ICustomerService {

	PageInfo<PolicyInfo> findAllByCondition(CustomerCondition customerCondition, Integer pageIndex, Integer pageSize);

	PolicyInfo findCarOwnerInfoById(Integer id);

	List<PolicyInfo> findAll(CustomerCondition customerCondition);

}
