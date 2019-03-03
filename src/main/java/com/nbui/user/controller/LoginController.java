package com.nbui.user.controller;

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

import com.nbui.entity.LoginInfo;
import com.nbui.user.service.ILoginService;
import com.nbui.user.service.IUserService;
import com.nbui.util.MD5Utils;

@Controller
public class LoginController {
	@Autowired
	ILoginService loginService;
	@Autowired
	IUserService IUserService;
	//前台用户账号密码登陆
	@RequestMapping("/loginByName.action")
	@ResponseBody
	public String loginByName(LoginInfo login,HttpServletResponse resp,HttpServletRequest req){
		String pwdmm=MD5Utils.MD5Encode(login.getLoginPwd(), "utf-8");
		//System.out.println(pwdmm);
		login.setLoginPwd(pwdmm);
		LoginInfo login2=loginService.loginByNamePwd(login);
		if(login2!=null) {
			System.out.println(login2.getLoginStatus());
			if(login2.getLoginStatus().equals("离线")) {
			  login2.setLoginStatus("在线");
			  loginService.updateLoginState(login2.getLoginId(), "在线");
			  Cookie cook=new Cookie("loginName", login2.getLoginName());
			  cook.setMaxAge(3600*24);
			  resp.addCookie(cook);	
			  HttpSession session=req.getSession();
			  session.setAttribute("loginInfo", login2);
			 // System.out.println(session.getAttribute("loginInfo"));
			  return "{\"msg\":\"success\"}";
			}else {
			    return "{\"msg\":\"having\"}";
		     }
			
		}else {
			return "{\"msg\":\"fail\"}";
		}	
	}
	//前台用户短信登陆
	@RequestMapping("/loginByPhone.action")
	public String loginByPhone(String phone,String pwd,HttpServletResponse resp,HttpServletRequest req){
		String pwdm=MD5Utils.MD5Encode("123456", "utf-8");
		LoginInfo login2=loginService.loginByPhone(phone, pwdm);
		if(login2!=null) {
			System.out.println(login2.getLoginId().toString());
			Cookie cook=new Cookie("loginName", login2.getLoginName());
			       cook.setMaxAge(3600*24);
			resp.addCookie(cook);
			HttpSession session=req.getSession();
			session.setAttribute("loginInfo", login2);
			//System.out.println(session.getAttribute("loginInfo"));
			return "redirect:/foreground/foreground-index.html";
		}else {
			return "redirect:/foreground/foreground-login.html";
		}			
	}
	
	//前台用户注册
	@RequestMapping("/userRegister.action")
	@ResponseBody
	public String userRegister(LoginInfo login){
		String pwdmm=MD5Utils.MD5Encode(login.getLoginPwd(), "utf-8");
		login.setLoginPwd(pwdmm);
		Integer s=loginService.addLoginInfo(login);
		Integer id=login.getLoginId();	
		if(s>0) {
			System.out.println(id+"@@@@@@@@");
			@SuppressWarnings("unused")
            Integer s2=IUserService.addUserInfo(id);
			return "{\"msg\":\"success\"}";
		}
		else {
			return "{\"msg\":\"fail\"}";
		}
			
	}
	//检查用户是否已注册过
	@RequestMapping("/checkPhone.action")
	@ResponseBody
	public String checkPhone(String phone){
		LoginInfo login=loginService.findLoginByPhone(phone);
		if(login!=null) {
			return "{\"msg\":\"success\"}";
		}
		else {
			return "{\"msg\":\"fail\"}";
		}
			
	}
	
	//退出登陆方法
	@RequestMapping("/outLogin.action")
	public String outLogin(HttpServletRequest req,LoginInfo login) {
		 HttpSession session=req.getSession();
		if(login.getLoginName()==null) {
			if(session.getAttribute("loginInfo")==null) {
				return 	"forward:/foreground/foreground-login.html";
			}else {
			login=(LoginInfo)session.getAttribute("loginInfo");
			LoginInfo login2=loginService.loginByNamePwd(login);	
			Integer loginId=login2.getLoginId();
			loginService.updateLoginState(loginId, "离线");		
			session.removeAttribute("loginInfo");
			return 	"forward:/foreground/foreground-login.html";	}
		}else {
		//System.err.println(login.getLoginName()+login.getLoginPwd());
		String pwdmm=MD5Utils.MD5Encode(login.getLoginPwd(), "utf-8");
		login.setLoginPwd(pwdmm);
		LoginInfo login2=loginService.loginByNamePwd(login);	
		Integer loginId=login2.getLoginId();
		loginService.updateLoginState(loginId, "离线");
		session.removeAttribute("loginInfo");
		return 	"forward:/foreground/foreground-login.html";	
		}
	}
	
	//获取cookie里面登陆名方法
	@RequestMapping("/getLoginName.action")
	@ResponseBody
	public Map<Object, Object> name(HttpServletRequest req) {
		Cookie[] cookies=req.getCookies();
		Map<Object, Object> map=new HashMap<>();
		 for(Cookie ck :cookies) {
			if(ck.getName().equals("loginName")) {
				map.put("loginName", ck.getValue());
				//System.err.println(ck.getValue()+"========");
			}
		}
		 return map;	
	}
	
	//获取session里面登陆用户方法
	@RequestMapping("/getLoginInfo.action")
	@ResponseBody
	public Map<String, Object> getLoginEmp(HttpSession session) {
		LoginInfo login = (LoginInfo)session.getAttribute("loginInfo");
		System.out.println(login);
		Map<String, Object> map = new HashMap<>();
		map.put("loginInfo", login);
		return map;
	}
	//获取session里面登陆用户Id的方法
		@ResponseBody
		@RequestMapping("/getUserId.action")
		public Integer getUserId(HttpSession session) {
			LoginInfo login = (LoginInfo)session.getAttribute("loginInfo");
			System.out.println(login.getLoginId());
			Integer UserId=IUserService.getUserId(login.getLoginId());
			//System.out.println(UserId);
			return UserId;
		}
	//修改登陆用户信息
	@RequestMapping("/updateLoginInfo.action")
	@ResponseBody
	public String updateLoginInfo(LoginInfo login,HttpSession session,String oldPwd) {
		LoginInfo oldLogin=(LoginInfo)session.getAttribute("loginInfo");
		String pwdmm=MD5Utils.MD5Encode(oldPwd, "utf-8");
		//System.out.println(pwdmm+oldLogin.getLoginPwd());
		if(!pwdmm.equals(oldLogin.getLoginPwd())) {
			return "{\"msg\":\"notSame\"}";
		}else {
			String pwdm=MD5Utils.MD5Encode(login.getLoginPwd(), "utf-8");
			login.setLoginPwd(pwdm);
			Integer s=loginService.updateLoginInfo(login);
			if(s>0) {
				return "{\"msg\":\"success\"}";
			}else {
				return "{\"msg\":\"fail\"}";
			}
		}
		
		
	}
}
