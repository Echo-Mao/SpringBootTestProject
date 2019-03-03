package com.nbui.policy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nbui.entity.VersionInfo;

public interface IVersionDao {

	List<VersionInfo> queryVersionByBrandId(@Param("brandId")String brandId);

	Integer queryCarOfferByVersion(@Param("versionId")String versionId);
}
