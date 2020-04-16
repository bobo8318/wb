package pub.imba.controller;

import com.google.common.collect.ImmutableMap;
import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.ProdItem;
import com.luo.erlei.comm.out.Ret;
import com.luo.erlei.comm.out.ShopItem;
import com.luo.erlei.comm.service.QueryService;
import com.sun.xml.internal.txw2.output.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pub.imba.feignInterface.UserFeignInterface;
import pub.imba.model.MyWatchPo;
import pub.imba.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
//@AutoConfigureAfter(UserFeignInterface.class)
public class UserController {

    @Autowired
    private RestTemplate template;
    @Autowired
    private DiscoveryClient discoveryClient;
    //@Autowired

    @Autowired
    private UserFeignInterface userFeignInterface;

    @Autowired
    private ResultConverter converter;

    @Resource(name="queryfeign")
    private QueryService queryService;

    @RequestMapping("/index")
    public String tologin(){
        /*List<ServiceInstance> serices = discoveryClient.getInstances("user-service");
        ServiceInstance instance = serices.get(0);
        String url = "http://" + instance.getHost() +":"+instance.getPort() + "/user/"  + id;
        return template.getForObject(url, MyUser.class);*/
        return "login";
    }

    /*@ResponseBody
    @PostMapping("/getCoder")
    public ResultEntry getCode(@RequestBody Map<String, String> params, HttpSession session){
        if(params.containsKey("phoneno")){
           ResultEntry entry = userFeignInterface.getCode(params.get("phoneno"));
            //ResultEntry entry = ResultEntryFactory.getInstance();
            //entry.setData("123456");
            //entry.setStatus( ResultEntry.SUCCESS);
            if(entry.getStatus() == ResultEntry.SUCCESS){
                // 成功 暂存code用于验证
                //session.setAttribute(params.get("phoneno"),entry.getData().toString());
                SecurityUtils.getSubject().getSession().setAttribute("coder",entry.getData().toString());
                return ResultEntryFactory.getSuccessEntry("success");
            }else{
                return ResultEntryFactory.getSMSErrorEntry(entry.getMsg());
            }
        }else{
            return ResultEntryFactory.getSMSErrorEntry("wrong phone");
        }

    }*/

    @ResponseBody
    @PostMapping("/doLogin")
    public ResultEntry doLogin(@RequestBody Map<String, String> params){
        if(params.containsKey("code") && params.containsKey("phoneno") ){

            String code = params.get("code");
            String phoneno = params.get("phoneno");

            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(phoneno, code);
            try {
                currentUser.login(token);
                ResultEntry logininfo = userFeignInterface.addLoginInfo(ImmutableMap.of("phoneno", phoneno) );
                // 记录用户数据
                if(logininfo.ok()){

                    String userid = logininfo.getParam0();

                    String res_token = TextUtil.getRandomCoder(10);

                    SecurityUtils.getSubject().getSession().setAttribute("phoneno", phoneno);
                    SecurityUtils.getSubject().getSession().setAttribute("token", res_token);
                    SecurityUtils.getSubject().getSession().setAttribute("userid", userid);

                    ResultEntry result = ResultEntryFactory.getSuccessEntry("login success");
                    result.setParam0(phoneno);
                    result.setParam1(res_token);
                    return result;
                }else{
                    return ResultEntryFactory.getErrorEntry("add login error");
                }

            } catch (AuthenticationException e) {
                //return ResultEntryFactory.getTimeOutEntry("check authen fail");
            }
            return ResultEntryFactory.getTimeOutEntry("login faile");
        }else{
            return ResultEntryFactory.getParamInvalidate("wrong param");
        }

    }

    /**
     * 我的关注
     * @return
     */
    @ResponseBody
    @PostMapping(value="/prod/watchs")
    public Ret<List<ProdItem>> proWatchList(@RequestBody InParamProd p){

        if(SecurityUtils.getSubject().getSession().getAttribute("userid") != null) {
            String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userid");
            ResultEntry<List<MyWatchPo>> watches_ret = this.userFeignInterface.getProdWatchList(userid);
            if (watches_ret.ok() && watches_ret.getData() != null && !watches_ret.getData().isEmpty()) {
                List<MyWatchPo> watchs = watches_ret.getData();
                if (watchs != null && !watchs.isEmpty()) {
                    String[] watchs_id = new String[watchs.size()];
                    for (int i = 0; i < watchs_id.length; i++) {
                        watchs_id[i] = watchs.get(i).getProid();
                    }
                    p.ids = watchs_id;
                    p.page = 1;
                    p.size = watchs_id.length;

                    Ret<List<ProdItem>> prodlist = this.queryService.prodList(p);
                    if (prodlist.ok()) {
                        if (prodlist.d != null && !prodlist.d.isEmpty()) {
                            for (ProdItem item : prodlist.d) {
                                item.fav = true;
                            }
                        }
                    }

                    return prodlist;
                }

                return Ret.getNewFailure("no favs");

            }
            return Ret.getNewFailure(watches_ret.getMsg());
        }else {
            return Ret.getNewFailure("please login");
        }
    }

