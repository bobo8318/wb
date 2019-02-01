package openui.handler;

import openui.bean.LoginLog;
import openui.dao.LogDao;
import openui.tools.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private LogDao dao;


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {



        //response.setContentType("application/json;charset=UTF-8");
        //response.getWriter().write(objectMapper.writeValueAsString(authentication));
        //request.getRequestDispatcher("/whoim").forward(request, response);//跳转

        LoginLog log = new LoginLog();
        log.setLogintime(DateTools.getFormatDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
        log.setUsername(authentication.getName());
        log.setLoginip(request.getRemoteAddr());

        int id = dao.addLog(log);

        request.getSession().setAttribute("name",authentication.getName());
        request.getSession().setAttribute("logid",id);

        logger.info("登录成功"+id +"-"+authentication.getName());

        this.redirectStrategy.sendRedirect(request, response, request.getContextPath() +"/manage");
    }
}
