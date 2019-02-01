package cn.openui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by My on 2018/1/25.
 */
@RestController
public class BaseController {
    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    String index(){
        return "Hello Spring Boot! The BookName is ;and Book Author is ;and Book PinYin is ";
    }
}
