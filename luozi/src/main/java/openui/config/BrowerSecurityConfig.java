package openui.config;

import openui.handler.AccessDeniedServletHandler;
import openui.handler.MyAuthenctiationFailureHandler;
import openui.handler.MyAuthenctiationSuccessHandler;
import openui.service.imply.CustomUserServiceImply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{

    private static final Logger logger = LoggerFactory.getLogger(BrowerSecurityConfig.class);


    //@Autowired
    //private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    CustomUserServiceImply customUserService;

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenctiationFailureHandler myAuthenticationFailureHandler;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        return new MySecurityProvider(customUserService);
    }

    @Bean
    AccessDeniedServletHandler accessDeniedServletHandler(){
        return new AccessDeniedServletHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER"); //添加一个授权用户 身份为User
        auth.userDetailsService(customUserService); //user Details Service验证
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("HttpSecurity http init");

               http
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/index","/whoim").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .permitAll()
                .and()
                .formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/index").loginProcessingUrl("/admin/login").failureUrl("/index?error")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)          // 设置登录页面
                .and()
                .csrf().disable();          // 关闭csrf防护

        /*http.authorizeRequests()
                .anyRequest().authenticated()   // 任何请求都拦截
                .and()
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/admin/login")  // 自定义的登录接口
                .failureUrl("/index?error")
                .permitAll()        // 登陆后可访问任意页面
                .and()
                .logout().permitAll();  // 注销后任意访问*/
       /* http.authorizeRequests().anyRequest().permitAll()//所有都放行
              .and()
                .formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/index").loginProcessingUrl("/admin/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)          // 设置登录页面
                .and()
                .csrf().disable();          // 关闭csrf防护
        //http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);*/
    }
}
