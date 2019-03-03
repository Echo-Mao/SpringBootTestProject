package com.nbui.employee.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.nbui.employee.condition.EmpCondition;
import com.nbui.employee.service.IEmpService;
import com.nbui.entity.City;
import com.nbui.entity.EmpInfo;
import com.nbui.entity.Province;
import com.nbui.entity.Role;
import com.nbui.provinceCity.service.ICityService;
import com.nbui.provinceCity.service.IProvinceService;
import com.nbui.role.service.IRoleService;
import com.nbui.util.DateUtils;
import com.nbui.util.MD5Utils;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

import net.sf.json.JSONArray;
/**
 * 员工信息查询模块
 * @author 雷石秀
 * 员工查询Controller层：EmpQueryController
 * 
 */

@RestController
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private IEmpService empService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	private ICityService cityService;
	
	/*
	 * 根据条件查询所有的员工信息，并且分页
	 * 用于分页显示
	 */
	@RequestMapping("/findAllEmpAndPage.action")
	public RetResult<PageInfo<EmpInfo>> findAllEmpAndPage(@RequestParam(defaultValue="1") Integer pageIndex,@RequestParam(defaultValue="10")Integer pageSize,String empCondition) {
		
		EmpCondition empConditionObject = JSONObject.parseObject(empCondition,EmpCondition.class);
		PageInfo<EmpInfo> empInfo = empService.findAllAndPage(pageIndex, pageSize,empConditionObject);
        return RetResponse.makeOKRsp(empInfo);
	} 
	
	
	/*
	 * 根据条件查询所有的员工信息，不分页
	 * 用于导出员工信息表格文件
	 */
	@RequestMapping("/findAllEmp.action")
	public HashMap<String,Object> findAllEmp(EmpCondition empCondition) {
		List<EmpInfo> empList = empService.findAll(empCondition);
		HashMap<String,Object> map = new HashMap<>();
		map.put("empList", empList);
        return map;
	} 
	
	/*
	 * 查询权限选择列表和省信息
	 * 用于添加页面加载后初始化角色的选项和省的选项  
	 */
	@RequestMapping("/findRoleAndProvince.action")
	public HashMap<String,Object> findRoleAndProvince(String loginEmpId) {
		HashMap<String,Object> map = new HashMap<>();
		char[] array = loginEmpId.toCharArray();
		for (char c : array) {
			if (c<'0' || c>'9') {
				map.put("findStatus", "fail");
				return map;
			}
		}
		Integer empIdInt = Integer.parseInt(loginEmpId);
		
		List<Role> roleList = roleService.findAllByEmpId(empIdInt);
		List<Province> provinceList = provinceService.findAll();
		map.put("findStatus", "success");
		map.put("roleList", roleList);
		map.put("provinceList", provinceList);
        return map;
	} 
	
	/*
	 * 查询根据省id查询市信息
	 * 用于添加页面改变省的选项 后查询市选项 
	 */
	@RequestMapping("/findCityByProvinceId.action")
	public HashMap<String,Object> findCityByProvinceId(String proviceId) {
		List<City> cityList = cityService.findAllByProviceId(proviceId);
		HashMap<String,Object> map = new HashMap<>();
		map.put("cityList", cityList);
        return map;
	}
	
	/*
	 * 添加员工
	 */
	@RequestMapping("/addEmp.action")
	public HashMap<String,Object> addEmp(String empInfo) {
		EmpInfo empInfoObject = JSONObject.parseObject(empInfo,EmpInfo.class);
		String phoneLast = empInfoObject.getPhoneNum().substring(5);  //将密码设置为手机号后六位
		empInfoObject.setLoginPwd(MD5Utils.MD5Encode(phoneLast, "utf-8"));
		boolean addStatus = empService.addEmp(empInfoObject);
		HashMap<String,Object> map = new HashMap<>();
		if (addStatus) {
			map.put("addStatus", "success");
		}else {
			map.put("addStatus", "fail");
		}
        return map;
	} 
	
	/*
	 * 修改员工权限
	 * 注意，这里前端传入的参数只有权限Id和员工Id
	 */
	@RequestMapping("/updateRoleId.action")
	public HashMap<String,Object> updateRoleId(EmpInfo empInfo) {
		boolean updateStatus = empService.updateRoleId(empInfo);
		HashMap<String,Object> map = new HashMap<>();
		if (updateStatus) {
			map.put("updateStatus", "success");
		}else {
			map.put("updateStatus", "fail");
		}
        return map;
	} 
	
	/*
	 * 删除员工
	 */
	@RequestMapping("/deleteOneEmp.action")
	public HashMap<String,Object> deleteOneEmp(Integer deleteId) {
		boolean delelteStatus = empService.deleteOneByEmpId(deleteId);
		HashMap<String,Object> map = new HashMap<>();
		if (delelteStatus) {
			map.put("deleteStatus", "success");
		}else {
			map.put("deletefailNum", "fail");
		}
		 return map;
	} 
	/* 批量删除 */
	@RequestMapping("/deleteBatchEmp.action")
	public HashMap<String,Object> deleteBatchEmp(String empIdArrStr) {
		JSONArray jArray= JSONArray.fromObject(empIdArrStr);
		boolean delelteStatus = empService.deleteBatchById(jArray);
		HashMap<String,Object> map = new HashMap<>();
		if (delelteStatus) {
			map.put("deleteStatus", "success");
		}else {
			map.put("deletefailNum", "fail");
		}
		 return map;
	} 
	
	
	
	/*
	 * 查询员工
	 * 根据员工Id号查询员工信息
	 */
	@RequestMapping("/findByEmpId.action")
	public HashMap<String,Object> findByEmpId(String empId) {
		HashMap<String,Object> map = new HashMap<>();
		char[] array = empId.toCharArray();
		for (char c : array) {
			if (c<'0' || c>'9') {
				map.put("findStatus", "fail");
				return map;
			}
		}
		Integer empIdInt = Integer.parseInt(empId);
		EmpInfo empInfo = empService.findByEmpId(empIdInt);
		
		map.put("empInfo", empInfo);
		if (empInfo==null) {
			map.put("findStatus", "fail");
		}else {
			map.put("findStatus", "success");
		}
        return map;
	} 
	/* 修改登录员工的个人信息 */
	@RequestMapping("/updateEmpByEmpId.action")
	public HashMap<String,Object> updateEmpByEmpId(String empInfo,HttpSession session) {
		
		EmpInfo empInfoObject = JSONObject.parseObject(empInfo,EmpInfo.class);
		boolean updateStatus = empService.updateEmp(empInfoObject);
		HashMap<String,Object> map = new HashMap<>();
		if (updateStatus) {
			EmpInfo loginEmp = empService.findByEmpId(empInfoObject.getEmpId());
			session.setAttribute("loginEmp", loginEmp);
			map.put("updateStatus", "success");
		}else {
			map.put("updateStatus", "fail");
		}
        return map;
	} 
	
	
	
	/* 下载报表   */
	@RequestMapping(value = "empExcelDownload.action")
    public void downloadAllClassmate(HttpServletResponse response,EmpCondition empCondition) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<EmpInfo> empInfoList = empService.findAll(empCondition);
        String fileName = "empinfo"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"员工姓名","工号","手机号 ","年龄","所在区域","职务","是否在职"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (EmpInfo empInfo : empInfoList) {
            HSSFRow row1 = sheet.createRow(rowNum);//代表每次都只添加一行
            row1.createCell(0).setCellValue(empInfo.getEmpName());
            row1.createCell(1).setCellValue(empInfo.getLoginId()+"");
            row1.createCell(2).setCellValue(empInfo.getPhoneNum());
            row1.createCell(3).setCellValue(DateUtils.calculateAge(empInfo.getBirthday())+"");
            row1.createCell(4).setCellValue(empInfo.getProvince().getProvince()+empInfo.getCity().getCity());
            row1.createCell(5).setCellValue(empInfo.getRole().getRoleName());
            row1.createCell(6).setCellValue(empInfo.getIncumbency()==1?"是":"否");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
	
	
	
	
}
