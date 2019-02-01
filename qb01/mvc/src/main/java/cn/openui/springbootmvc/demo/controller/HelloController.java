package cn.openui.springbootmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/bootstrap")
    public String bootstrap(){
        return "bootstrap";
    }

    @RequestMapping("success")
    public String success(Model model){
        model.addAttribute("name","summer");
        return "success";
    }

    @GetMapping("d3js")
    public String d3js(){
        return "d3js";
    }
}
