package openui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class MySecurityProvider extends DaoAuthenticationProvider {

    public static final Logger logger = LoggerFactory.getLogger(MySecurityProvider.class);


    public MySecurityProvider(UserDetailsService userDetailsService){
        setUserDetailsService(userDetailsService);

    }

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {

        String name = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = this.getUserDetailsService().loadUserByUsername(name);
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(!userDetails.getPassword().equals(password)){
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        //构建返回用户登录成功的token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
        logger.info(" MySecurityProvider "+name+":"+password+":"+token);
        return token;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