    /**
     * 修改关注与否
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value="/prod/watch")
    public  Ret changeProWatch(@RequestBody MyWatchPo watchPo){
        //String userid = session.getAttribute("userid").toString();
        if(watchPo != null && SecurityUtils.getSubject().getSession().getAttribute("userid") != null){
            String userid = SecurityUtils.getSubject().getSession().getAttribute("userid").toString();
            watchPo.setUserid(userid);

            LocalDate today = LocalDate.now();
            watchPo.setWatchdate(today.toString());

            ResultEntry res = this.userFeignInterface.watchProduct(watchPo);
            if(res.getStatus() == 200){
                return Ret.getNewRet(0);
            }


        }

        return Ret.getNewFailure("error!");
    }


    /**
     * 修改关注与否
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value="/shop/watch")
    public Ret changeShopWatch(@RequestBody MyWatchPo watchPo, HttpSession session){
        //String userid = session.getAttribute("userid").toString();
        if(watchPo != null  && SecurityUtils.getSubject().getSession().getAttribute("userid") != null){
            String userid = SecurityUtils.getSubject().getSession().getAttribute("userid").toString();
            watchPo.setUserid(userid);
            watchPo.setWatchdate(DateTools.getDate(new Date(),""));
            ResultEntry res = this.userFeignInterface.watchShop(watchPo);
            if(res.getStatus() == 200){
                return Ret.getNewRet(0);
            }
        }

        return Ret.getNewFailure("wrong!");
    }

    /**
     * 我的关注
     * @return
     */
    @ResponseBody
    @PostMapping(value="/shop/watchs")
    public Ret shopWatchList(@RequestBody InParam param){
        if(SecurityUtils.getSubject().getSession().getAttribute("userid") != null) {
            String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userid");
            ResultEntry<List<MyWatchPo>> watchs_ret = this.userFeignInterface.getShopWatchList(userid);
            if (watchs_ret.ok() && watchs_ret.getData() != null && !watchs_ret.getData().isEmpty()) {
                List<MyWatchPo> watchs = watchs_ret.getData();
                if (watchs != null && !watchs.isEmpty()) {
                    String[] ids = new String[watchs.size()];
                    for (int i = 0; i < ids.length; i++) {
                        ids[i] = watchs.get(i).getShopid();
                    }

                    param.setIds(ids);
                    param.page = 1;
                    param.size = ids.length;
                    param.c1 = "";
                    param.c2 = "";
                    param.c3 = "";
                    param.q = "";
                    param.sort = 1;
                    param.lastId = "";
                    param.lastValue = 0;

                    //logger.info(JSONObject.toJSONString(param));
                    Ret<List<ShopItem>> shoplist = this.queryService.shopList(param);
                    if (shoplist.ok()) {
                        if (shoplist.d != null && !shoplist.d.isEmpty()) {
                            for (ShopItem item : shoplist.d) {
                                item.fav = true;
                            }
                        }
                    }
                    return shoplist;
                }
            }
            return Ret.getNewFailure(watchs_ret.getMsg());
        }else {
            return Ret.getNewFailure("please login");
        }

    }

    @GetMapping("/getCode")
    @ResponseBody
    public ResultEntry getCode(@RequestParam  String phoneno) {
        if(!TextUtil.isEmpty(phoneno)){
            ResultEntry entry = userFeignInterface.getCode(phoneno);
            //ResultEntry entry = ResultEntryFactory.getInstance();
            //entry.setData("123456");
            //entry.setStatus( ResultEntry.SUCCESS);
            if(entry.getStatus() == ResultEntry.SUCCESS){
                // 成功 暂存code用于验证
                //session.setAttribute(params.get("phoneno"),entry.getData().toString());
                SecurityUtils.getSubject().getSession().setAttribute("coder",entry.getParam0());
                return ResultEntryFactory.getSuccessEntry("success");
            }else{
                return ResultEntryFactory.getSMSErrorEntry(entry.getMsg());
            }
        }else{
            return ResultEntryFactory.getSMSErrorEntry("wrong phone");
        }
    }

    @GetMapping("/validateToken/{token}")
    @ResponseBody
    public ResultEntry validateToken(@PathVariable String token){
        String token_session = SecurityUtils.getSubject().getSession().getAttribute("token").toString();
        if(token.equals(token_session)){
            return ResultEntryFactory.getSuccessEntry("success");
        }else{
            return ResultEntryFactory.getTimeOutEntry("please login");
        }

    }

    @GetMapping("/exit")
    @ResponseBody
    public ResultEntry exit(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return  ResultEntryFactory.getSuccessEntry("exit");
    }

}
