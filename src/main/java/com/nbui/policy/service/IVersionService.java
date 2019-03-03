package com.nbui.policy.service;

import java.util.List;

import com.nbui.entity.VersionInfo;

public interface IVersionService {

	List<VersionInfo> getVersionByBrandId(String brandId);

	Integer getCarOfferByVersion(String versionId);

}
