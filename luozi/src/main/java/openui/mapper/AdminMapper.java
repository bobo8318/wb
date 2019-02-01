package openui.mapper;

import openui.bean.OpenAdmin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select username,password,role from OpenAdmin where username=#{username} and password=#{password}")

    public OpenAdmin getAdminByLogin(@Param("username") String username,@Param("password") String password);

    @Select("select id,username,password,role from OpenAdmin where username=#{username}")
    OpenAdmin findByUserName(@Param("username") String username);

    @Select("select id,username,password,role,logintime,loginip \n" +
            "from (select t1.id,t1.username,password,role,logintime,loginip from openadmin t1  left join loginlog t2 on t1.username=t2.username order by logintime desc ) t3 \n" +
            "group by username order by id")
    List<OpenAdmin> listUser();

    @Select("select id,username,password,role from openadmin where id=#{id}")
    OpenAdmin getUserById(@Param("id") int id);

    @Delete("delete from openadmin where id=#{id}")
    void removeUserById(@Param("id") int id);

    @Insert("insert into openadmin(username,password,role,adddate) values(#{user.username},#{user.password},#{user.role},#{user.adddate})")
    void addNewUser(@Param("user") OpenAdmin user);

    @Update("update openadmin set role=#{user.role} where id=#{user.id}")
    void updateUserRole(@Param("user") OpenAdmin user);

    @Update("update openadmin set password=#{user.password} where id=#{user.id}")
    void updateUserPassword(@Param("user") OpenAdmin user);
}
