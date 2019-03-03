package com.nbui.policy.dao;

import java.util.List;

import com.nbui.entity.PolicyStatus;

public interface IPolicyStatusDao {
	//查询所有状态类型
	List<PolicyStatus> queryStatus();
}
