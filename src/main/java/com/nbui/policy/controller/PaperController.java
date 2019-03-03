package com.nbui.policy.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nbui.entity.PaperInfo;
import com.nbui.policy.service.IPaperService;

@RestController
@RequestMapping(value="/paper")
public class PaperController {
	@Autowired
	IPaperService iPaperService ;
	
	@RequestMapping("/getAllPaper.action")
	public HashMap<String,Object> getAllPaper() {
		List<PaperInfo> paperList = iPaperService.getAllPaper();
		HashMap<String,Object> map = new HashMap<>();
		map.put("paperList", paperList);
        return map;
	} 
}