package cn.openui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "hello world";
    }

    @PostMapping("/api/login")
    public String login(String username,String password){
        return username+" : "+password + "is login";
    }
}
