package pub.imba.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.*;
import com.luo.erlei.comm.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pub.imba.feignInterface.DataFeignInterface;
import pub.imba.feignInterface.UserFeignInterface;
import pub.imba.model.MyProdItem;
import pub.imba.model.MyRet;
import pub.imba.model.MyWatchPo;
import pub.imba.util.DateTools;
import pub.imba.util.ResultEntry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataApiController {

    private  final static Logger logger = LoggerFactory.getLogger(DataApiController.class);

    @Autowired
    private RestTemplate template;


    @Autowired
    UserFeignInterface userFeignInterface;

    @Resource(name="queryfeign")
    private QueryService queryService;

    /**
     * 我的关注
     * @return
     */
    @PostMapping(value="/prod/watchs")
    public Ret<List<ProdItem>> proWatchList(@RequestBody InParamProd p){
        String userid = "123";
        ResultEntry<List<MyWatchPo>> watches_ret = this.userFeignInterface.getProdWatchList(userid);
        if(watches_ret.ok() && watches_ret.getData() !=null && !watches_ret.getData().isEmpty()){
            List<MyWatchPo> watchs = watches_ret.getData();
            if(watchs !=null && !watchs.isEmpty()){
                String[] watchs_id =  new String[watchs.size()];
                for(int i=0;i<watchs_id.length;i++){
                    watchs_id[i] = watchs.get(i).getProid();
                }
                p.ids = watchs_id;
                p.page = 1;
                p.size = watchs_id.length;

                Ret<List<ProdItem>> prodlist = this.queryService.prodList(p);
                if(prodlist.ok()){
                    if(prodlist.d != null && !prodlist.d.isEmpty()){
                        for (ProdItem item: prodlist.d) {
                            item.fav = true;
                        }
                    }
                }

                return prodlist;
            }

            return Ret.getNewFailure("no favs");

        }
        return Ret.getNewFailure(watches_ret.getMsg());
    }

    /**
     * 修改关注与否
     * @param
     * @return
     */
    @PostMapping(value="/prod/watch")
    public  Ret changeProWatch(@RequestBody MyWatchPo watchPo, HttpSession session){
        //String userid = session.getAttribute("userid").toString();
        if(watchPo != null){
            String userid = "123";
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
    @PostMapping(value="/shop/watch")
    public Ret changeShopWatch(@RequestBody MyWatchPo watchPo, HttpSession session){
        //String userid = session.getAttribute("userid").toString();
        if(watchPo != null){
            String userid = "123";
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
    @PostMapping(value="/shop/watchs")
    public Ret shopWatchList(@RequestBody InParam param){
        ResultEntry<List<MyWatchPo>> watchs_ret = this.userFeignInterface.getShopWatchList("123");
        if(watchs_ret.ok() && watchs_ret.getData() !=null && !watchs_ret.getData().isEmpty()){
            List<MyWatchPo> watchs = watchs_ret.getData();
            if(watchs!=null && !watchs.isEmpty()){
                String[] ids = new String[watchs.size()];
                for(int i=0;i<ids.length;i++){
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
                if(shoplist.ok()){
                    if(shoplist.d != null && !shoplist.d.isEmpty()){
                        for (ShopItem item: shoplist.d) {
                            item.fav = true;
                        }
                    }
                }
                return shoplist;
            }
        }
        return Ret.getNewFailure(watchs_ret.getMsg());
    }

}
