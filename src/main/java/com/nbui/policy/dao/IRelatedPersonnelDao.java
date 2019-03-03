package com.nbui.policy.dao;

import com.nbui.entity.RelatedPersonnel;

public interface IRelatedPersonnelDao {

    /**
     * >添加投保相关人信息,在service层接口中改名,例:addRelatedPersonnelInfo();
     * 
     * @param person
     * @return
     */
    int addRelatedPersonnel(RelatedPersonnel person);

    /**
     * >根据id查询相关人员信息
     * 
     * @param id
     * @return
     */
    RelatedPersonnel findRelatedPersonnelInfoById(int id);

}
