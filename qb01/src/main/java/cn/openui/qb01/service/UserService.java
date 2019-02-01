package cn.openui.qb01.service;

import cn.openui.qb01.dao.UserDao;
import cn.openui.qb01.dao.mapper.UserMapper;
import cn.openui.qb01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserMapper mapper;

    @Override
    public Integer userInsert(User user) {
        return null;
    }

    @Override
    public List<User> queryById(Integer id) {
        return mapper.selectAll();
    }
}
