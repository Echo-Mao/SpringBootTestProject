package com.nbui.employee.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nbui.employee.condition.EmpCondition;
import com.nbui.entity.EmpInfo;

import net.sf.json.JSONArray;

public interface IEmpService {
	//工号密码登陆方法
	EmpInfo loginByIDAndPwd(EmpInfo emp);
	//修改密码方法
	boolean updatePassword(Integer loginId,String oldPass,String newPass);

	PageInfo<EmpInfo> findAllAndPage(Integer pageIndex, Integer pageSize, EmpCondition empCondition);

	List<EmpInfo> findAll(EmpCondition empCondition);

	boolean addEmp(EmpInfo empInfo);

	boolean updateRoleId(EmpInfo empInfo);

	boolean deleteOneByEmpId(Integer empId);

	EmpInfo findByEmpId(Integer empId);

	boolean deleteBatchById(JSONArray jArray);

	boolean updateEmp(EmpInfo empInfo);
}
