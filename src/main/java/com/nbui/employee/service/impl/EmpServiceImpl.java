package com.nbui.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbui.employee.condition.EmpCondition;
import com.nbui.employee.dao.IEmpDao;
import com.nbui.employee.service.IEmpService;
import com.nbui.entity.EmpInfo;
import com.nbui.util.MD5Utils;

import net.sf.json.JSONArray;

@Service
public class EmpServiceImpl implements IEmpService {
	@Autowired
	IEmpDao empDao;

	/*
	 * 账号密码登陆
	 * 
	 */
	@Override
	public EmpInfo loginByIDAndPwd(EmpInfo emp) {
		if(empDao.findEmpByNameAndPwd(emp)!=null){
			return empDao.findEmpByNameAndPwd(emp);			
		}else {
			return null;
		}
	}
	/*
	 * 修改登陆密码
	 * 
	 */
	public boolean updatePassword(Integer loginId,String oldPass,String newPass){
		EmpInfo emp=new EmpInfo();
		emp.setLoginId(loginId);
		String oldPassM=MD5Utils.MD5Encode(oldPass, "utf-8");
		String newPassM=MD5Utils.MD5Encode(newPass, "utf-8");
		emp.setLoginPwd(oldPassM);
		if(empDao.findEmpByNameAndPwd(emp)!=null){
			EmpInfo emp2=new EmpInfo();
			emp2.setLoginId(loginId);
			emp2.setLoginPwd(newPassM);
			Integer s= empDao.updateEmpPassword(emp2);
			if(s>0) {
				return true;
			}else {
			       return false;	
			       }
		}else{
		    return false;	
		     }
	}
	
	/*
	 * 条件分页查询
	 * 
	 */
	@Override
	public PageInfo<EmpInfo> findAllAndPage(Integer pageIndex, Integer pageSize, EmpCondition empCondition) {
		PageHelper.startPage(pageIndex, pageSize);
        List<EmpInfo> empInfoList = empDao.findAll(empCondition);
        PageInfo<EmpInfo> pageInfo = new PageInfo<>(empInfoList);
        return pageInfo;
	}
	
	
	/*	
	 * 条件查询，全查
	 *
	 */
	@Override
	public List<EmpInfo> findAll(EmpCondition empCondition) {
		List<EmpInfo> empInfoList = empDao.findAll(empCondition);
		return empInfoList;
	}
	
	/*
	 *  添加
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public boolean addEmp(EmpInfo empInfo) {
		EmpInfo empInfoExist = empDao.findByNameAndPhone(empInfo);
		System.out.println("empInfoExist"+empInfoExist);
		if (empInfoExist != null) {
			return false;
		}
		Integer addEmpInfo = empDao.addEmpInfo(empInfo);
		System.out.println("addEmpInfo"+addEmpInfo);
		if (addEmpInfo>0) {
			return true;
		}else {
			return false;
		}
		
	}

	/* 修改权限 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateRoleId(EmpInfo empInfo) {
		EmpInfo empInfoExist = empDao.findByEmpId(empInfo.getEmpId());
		if (empInfoExist != null) {
			Integer updateNum = empDao.updateRoleId(empInfo);
			if (updateNum>0) {
				return true;
			}
		}
		return false;
	}

	/* 删除  通过EmpId删除 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteOneByEmpId(Integer empId) {
		EmpInfo empInfoExist = empDao.findByEmpId(empId);
		if (empInfoExist != null) {
			Integer deleteNum = empDao.deleteByEmpId(empId);
			if (deleteNum>0) {
				return true;
			}
		}
		return false;
	}

	/* 通过Id查询员工 */
	@Override
	public EmpInfo findByEmpId(Integer empId) {
		EmpInfo empInfo = empDao.findByEmpId(empId);
		return empInfo;
	}

   /* 批量删除 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteBatchById(JSONArray jArray) {
		for (Object object : jArray) {
			Integer deleteByEmpIdNum = empDao.deleteByEmpId((Integer)object);
			if (deleteByEmpIdNum<=0) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean updateEmp(EmpInfo empInfo) {
		EmpInfo empInfoExist = empDao.findByEmpId(empInfo.getEmpId());
		if (empInfoExist != null) {
			Integer updateNum = empDao.updateEmp(empInfo);
			if (updateNum>0) {
				return true;
			}
		}
		return false;
	}

}
