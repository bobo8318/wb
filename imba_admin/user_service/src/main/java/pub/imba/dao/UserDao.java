package pub.imba.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pub.imba.mapper.UserMapper;
import pub.imba.model.LoginLog;
import pub.imba.model.MyWatchPo;
import pub.imba.model.UserPo;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserMapper userMapper;

    public List<UserPo> test(){
        return userMapper.test();
    }

    public UserPo getUserByPhone(String phoneno) {
        return userMapper.getUserByPhone(phoneno);
    }

    public void addUser(UserPo newUser) {
        userMapper.addUser(newUser);
    }

    public void addLoginLog(LoginLog log) {
        userMapper.addLoginLog(log);
    }

    public List<MyWatchPo> getWatchProListByUser(String userid) {
        return userMapper.getWatchProListByUser(userid);
    }

    public List<MyWatchPo> getWatchShopListByUser(String userid) {
        return userMapper.getWatchShopListByUser(userid);
    }

    public void watchPro(MyWatchPo watch) {
        userMapper.watchPro(watch);
    }
    public void unWatchPro(MyWatchPo watch) {
        userMapper.unWatchPro(watch);
    }

    public void watchShop(MyWatchPo watchPo) {
        userMapper.watchShop(watchPo);
    }

    public void unWatchShop(MyWatchPo watchPo) {
        userMapper.unWatchShop(watchPo);
    }
}
