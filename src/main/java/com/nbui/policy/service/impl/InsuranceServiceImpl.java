package com.nbui.policy.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbui.entity.InsuranceType;
import com.nbui.policy.condition.InsuranceTypeCondition;
import com.nbui.policy.dao.IInsuranceDao;
import com.nbui.policy.service.IInsuranceService;
import com.nbui.util.DateUtils;

/**
 * @author EchoMao
 * @date 2019年1月17日下午2:00:26
 * 
 */
@Service
public class InsuranceServiceImpl implements IInsuranceService {

    @Autowired
    IInsuranceDao insuranceDao;

    /**
     * >查询所有险种(预留
     * 
     */
    @Override
    public List<InsuranceType> findAllInsuranceType() {
        return insuranceDao.findAllInsuranceType();
    }

    /**
     * >根据ID查询险种
     * 
     */
    @Override
    public InsuranceType findInsuranceTypeById(int insuranceId) {
        return insuranceDao.findInsuranceTypeById(insuranceId);
    }

    /**
     * >添加险种
     * 
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int addInsuranceType(InsuranceType insurance) throws Exception {
        return insuranceDao.addInsuranceType(insurance);
    }

    /**
     * >删除险种
     * 
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int deleteInsuranceType(String[] ids) throws Exception {
        int flag = 1;
        for (String id : ids) {
            int num = insuranceDao.deleteInsuranceType(new Integer(id));
            if (num == 0) {
                flag = 0;
                break;
            }
        }
        return flag;
    }

    /**
     * >修改险种
     * 
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int updateInsuranceType(InsuranceType insurance) throws Exception {
        return insuranceDao.updateInsuranceType(insurance);
    }

    /**
     * >条件分页查询
     * 
     */
    @Override
    public PageInfo<InsuranceType> findInsuranceTypeByCondition(InsuranceTypeCondition conditionn, Integer page,
            Integer size) {
        PageHelper.startPage(page, size);
        List<InsuranceType> insuranceTypes = insuranceDao.findInsuranceTypeByCondition(conditionn);
        PageInfo<InsuranceType> pageInfo = new PageInfo<>(insuranceTypes);
        return pageInfo;
    }
    
    /**
     * >下载险种报表
     * 
     */
    @Override
    public void exportExcel(HttpServletResponse response, InsuranceTypeCondition condition) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("保单信息表");

        List<InsuranceType> insuranceList = insuranceDao.findAllInsuranceType();

        // 新增数据行，并且设置单元格数据
        String fileName = "insuranceInfoList_" + DateUtils.getCurrentDateStr() + ".xls";// 设置要导出的文件的名字

        int rowNum = 1;

        // headers表示excel表中第一行的表头
        String[] headers = {"险种名称", "上线状态", "报价方案ID", "购买指数", "适用人群", "赔款须知", "案例", "创建时间", "修改时间"};

        // 在excel表中添加表头
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 在表中存放查询到的数据放入对应的列
        if(insuranceList.size()>0) {
            for (InsuranceType insuranceType : insuranceList) {
                int cellNum = 0;
                HSSFRow row1 = sheet.createRow(rowNum);
                //险种名称
                row1.createCell(cellNum++).setCellValue(insuranceType.getInsuranceName());
                //上线状态
                if (insuranceType.getViewState() != 0) {
                    row1.createCell(cellNum++).setCellValue("已上线");
                } else {
                    row1.createCell(cellNum++).setCellValue("已下线/未上线");
                }
                //报价方案id
                row1.createCell(cellNum++).setCellValue(insuranceType.getProgrammeId());
                //购买指数
                row1.createCell(cellNum++).setCellValue(insuranceType.getBuyPercent());
                //适用人群
                row1.createCell(cellNum++).setCellValue(insuranceType.getBuyPercent());
                //赔款须知
                row1.createCell(cellNum++).setCellValue(insuranceType.getNotice());
                //案例
                row1.createCell(cellNum++).setCellValue(insuranceType.getExample());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                //创建时间
                row1.createCell(cellNum++).setCellValue(sdf.format(insuranceType.getCreateTime()));
                //修改时间
                row1.createCell(cellNum++).setCellValue(sdf.format(insuranceType.getModifyTime()));
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

}
