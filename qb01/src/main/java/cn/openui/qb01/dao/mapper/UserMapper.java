package cn.openui.qb01.dao.mapper;

import cn.openui.qb01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();
}
