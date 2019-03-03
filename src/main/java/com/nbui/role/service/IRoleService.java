package com.nbui.role.service;

import java.util.List;

import com.nbui.entity.Role;

public interface IRoleService {

	List<Role> findAllByEmpId(Integer loginEmpId);
	
}
