package com.nbui.user.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.nbui.entity.PolicyInfo;
import com.nbui.user.condition.CustomerCondition;
import com.nbui.user.service.ICustomerService;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

/**
 * @author Ding
 * @date 2019年1月19日 上午10:58:50
 */

@RestController
@RequestMapping("/customer")
public class CustomerInfoController {
	@Autowired
	private ICustomerService customerService;
	
	/*
	 * 根据条件查询所有的客户信息，并且分页
	 * 用于分页显示
	 */
	@RequestMapping("/findAllByCondition.action")
	public RetResult<PageInfo<PolicyInfo>>  findAll(String customerCondition,@RequestParam(defaultValue = "1") Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		CustomerCondition customerConditionObject = JSONObject.parseObject(customerCondition,CustomerCondition.class);	
		// 使用pagehelper帮助分页
		PageInfo<PolicyInfo> page=customerService.findAllByCondition(customerConditionObject,pageIndex,pageSize);
		return RetResponse.makeOKRsp(page);
	}
	/*
	 * 根据车主id查询车主的详细信息
	 */
	@RequestMapping("/findCarOwnerInfoById.action")
	public HashMap<String,Object> findCarOwnerInfoById(Integer carOwnerId) {
		PolicyInfo relatedPersonnel=customerService.findCarOwnerInfoById(carOwnerId);
		HashMap<String,Object> map=new HashMap<>();
		map.put("carOwner", relatedPersonnel);
		return map;
	}
	/*
	 * 根据条件下载报表
	 */
	@RequestMapping("/customerExcelDownload.action")
	public void customerExcelDownload(HttpServletRequest request,HttpServletResponse response,CustomerCondition customerCondition) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<PolicyInfo> policyInfoList = customerService.findAll(customerCondition);
        String fileName = "客户信息表" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"客户姓名","手机号","所在区域","业务员"};
      
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            if(customerCondition.getRoleId()==null||i!=3) {
            	 HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(text);
            }
        }
        System.err.println(customerCondition.getRoleId());
        //在表中存放查询到的数据放入对应的列
        for (PolicyInfo policyInfo: policyInfoList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(policyInfo.getPolicyHolder().getPersonnelName());
            row1.createCell(1).setCellValue(policyInfo.getPolicyHolder().getPhoneNum());
            row1.createCell(2).setCellValue(policyInfo.getProvince().getProvince()+policyInfo.getCity().getCity());
            if(customerCondition.getRoleId()==null) {
            	 row1.createCell(3).setCellValue(policyInfo.getOperator().getEmpName());
            }
            rowNum++;
        }
        //中文编码
        String agent = request.getHeader("USER-AGENT").toLowerCase();
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		if (agent.contains("firefox")) {
			response.setCharacterEncoding("utf-8");
			response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
		} else {
			response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
		}
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
