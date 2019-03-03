package com.nbui.policy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.VersionInfo;
import com.nbui.policy.dao.IVersionDao;
import com.nbui.policy.service.IVersionService;

@Service
public class VersionServiceImpl implements IVersionService {
	@Autowired
	private IVersionDao iVersionDao ;

	@Override
	public List<VersionInfo> getVersionByBrandId(String brandId) {
		return iVersionDao.queryVersionByBrandId(brandId) ;
	}

	@Override
	public Integer getCarOfferByVersion(String versionId) {
		return iVersionDao.queryCarOfferByVersion(versionId) ;
	}

}
