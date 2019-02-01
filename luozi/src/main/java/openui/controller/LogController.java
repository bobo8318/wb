package openui.controller;

import com.alibaba.fastjson.JSON;
import openui.bean.LoginLog;
import openui.bean.OpenAdmin;
import openui.dao.LogDao;
import openui.service.LogSevice;
import openui.tools.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);


    @Autowired
    private LogSevice service;

    @GetMapping("/list")
    public String list(HttpServletRequest request, HttpSession session, Model model){

        String role = "";
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails)
            role = ((OpenAdmin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRole();
        else
            role = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        String username = "";
        if(role.toLowerCase().equals("role_user")){
            username = session.getAttribute("name").toString();
        }else{
            username = request.getParameter("key");
            if(TextUtil.isEmpty(username)){
                username = "";
            }
        }


        List<LoginLog> datas = null;
        int currentpage = 0;
        int pagecount = 0;
        int datacount = 0;
        int pagesize = 10;
        int pageShowCount = 5;

        try {
            currentpage = Integer.valueOf(request.getParameter("page"));
        }catch (Exception e){
            currentpage = 1;
        }

        datas = service.listLogInfo(username,currentpage,pagesize,role);
        pagecount = service.getPageCount(username,pagesize,role);
        datacount = service.getLogCount(username,role);
        pagecount = pagecount<=0?1:pagecount;


        currentpage = currentpage<=0?1:currentpage;
        currentpage = currentpage>pagecount?pagecount:currentpage;

        int pagestart = 1;
        int pageEnd = pagecount;

        if(currentpage-pageShowCount/2>0){
            pagestart = currentpage-pageShowCount/2;
        }
        if(currentpage+pageShowCount/2<pagecount){
            pageEnd = currentpage+pageShowCount/2;
        }


        model.addAttribute("datas",datas);
        model.addAttribute("currentpage",currentpage);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("datacount",datacount);
        model.addAttribute("pagestart",pagestart);
        model.addAttribute("pageend",pageEnd);
        model.addAttribute("key",username);
        model.addAttribute("pagesize",pagesize);

        //logger.info(JSON.toJSONString(model));
        return "log_list";
    }
}
