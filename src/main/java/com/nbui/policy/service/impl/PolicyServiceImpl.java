package com.nbui.policy.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbui.entity.CheckInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyStatus;
import com.nbui.policy.condition.PolicyCondition;
import com.nbui.policy.dao.IPolicyDao;
import com.nbui.policy.dao.IPolicyStatusDao;
import com.nbui.policy.service.IQueryPolicyService;
import com.nbui.util.DateUtils;

@Service
public class PolicyServiceImpl implements IQueryPolicyService {
	
	@Autowired
	private IPolicyStatusDao iPolicyStatusDao;

    /*
     * @Autowired private RedisUtils redisUtils;
     */
    @Autowired
    private IPolicyDao iPolicyDao;

    @Override
    public PageInfo<PolicyInfo> queryPolicyByEmp(PolicyCondition condition, Integer page, Integer size) {
        List<PolicyInfo> queryPolicys = new ArrayList<>();
		PageHelper.startPage(page,size);
//		try {
//			if(redisUtils.hasKey("policyByCondition")) {
//				queryPolicys = (List<PolicyInfo>) redisUtils.get("policyByCondition");
//				System.err.println("redis....");
//			}
//		} catch (PoolException e) {
        queryPolicys = iPolicyDao.queryPolicyByEmp(condition);
//			redisUtils.set("policyByCondition",queryPolicys);
//		}

		PageInfo<PolicyInfo> pageInfo = new PageInfo<>(queryPolicys);
		return pageInfo;
	}

    @Override
    public CheckInfo queryAuditData(PolicyCondition condition) {
        CheckInfo checkInfo = iPolicyDao.queryAuditData(condition);
        return checkInfo;
    }

    @Override
    public Integer auditResource(PolicyCondition condition) {
        return iPolicyDao.audit(condition);
    }

    public void exportPocliy(HttpServletResponse response, PolicyCondition condition) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("保单信息表");

        List<PolicyInfo> policyList = iPolicyDao.queryPolicyByEmp(condition);

        // 新增数据行，并且设置单元格数据
        String fileName = "policyinf_" + DateUtils.getCurrentDateStr() + ".xls";// 设置要导出的文件的名字

        int rowNum = 1;

        // headers表示excel表中第一行的表头
        String[] headers = { "保单号", "车主姓名", "车辆品牌 ", "区域", "车辆价格", "保费", "保单生效日期", "保单终止日期", "业务员姓名", "保单状态" };

        // 在excel表中添加表头
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 在表中存放查询到的数据放入对应的列
        if(policyList.size()>0) {
	        for (PolicyInfo policyInfo : policyList) {
	            int cellNum = 0;
	            HSSFRow row1 = sheet.createRow(rowNum);
	            row1.createCell(cellNum++).setCellValue(policyInfo.getPolicyNum());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getCarOwner().getPersonnelName());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getCarInfo().getBrandInfo().getBrandname() + " "
	                    + policyInfo.getCarInfo().getVersionInfo().getVersionName());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getPolicyHolder().getProvince().getProvince()
	                    + policyInfo.getPolicyHolder().getCity().getCity());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getCarInfo().getVersionInfo().getCarOffer());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getInsuranceAmount());
	            if(policyInfo.getStatusInfo().getStatusId()<5) {
	            	row1.createCell(cellNum++).setCellValue("");
		            row1.createCell(cellNum++).setCellValue("");
	            }else {
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            	
	            	row1.createCell(cellNum++).setCellValue(sdf.format(policyInfo.getBillStartDate()));
		            row1.createCell(cellNum++).setCellValue(sdf.format(policyInfo.getBillEndDate()));
	            }
	            row1.createCell(cellNum++).setCellValue(policyInfo.getOperator().getEmpName());
	            row1.createCell(cellNum++).setCellValue(policyInfo.getStatusInfo().getStatusName());
	            rowNum++;
	        }
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer saveUploadFile(CheckInfo checkInfo) {
    	iPolicyDao.changePolicyStatus(checkInfo.getPolicyId());
        return iPolicyDao.uploadResource(checkInfo);
    }

	@Override
	public List<PolicyStatus> queryPolicyStatus() {
		return iPolicyStatusDao.queryStatus();
	}
}
