package openui.dao;

import openui.bean.OpenAdmin;
import openui.mapper.AdminMapper;
import openui.service.imply.CustomUserServiceImply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);
    @Autowired
    AdminMapper mapper;

    public OpenAdmin findByUserName(String username){
        return mapper.findByUserName(username);
    }

    public OpenAdmin login(String username, String password){
        logger.info(username,password);
        return mapper.getAdminByLogin(username, password);
    }

    public List<OpenAdmin> listAdmin(){
        return mapper.listUser();
    }

    public OpenAdmin getUserById(int id) {
        return mapper.getUserById(id);
    }

    public void removeUserById(int id) {
        mapper.removeUserById(id);
    }

    public void addUser(OpenAdmin user) {
        mapper.addNewUser(user);
    }

    public void updatePassword(OpenAdmin user) {
        mapper.updateUserPassword(user);
    }

    public void updateRole(OpenAdmin user) {
        mapper.updateUserRole(user);
    }
}
