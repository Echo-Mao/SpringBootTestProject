package com.nbui.policy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.ProgrammeInfo;
import com.nbui.policy.condition.ProgrammeCondition;
import com.nbui.policy.service.IProgrammeService;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

/**
 * @author EchoMao
 * @date 2019年1月17日下午6:39:01
 * 
 */
@RestController
@RequestMapping(value = "/programme")
public class ProgrammeController {

    @Autowired
    IProgrammeService programmeService;

    /**
     * >报价方案的多条件分页查询
     * 
     * @param condition 条件参数
     * @param page      当前页码
     * @param size      页容量
     * @return 统一返回格式
     */
    @RequestMapping(value = "/programmeList.action")
    public RetResult<PageInfo<ProgrammeInfo>> toProgrammeList(ProgrammeCondition condition,
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo<ProgrammeInfo> pageInfo = programmeService.findProgrammeInfoByCondition(condition, page, size);
        return RetResponse.makeOKRsp(pageInfo);
    }

    /**
     * >查询所有报价方案
     * 
     * @return
     */
    @RequestMapping(value = "/findAllProgramme.action")
    public RetResult<List<ProgrammeInfo>> findAllProgramme() {
        List<ProgrammeInfo> programmes = programmeService.findAllProgrammeInfo();
        return RetResponse.makeOKRsp(programmes);
    }

    /**
     * >添加报价方案
     * 
     * @param programme 新增的险种对象
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/addProgramme.action")
    public RetResult<Integer> addProgramme(ProgrammeInfo programme) {
        Date now = new Date();
        programme.setCreateTime(now);
        programme.setModifyTime(now);
        int flag = 0;
        try {
            flag = programmeService.addProgrammeInfo(programme);
        } catch (Exception e) {
            System.err.println("错误-1445 : 添加方法出现错误,已执行操作将进行回滚.");
        }
        if (flag > 0) {
            return RetResponse.makeRsp(200, "Option - Add : SUCCESS", flag);
        }
        return RetResponse.makeRsp(500, "Option - Add : FAIL", flag);
    }

    /**
     * >修改报价方案
     * 
     * @param programme 修改后的从前台获取到的险种对象
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/updateProgramme.action")
    public RetResult<Integer> updateProgramme(ProgrammeInfo programme) {
        programme.setModifyTime(new Date());
        int flag = 0;
        try {
            flag = programmeService.updateProgrammeInfo(programme);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("错误-9941 : 修改方法出现错误,已执行操作将进行回滚.");
        }
        if (flag > 0) {
            return RetResponse.makeRsp(200, "Option - Update : SUCCESS", flag);
        }
        return RetResponse.makeRsp(500, "Option - Update : FAIL", flag);
    }

    /**
     * >根据id查找报价方案,是前往修改页面的中间控制器
     * 
     * @param id 从location获取到的id
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/toUpdate.action")
    public RetResult<ProgrammeInfo> toUpdate(Integer programmeId) {
        ProgrammeInfo programme = programmeService.findProgrammeInfoById(programmeId);
        return RetResponse.makeOKRsp(programme);
    }

    /**
     * >导出Excel
     * 
     * @param response
     * @param condition
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel.action")
    public void exportExcel(HttpServletResponse response, ProgrammeCondition condition) throws Exception {
        programmeService.exportExcel(response, condition);
    }

}
