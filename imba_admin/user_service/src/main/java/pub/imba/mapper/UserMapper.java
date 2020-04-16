package pub.imba.mapper;

import org.apache.ibatis.annotations.*;
import pub.imba.model.CollectPro;
import pub.imba.model.LoginLog;
import pub.imba.model.MyWatchPo;
import pub.imba.model.UserPo;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from imba_user")
    List<UserPo> test();

    @Select("select * from imba_user where phoneno=#{phoneno}")
    UserPo getUserByPhone(@Param("phoneno") String phoneno);

    @Insert("insert into imba_user(uuid, phoneno) values(#{user.uuid}, #{user.phoneno});")
    void addUser(@Param("user") UserPo newUser);

    @Insert("insert into imba_loginlog(userid, logintime) values(#{loginlog.userid}, #{loginlog.logintime})")
    void addLoginLog(@Param("loginlog") LoginLog log);


    @Delete("delete from imba_watch_pro where userid=#{watch.userid} and proid = #{watch.proid}")
    void unWatchPro(@Param("watch") MyWatchPo watchPo);


    @Select("select id,proid,watchdate from imba_watch_pro where userid = #{userid};")
    List<MyWatchPo> getWatchProListByUser(@Param("userid") String userid);

    @Select("select id,shopid,watchdate from imba_watch_shop where userid = #{userid};")
    List<MyWatchPo> getWatchShopListByUser(@Param("userid") String userid);

    @Insert("insert into imba_watch_pro(userid, proid, watchdate) values(#{watch.userid}, #{watch.proid}, #{watch.watchdate})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void watchPro(@Param("watch") MyWatchPo watch);

    @Insert("insert into imba_watch_shop(userid, shopid, watchdate) values(#{watch.userid}, #{watch.shopid}, #{watch.watchdate})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void watchShop(@Param("watch") MyWatchPo watchPo);

    @Delete("delete from imba_watch_shop where userid=#{watch.userid} and shopid = #{watch.shopid}")
    void unWatchShop(@Param("watch") MyWatchPo watchPo);
}
