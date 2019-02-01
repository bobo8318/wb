package org.ga.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.ga.po.Admin;
import org.ga.service.imply.MainServiceImply;
import org.hao.tools.CommonTools;
import org.hao.tools.SQLHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

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
	 public String index(HttpServletRequest request ,HttpServletResponse response) throws IOException {
	               //response,request会自动传进来
		 //response.getWriter().write("123123");
		 //System.out.println("im coming");
		// return  null;
		 //pinyin test
		//System.out.println(CommonTools.getPinYinFirst("中文 测试"));
		 SQLHelper helper = new SQLHelper();
		 System.out.println(helper.tableNNameRouter("11",3));
		 System.out.println(helper.tableNNameRouter("21",3));
		 System.out.println(helper.tableNNameRouter("31",3));
		 System.out.println(helper.tableNNameRouter("41",3));

		 return "index";
	    }  
	 @RequestMapping(value="/in",method=RequestMethod.POST)  
	 public String Login(HttpServletRequest request ,HttpServletResponse response,Admin admin){
	               //response,request会自动传进来
		 System.out.println(admin.getLoginPwd());
		 if( "".equals(admin.getLoginName()) || "".equals(admin.getLoginPwd())){
			 return "index";
		 }else{
		 	Admin result = mainService.login(admin.getLoginName(), admin.getLoginPwd());
		 	String ip = request.getRemoteAddr();
		 	if(result!=null){
		 		request.getSession().setAttribute("admin", result);
		 		return "main";
		 	}
		 	else{
		 		return "index";
		 	}
		 }
	 }

	 @RequestMapping("ui")
    public String uiTest(HttpServletRequest request ,HttpServletResponse response){

         return "login";
     }
     @RequestMapping("json")
	public void getJsonData(HttpServletRequest request ,HttpServletResponse response) throws IOException {

		String page =  request.getParameter("page");
		 String rows =  request.getParameter("rows");

		 String jsonString = JSON.toJSONString("");

		  response.getWriter().write(jsonString);
		  response.getWriter().close();
	 }

}
