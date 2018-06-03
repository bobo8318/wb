package cn.openui.qb01.Controller;

import cn.openui.qb01.entity.User;
import cn.openui.qb01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/hello")
    public List<User> listAll() {
        return userService.listAll();
    }
}
