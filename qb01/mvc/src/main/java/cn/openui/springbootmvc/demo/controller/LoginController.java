package cn.openui.springbootmvc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.TextUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("login")
    public String  login(String username, String password,Model model, HttpSession session){
        //System.out.println(username+"---------------------------");
        LOGGER.info(username);
        model.addAttribute("username",username);
        return "main";
    }
}
