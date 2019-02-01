package openui.service.imply;

import openui.bean.OpenAdmin;
import openui.bean.Permission;
import openui.dao.AdminDao;
import openui.dao.PermissionDao;
import openui.service.CustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserServiceImply implements CustomUserService,UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserServiceImply.class);

    @Autowired
    AdminDao adminDao;
    //@Autowired
    //PermissionDao permissionDao;

    /**
     * 根据用户名加载用户信息(包含权限信息)
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username) {

        OpenAdmin admin = adminDao.findByUserName(username);
        if (admin != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(""+admin.getRole());
            grantedAuthorities.add(grantedAuthority);
            admin.setGrantedAuthorities(grantedAuthorities);
            /*List<Permission> permissions = permissionDao.findByAdminUserId(admin.getId());
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }*/
            logger.info(admin.getRole());
            return admin;
            //return new OpenAdmin(admin.getUsername(), admin.getPassword(), grantedAuthorities);
        } else {
            logger.info("admin: " + username + " do not exist!");
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

    @Override
    public List<OpenAdmin> listUser() {
        return adminDao.listAdmin();
    }

    @Override
    public void addUser(OpenAdmin user) {
        adminDao.addUser(user);
    }

    @Override
    public void updateUser(OpenAdmin user) {
        if(user.getPassword()!=null&&!"".equals(user.getPassword())){
            adminDao.updatePassword(user);
        }if(user.getRole()!=null&&!"".equals(user.getRole())){
            adminDao.updateRole(user);
        }
    }

    @Override
    public void removeUser(int id) {
        adminDao.removeUserById(id);
    }

    @Override
    public OpenAdmin findUserById(int id) {
        return adminDao.getUserById(id);
    }

    @Override
    public OpenAdmin findUserByName(String username) {
        return adminDao.findByUserName(username);
    }
}
