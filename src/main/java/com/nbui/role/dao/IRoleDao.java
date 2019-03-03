package com.nbui.role.dao;

import java.util.List;

import com.nbui.entity.Role;

import io.lettuce.core.dynamic.annotation.Param;

public interface IRoleDao {
	Role findRoleById(@Param("roleId")Integer roleId);

	List<Role> findAll();
	
	List<Role> findAllByEmpId(@Param("loginEmpId")Integer loginEmpId);
	
	
}
