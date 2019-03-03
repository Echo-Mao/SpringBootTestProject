package com.nbui.policy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.PaperInfo;
import com.nbui.policy.dao.IPaperDao;
import com.nbui.policy.service.IPaperService;

@Service
public class PaperServiceImpl implements IPaperService {
	@Autowired
	IPaperDao iPaperDao ;
	
	@Override
	public List<PaperInfo> getAllPaper() {
		return iPaperDao.queryAll();
	}

}
