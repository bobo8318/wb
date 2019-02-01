package org.ga.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ga.po.Admin;
import org.ga.service.imply.MainServiceImply;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 登录 
 * @author Administrator
 */
@Controller  
@RequestMapping("/login") 
@Scope("prototype")
public class LoginController{
	@Resource
	private MainServiceImply mainService;
	
	 @RequestMapping("/index")  
	 public String index(HttpServletRequest request ,HttpServletResponse response){  
	               //response,request会自动传进来  
		 
	        return "index.jsp";  
	    }  
	 @RequestMapping(value="/in",method=RequestMethod.POST)  
	 public String Login(HttpServletRequest request ,HttpServletResponse response,Admin admin){  
	               //response,request会自动传进来 
		 if(admin==null || "".equals(admin.getLoginName()) || "".equals(admin.getLoginPwd())){
			 return "index.jsp";
		 }else{
		 	Admin result = mainService.login(admin.getLoginName(), admin.getLoginPwd());
		 	String ip = request.getRemoteAddr();
		 	if(result!=null){
		 		request.getSession().setAttribute("admin", result);
		 		return "main.jsp";
		 	}
		 	else{
		 		return "index.jsp";
		 	}
		 }
	 }  
}
