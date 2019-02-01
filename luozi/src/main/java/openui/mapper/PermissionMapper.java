package openui.mapper;

import openui.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("")
    List<Permission> findAll();

    @Select("")
    List<Permission> findByAdminUserId(int userId);
}
