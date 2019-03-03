package com.nbui.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbui.entity.Role;
import com.nbui.role.service.IRoleService;

@RestController
@RequestMapping(value="/role")
public class RoleController {
	@Autowired
	IRoleService roleService;
	 /**
     * >根据登录员工id查询比他低的角色
     * @param provinceId
     * @return
     */
    @RequestMapping(value="/findAllRoleByEmpId.action")
    public Map<String, Object> findAllRoleByEmpId(Integer loginEmpId) {
        Map<String, Object> roleMap = new HashMap<>();
        List<Role> roleList = roleService.findAllByEmpId(loginEmpId);
        if (roleList != null) {
            roleMap.put("roleList", roleList);
        }
        return roleMap;
    }
}
