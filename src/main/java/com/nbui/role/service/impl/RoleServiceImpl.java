package com.nbui.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.Role;
import com.nbui.role.dao.IRoleDao;
import com.nbui.role.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private IRoleDao roleDao;

	@Override
	public List<Role> findAllByEmpId(Integer loginEmpId) {
		return roleDao.findAllByEmpId(loginEmpId);
	}

	
	
}
