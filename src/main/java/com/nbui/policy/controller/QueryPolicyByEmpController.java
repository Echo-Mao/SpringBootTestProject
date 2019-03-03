package com.nbui.policy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.CheckInfo;
import com.nbui.entity.PolicyInfo;
import com.nbui.entity.PolicyStatus;
import com.nbui.policy.condition.PolicyCondition;
import com.nbui.policy.service.IQueryPolicyService;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

/**
 * 
 * @author ggz 后台查询保单的接口
 */
@RequestMapping("/policy")
@Controller
public class QueryPolicyByEmpController {
	@Autowired
	private CheckInfo checkInfo;

	@Autowired
	private IQueryPolicyService ips;

	// 后台查询保单
	@RequestMapping(value = "/queryPolicy", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public RetResult<PageInfo<PolicyInfo>> queryPolicyByEmp(PolicyCondition condition,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue ="0") Integer size) {
		PageInfo<PolicyInfo> pageInfo = ips.queryPolicyByEmp(condition, page, size);
		return RetResponse.makeOKRsp(pageInfo);
	}
	
	// 审核资料查询
	@RequestMapping("/queryAuditData")
	@ResponseBody
	public RetResult<CheckInfo> queryAuditData(PolicyCondition condition) {
		CheckInfo checkInfo = ips.queryAuditData(condition);
		return RetResponse.makeOKRsp(checkInfo);
	}
	
	//审核
	@RequestMapping("/auditResource")
	@ResponseBody
	public RetResult<Integer> auditResource(PolicyCondition condition) {
		Integer flag = ips.auditResource(condition);
		return RetResponse.makeOKRsp(flag);
	}
	//导出excel
	@RequestMapping(value = "exportPolicys", method = RequestMethod.GET)
	@ResponseBody
    public void exportPocliys(HttpServletResponse response,PolicyCondition condition) throws IOException {
		ips.exportPocliy(response, condition);
    }
	
	@RequestMapping(value = "/fileUpload", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    public String imgUpdate(MultipartFile[] uploadFiles,Integer policyId, HttpServletRequest request) {
        if (uploadFiles.length<=0) {
        	//return RetResponse.makeRsp(500, "文件不能为空!");
        }
        String filePath = "C:/test/upload/";
       	List<String> fileNames = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
        	String fileName = uploadFile.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传后的路径
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            // 检测是否存在目录 
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
            	fileNames.add(fileName);
            	uploadFile.transferTo(dest);
            	//添加
            } catch (IllegalStateException e) {
            	//return RetResponse.makeRsp(400, "网络暂忙,文件上传失败!");
            } catch (IOException e) {
           	 	//return RetResponse.makeRsp(400, "网络暂忙,文件上传失败!");
            }
		}
        int subNum = 0;
        checkInfo.setIdCard(fileNames.get(subNum++));
        checkInfo.setDriverlicense(fileNames.get(subNum++));
        checkInfo.setDrivingPermit(fileNames.get(subNum++));
        checkInfo.setAnnualReport(fileNames.get(subNum++));
        checkInfo.setVehicleinSpection(fileNames.get(subNum++));
        checkInfo.setFrame(fileNames.get(subNum++));
        checkInfo.setEngine(fileNames.get(subNum++));
        checkInfo.setAgreement(fileNames.get(subNum++));
        checkInfo.setPolicyId(policyId);
        System.err.println(checkInfo.toString());
        ips.saveUploadFile(checkInfo);
//        return RetResponse.makeRsp(200, "文件上传成功!");
        return "redirect:/background/upload-list.html";

    }
	//保单状态表
	@RequestMapping(value = "/queryPolicyStatus", produces = "application/json; charset=utf-8")
	@ResponseBody
	public RetResult<List<PolicyStatus>> queryPolicyStatus() {
		return RetResponse.makeOKRsp(ips.queryPolicyStatus());
	}

}
