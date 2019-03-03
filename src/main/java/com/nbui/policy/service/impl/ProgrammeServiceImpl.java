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
import com.nbui.entity.ProgrammeInfo;
import com.nbui.policy.condition.ProgrammeCondition;
import com.nbui.policy.dao.IProgrmmeDao;
import com.nbui.policy.service.IProgrammeService;
import com.nbui.util.DateUtils;

/**
 * @author EchoMao
 * @date 2019年1月17日下午2:00:30
 * 
 */
@Service
public class ProgrammeServiceImpl implements IProgrammeService {

    @Autowired
    IProgrmmeDao programmeDao;

    @Override
    public List<ProgrammeInfo> findAllProgrammeInfo() {
        return programmeDao.findAllProgrammeInfo();
    }

    @Override
    public ProgrammeInfo findProgrammeInfoById(int id) {
        return programmeDao.findProgrammeInfoById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int addProgrammeInfo(ProgrammeInfo programme) throws Exception {
        return programmeDao.addProgrammeInfo(programme);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int updateProgrammeInfo(ProgrammeInfo programme) throws Exception {
        return programmeDao.updateProgrammeInfo(programme);
    }

    @Override
    public PageInfo<ProgrammeInfo> findProgrammeInfoByCondition(ProgrammeCondition condition, Integer page,
            Integer size) {
        PageHelper.startPage(page, size);
        List<ProgrammeInfo> programmeInfo = programmeDao.findProgrammeInfoByCondition(condition);
        PageInfo<ProgrammeInfo> pageInfo = new PageInfo<>(programmeInfo);
        return pageInfo;
    }

    @Override
    public void exportExcel(HttpServletResponse response, ProgrammeCondition condition) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("保单信息表");

        List<ProgrammeInfo> programmeInfoList = programmeDao.findAllProgrammeInfo();

        // 新增数据行，并且设置单元格数据
        String fileName = "programmeInfoList_" + DateUtils.getCurrentDateStr() + ".xls";// 设置要导出的文件的名字

        int rowNum = 1;

        // headers表示excel表中第一行的表头
        String[] headers = {"报价方案ID", "百分比", "基础保费", "创建时间", "修改时间"};

        // 在excel表中添加表头
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 在表中存放查询到的数据放入对应的列
        if(programmeInfoList.size()>0) {
            for (ProgrammeInfo programmeInfo : programmeInfoList) {
                int cellNum = 0;
                HSSFRow row1 = sheet.createRow(rowNum);
                //报价方案ID
                row1.createCell(cellNum++).setCellValue(programmeInfo.getProgrammeId());
                //百分比
                row1.createCell(cellNum++).setCellValue(programmeInfo.getPercent());
                //基础保费
                row1.createCell(cellNum++).setCellValue(programmeInfo.getBasicsMoney());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                //创建时间
                row1.createCell(cellNum++).setCellValue(sdf.format(programmeInfo.getCreateTime()));
                //修改时间
                row1.createCell(cellNum++).setCellValue(sdf.format(programmeInfo.getModifyTime()));
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
