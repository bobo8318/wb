package openui.controller;

import openui.bean.OpenAdmin;
import openui.service.LogSevice;
import openui.service.imply.CustomUserServiceImply;
import openui.tools.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
//@PreAuthorize("hasRole('USER')")
public class LoginController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LogSevice sevice;
   //CustomUserServiceImply serviceImply;

    @PostMapping("/admin/loin")
    public String login(){
        logger.info("login action");
        return "main";
    }

    @RequestMapping(value="/manage",method = {RequestMethod.POST,RequestMethod.GET})
   @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String manage(){
        logger.info("login success jump to manange page");
        return "main";
    }

    @RequestMapping(value="/index")
    public String index(){
        return "login";
    }


    @RequestMapping(value="/whoim")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String whoIm() {
       return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @RequestMapping("/error/")
    public String accessDeny(){
        return "403";
    }

    @RequestMapping("/exit")
    public String logout(HttpSession session){

        String logid = String.valueOf(session.getAttribute("logid"));
        logger.info("log out:"+logid);

        if(!TextUtil.isEmpty(logid)){
            sevice.LogOut(Integer.valueOf(logid));

        }
        SecurityContextHolder.clearContext();
        session.removeAttribute("name");
        return "login";
    }

}
