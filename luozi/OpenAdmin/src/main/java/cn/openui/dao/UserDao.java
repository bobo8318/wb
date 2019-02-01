package cn.openui.dao;

import cn.openui.mapper.UserMapper;
import cn.openui.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    UserMapper mapper;

    public AdminUser getUserByLogin(String username, String pwd){
        return mapper.getAdminByLogin(username, pwd);
    }

}
