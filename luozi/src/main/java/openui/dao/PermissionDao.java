package openui.dao;

import openui.bean.Permission;
import openui.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class  PermissionDao {

    @Autowired
    PermissionMapper mapper;

    public List<Permission> findAll(){
        return mapper.findAll();
    };
    public List<Permission> findByAdminUserId(int userId){
        return mapper.findByAdminUserId(userId);
    };
}
