package openui.mapper;

import openui.bean.LoginLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select username,loginip,logintime,logouttime from " +
            "loginlog where username like #{namefilt} order by logintime desc " +
            "limit #{offset}, #{pagesize}")
    List<LoginLog> listLogByPage(@Param("namefilt") String namefilt, @Param("offset") int offset,@Param("pagesize") int pagesize);

    @Select("select count(*) from loginlog where username like #{namefilt} ")
    int getCount(@Param("namefilt") String namefilt);

    @Insert("insert into loginlog(loginip,logintime,username) values(#{log.loginip},#{log.logintime},#{log.username})")
    @Options(useGeneratedKeys = true, keyProperty = "log.id")
    void addLog(@Param("log") LoginLog log);

    @Update("update loginlog set logouttime=#{logoutdate} where id=#{logid}")
    void logout(@Param("logid") int logid,@Param("logoutdate") String formatDate);
}
