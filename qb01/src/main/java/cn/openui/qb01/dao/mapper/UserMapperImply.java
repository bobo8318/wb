package cn.openui.qb01.dao.mapper;

import cn.openui.qb01.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public class UserMapperImply implements UserMapper {
    @Override
    public List<User> selectAll() {
        return null;
    }
}
