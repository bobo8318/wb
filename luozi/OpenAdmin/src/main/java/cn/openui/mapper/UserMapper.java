package cn.openui.mapper;

import cn.openui.model.AdminUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select username,password,role from AdminUseradmin_user where username=#{username} and password=#{password}")

    public AdminUser getAdminByLogin(@Param("username") String username, @Param("password") String password);

    @Select("select id,username,password,role from AdminUser where username=#{username}")
    AdminUser findByUserName(@Param("username") String username);

    @Select("select id,username,password,role,logintime,loginip \n" +
            "from (select t1.id,t1.username,password,role,logintime,loginip from admin_user t1  left join loginlog t2 on t1.username=t2.username order by logintime desc ) t3 \n" +
            "group by username order by id")
    List<AdminUser> listUser();

    @Select("select id,username,password,role from admin_user where id=#{id}")
    AdminUser getUserById(@Param("id") int id);

    @Delete("delete from openadmin where id=#{id}")
    void removeUserById(@Param("id") int id);

    @Insert("insert into admin_user(username,password,role,adddate) values(#{user.username},#{user.password},#{user.role},#{user.adddate})")
    void addNewUser(@Param("user") AdminUser user);

    @Update("update admin_user set role=#{user.role} where id=#{user.id}")
    void updateUserRole(@Param("user") AdminUser user);

    @Update("update admin_user set password=#{user.password} where id=#{user.id}")
    void updateUserPassword(@Param("user") AdminUser user);
}
