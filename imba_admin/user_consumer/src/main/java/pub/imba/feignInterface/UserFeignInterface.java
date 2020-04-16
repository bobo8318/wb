package pub.imba.feignInterface;

import com.luo.erlei.comm.out.Ret;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pub.imba.impl.UserFeignClientFallBack;
import pub.imba.model.MyUser;
import pub.imba.model.MyWatchPo;
import pub.imba.query.UserQueryInterface;
import pub.imba.util.ResultEntry;

import java.util.List;
import java.util.Map;

@FeignClient(name="user-service", fallback = UserFeignClientFallBack.class)
//@FeignClient(name="user-service")
@Component
public interface UserFeignInterface extends UserQueryInterface {

    /*@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    MyUser getUserById(@PathVariable("id") Long id);

    @RequestMapping(value = "/user/getCode", method = RequestMethod.POST)
    ResultEntry getSmsCode(@RequestBody Map map);

    @RequestMapping(value = "/user/addLoginInfo", method = RequestMethod.POST)
    ResultEntry addLoginInfo(@RequestBody Map map);

    @RequestMapping(value = "/user/getWatchProList",method = RequestMethod.GET)
    ResultEntry<List<MyWatchPo>> getProWatchListByUserId(@RequestParam("userid") String userid);

    @RequestMapping(value = "/user/getWatchShopList", method = RequestMethod.GET)
    ResultEntry<List<MyWatchPo>> getShopWatchListByUserId(@RequestParam("userid") String userid);

    @RequestMapping(value="/user/prod/watch", method = RequestMethod.POST)
    ResultEntry watchPro(@RequestBody  MyWatchPo watchPo);


    @Override
    @RequestMapping(value="/user/shop/watch", method = RequestMethod.POST)
    ResultEntry watchShop(@RequestBody  MyWatchPo watchPo);*/
}
