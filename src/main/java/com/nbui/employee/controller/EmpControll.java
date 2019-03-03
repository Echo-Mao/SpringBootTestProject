package com.nbui.employee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbui.employee.service.IEmpService;
import com.nbui.entity.EmpInfo;
import com.nbui.util.MD5Utils;

/**
 * 员工后台系统登陆，修改密码
 * @author 罗怡
 */
@Controller
public class EmpControll {
	
	@Autowired
	IEmpService empService;
	//员工账号密码登陆
	@RequestMapping("/EmpLogin.action")
	@ResponseBody
	public String loginByIdAndPwd(EmpInfo empOraginal,Boolean remember , HttpServletResponse resp,HttpSession session){
		
		String isLogin = (String)session.getAttribute("isLogin");
		//System.err.println(empOraginal.getLoginPwd());
		//System.err.println(remember);
		if ("true".equals(isLogin)) {
			return "{\"msg\":\"您已有账号登陆了，请先退出再登陆\"}";
		}
		String pwdmm=MD5Utils.MD5Encode(empOraginal.getLoginPwd(), "utf-8");
		EmpInfo emp = new EmpInfo();
		emp.setLoginId(empOraginal.getLoginId());
		emp.setLoginPwd(pwdmm);
		EmpInfo emp2=empService.loginByIDAndPwd(emp);
		if(emp2!=null) {
			/* 记住密码操作 */
			rememberOpration(remember,empOraginal,resp);
			session.setMaxInactiveInterval(60*30);
			session.setAttribute("loginEmp", emp2);
			session.setAttribute("isLogin", "true");
			return "{\"msg\":\"success\"}";
			
		}else {
			return "{\"msg\":\"用户名或密码错误\"}";
			
		}
		
	}
	/* 记住密码操作 */
	private void rememberOpration(Boolean remember, EmpInfo emp, HttpServletResponse resp) {
		if (remember) {
			Cookie cookieLoginId = new Cookie("rememberLoginId", emp.getLoginId()+"");
			Cookie cookieLoginPwd = new Cookie("rememberLoginPwd", emp.getLoginPwd());
			cookieLoginId.setMaxAge(3600*24*3);
			cookieLoginPwd.setMaxAge(3600*24*3);
			resp.addCookie(cookieLoginId);
			resp.addCookie(cookieLoginPwd);
		}else {
			Cookie cookieLoginId = new Cookie("rememberLoginId", emp.getLoginId()+"");
			Cookie cookieLoginPwd = new Cookie("rememberLoginPwd", emp.getLoginPwd());
			cookieLoginId.setMaxAge(0);
			cookieLoginPwd.setMaxAge(0);
			resp.addCookie(cookieLoginId);
			resp.addCookie(cookieLoginPwd);
		}
		
	}

	/* 获取记住的用户名和密码  若没有记住则返回为记住标志 */
	@RequestMapping("/getLoginIdAndPwd.action")
	@ResponseBody
	public Map<String,Object> getLoginIdAndPwd(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		Integer rememberLoginId = null ;
		String rememberLoginPwd = null;
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("rememberLoginId".equals(cookie.getName())) {
					rememberLoginId = Integer.parseInt(cookie.getValue());
				}
				if ("rememberLoginPwd".equals(cookie.getName())) {
					rememberLoginPwd = cookie.getValue();
				}
			}
		}
		
		Map<String,Object> map = new HashMap<>();
		if (rememberLoginId != null) {
			EmpInfo empInfo = new EmpInfo();
			empInfo.setLoginId(rememberLoginId);
			empInfo.setLoginPwd(rememberLoginPwd);
			map.put("loginEmp", empInfo);
			map.put("rememberFlag", true);
			return map;
		}
		map.put("rememberFlag", false);
		return map;
	}
	
	
	//员工修改密码
	@RequestMapping("/updateEmpPass.action")
	@ResponseBody
	public String updateEmpPass(Integer loginId,String loginPwd,String newPass) {
		Boolean flag=empService.updatePassword(loginId, loginPwd, newPass);		
		if(flag) {
			return "{\"msg\":\"success\"}";
		}else {
			return "{\"msg\":\"fail\"}";
		}
	}
	/* 退出方法  注意没有返回值  未测试*/
	@RequestMapping("/empEixt.action")
	@ResponseBody
	public void empEixt(HttpSession session){
		/* 清理当前存取的地方  */
		session.setAttribute("isLogin","false");
		session.setAttribute("loginEmp",null);

	}
	

	/* 获取登录员工对象  */
	@ResponseBody
	@RequestMapping("/getLoginEmp.action")
	public Map<String, Object> getLoginEmp(HttpSession session) {
		EmpInfo empInfo = (EmpInfo)session.getAttribute("loginEmp");
		Map<String, Object> map = new HashMap<>();
		map.put("loginEmp", empInfo);
		return map;
	}
	
	
}
