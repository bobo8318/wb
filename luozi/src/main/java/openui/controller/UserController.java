package openui.controller;

import com.alibaba.fastjson.JSON;
import openui.bean.OpenAdmin;
import openui.service.CustomUserService;
import openui.service.imply.CustomUserServiceImply;
import openui.tools.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CustomUserService userService;

    @GetMapping("/list")
    public String userList(Model model){
        List<OpenAdmin> datas = userService.listUser();
        model.addAttribute("users",datas);
        return "user_list";
    }

    @PostMapping("/add")
    public String userAdd(Model model, HttpServletRequest request){
        OpenAdmin admin = new OpenAdmin();
        admin.setUsername(request.getParameter("username"));
        admin.setPassword(request.getParameter("password"));
        admin.setRole(request.getParameter("role"));
        admin.setAdddate(DateTools.getFormatDate(new Date(),null));

        logger.info("new user:"+JSON.toJSONString(admin));
        userService.addUser(admin);

        model.addAttribute("result","success");
        return "user_add";
    }

    @GetMapping("/add")
    public String toUserAdd(){
        return "user_add";
    }

    @PostMapping("/update")
    public String userUpdate(Model model,HttpServletRequest request){
        OpenAdmin admin = new OpenAdmin();
        admin.setId(Integer.valueOf(request.getParameter("id")));
        admin.setUsername(request.getParameter("username"));
        admin.setPassword(request.getParameter("password"));
        admin.setRole(request.getParameter("role"));
        admin.setAdddate(DateTools.getFormatDate(new Date(),null));
        userService.updateUser(admin);

        model.addAttribute("result","success");
        model.addAttribute("user",admin);
        return "user_update";
    }

    @GetMapping("/update/{id}")
    public String toUserUpdate(@PathVariable int id, HttpSession session, Model model){
        //String username = String.valueOf(session.getAttribute("name"));
        //if(!"null".equals(username)&&username!=null&&!"".equals(username)){
            //logger.info("update: logined:"+username);
            //userService.findUserByName(username);
            OpenAdmin user = userService.findUserById(id);
            model.addAttribute("user",user);
            return "user_update";
        //}else{
           // logger.info("update: no login");
            //return "login";
      //  }

    }

    @ResponseBody
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id){
        userService.removeUser(id);
        return "success";
    }
}
