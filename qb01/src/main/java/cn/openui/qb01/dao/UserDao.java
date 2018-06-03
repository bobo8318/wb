package cn.openui.qb01.dao;

import cn.openui.qb01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    Integer userInsert(@Param("user") User user);
    User queryById(@Param("id") Integer id);
}
