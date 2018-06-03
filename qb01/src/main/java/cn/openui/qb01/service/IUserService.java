package cn.openui.qb01.service;

import cn.openui.qb01.entity.User;

public interface IUserService {
    Integer userInsert(User user);
    User queryById(Integer id);
}
