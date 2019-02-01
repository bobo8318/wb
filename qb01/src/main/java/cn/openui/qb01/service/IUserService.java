package cn.openui.qb01.service;

import cn.openui.qb01.entity.User;

import java.util.List;

public interface IUserService {
    Integer userInsert(User user);
    List<User> queryById(Integer id);
}
