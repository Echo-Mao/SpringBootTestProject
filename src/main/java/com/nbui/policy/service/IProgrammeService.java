package com.nbui.policy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.ProgrammeInfo;
import com.nbui.policy.condition.ProgrammeCondition;

public interface IProgrammeService {

    /**
     * >查所有
     */
    List<ProgrammeInfo> findAllProgrammeInfo();

    /**
     * >根据id查
     */
    ProgrammeInfo findProgrammeInfoById(int id);

    /**
     * >根据条件查
     * 
     * @param condition
     * @return
     */
    PageInfo<ProgrammeInfo> findProgrammeInfoByCondition(ProgrammeCondition condition, Integer page, Integer size);

    /**
     * >添加报价方案
     * 
     * @param programme
     * @return
     * @throws Exception 
     */
    int addProgrammeInfo(ProgrammeInfo programme) throws Exception;

    /**
     * >修改报价方案
     * 
     * @param programme
     * @return
     * @throws Exception
     */
    int updateProgrammeInfo(ProgrammeInfo programme) throws Exception;
    
    /**
     * >下载报价方案报表
     * 
     * @param response
     * @param condition
     */
    void exportExcel(HttpServletResponse response, ProgrammeCondition condition);
}
