package com.nbui.policy.dao;

import java.util.List;

import com.nbui.entity.ProgrammeInfo;
import com.nbui.policy.condition.ProgrammeCondition;

public interface IProgrmmeDao {

    /**
     * >查所有
     */
    List<ProgrammeInfo> findAllProgrammeInfo();

    /**
     * >根据条件查
     * 
     * @param condition
     * @return
     */
    List<ProgrammeInfo> findProgrammeInfoByCondition(ProgrammeCondition condition);

    /**
     * >根据id查
     */
    ProgrammeInfo findProgrammeInfoById(int id);

    /**
     * >添加报价方案
     * 
     * @param programme
     * @return
     */
    int addProgrammeInfo(ProgrammeInfo programme);

    /**
     * >修改报价方案
     * 
     * @param programme
     * @return
     */
    int updateProgrammeInfo(ProgrammeInfo programme);

}
