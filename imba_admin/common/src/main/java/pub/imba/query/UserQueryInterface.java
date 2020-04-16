package pub.imba.query;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pub.imba.model.MyWatchPo;
import pub.imba.util.ResultEntry;

import java.util.List;
import java.util.Map;

public interface UserQueryInterface {

    @ApiOperation(value = "获取手机验证码", notes="向手机发送验证码并返回")
    @GetMapping("/getCode")
    ResultEntry getCode(@ApiParam(value = "手机号码",required = true,example = "13****") @RequestParam("phoneno") String  phoneno);


    /**
     * 根据用户id获取收藏商品列表
     */
    @ApiOperation(value = "获取用户收藏商品列表", notes="获取用户收藏商品列表")
    @GetMapping("/getWatchProList")
    ResultEntry<List<MyWatchPo>> getProdWatchList(@ApiParam(value = "用户id", required = true) @RequestParam("userid")  String userid);

    /**
     * 根据用户id获取收藏商品列表
     */
    @ApiOperation(value = "获取用户收藏商品列表", notes="获取用户收藏商品列表")
    @GetMapping("/getWatchShopList")
    public ResultEntry<List<MyWatchPo>> getShopWatchList(@ApiParam(value = "用户id", required = true) @RequestParam("userid")  String userid);

    /**
     * 收藏 商品
     */
    @ApiOperation(value = "收藏商品id", notes="收藏商品")
    @PostMapping("/pro/watch")
    ResultEntry watchProduct(@ApiParam("请求参数")  @RequestBody  MyWatchPo watch);

    /**
     * 收藏 商铺
     */
    @ApiOperation(value = "收藏商铺id", notes="收藏商铺")
    @PostMapping("/shop/watch")
    ResultEntry watchShop(@ApiParam("请求参数") @RequestBody  MyWatchPo watchPo);


    /**
     * consumer 登录成功 记录用户登录信息 新用户 记录到表中
     * @return
     */
    @ApiOperation(value = "记录用户登录信息", notes="记录用户登录信息")
    @PostMapping("/addLoginInfo")
    ResultEntry addLoginInfo(@ApiParam("登录参数") @RequestBody Map<String, String> params);


    /**
     * test api
     * 接口测试
     */
    @ApiOperation(value = "接口测试", notes="接口测试")
    @RequestMapping("/test")
    ResultEntry test(@ApiParam(value = "测试数据")@RequestParam(value="param") String param);
}
