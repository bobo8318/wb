package pub.imba.controller;

import com.alibaba.fastjson.JSONObject;
import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.*;
import com.luo.erlei.comm.service.QueryService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pub.imba.feignInterface.QueryFeignInterface;
import pub.imba.feignInterface.UserFeignInterface;
import pub.imba.model.MyProdItem;
import pub.imba.model.MyShopItem;
import pub.imba.model.MyWatchPo;
import pub.imba.util.ResultEntry;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"data"})
public class DataQueryController implements QueryService {

    private  final static Logger logger = LoggerFactory.getLogger(DataQueryController.class);

    @Resource(name="queryfeign")
    private QueryService queryService;

    @Autowired
    UserFeignInterface userFeignInterface;


    @Override
    public Ret<Map<String, String>> cats(String p) {


            return this.queryService.cats(p);
    }

    @Override
    public Ret<Brief> brief() {
        return queryService.brief();
    }

    @Override
    public Ret<Map<String, BigDecimal>> sVolume(@RequestParam int n) {
        return queryService.sVolume(n);
    }

    @Override
    public Ret<Map<String, BigDecimal>> sValue(@RequestParam int n) {
        return this.queryService.sValue(n);
    }

    @Override
    public Ret<Map<String, BigDecimal>> nProd(String c1, String c2, int n) {
        return queryService.nProd(c1, c2, n);
    }

    @Override
    public Ret<List<DailySales>> catSalesTrend(@RequestParam String c1,@RequestParam String c2, @RequestParam String c3, @PathVariable("n") int n) {
        return this.queryService.catSalesTrend(c1, c2, c3, n);
    }

    @Override
    public Ret<List<TopOnes>> hot(@PathVariable("n") int n) {
        return this.queryService.hot(n);
    }

    @Override
    public Ret<List<TopOnes>> hots(@PathVariable("n") int n) {
        return this.queryService.hots(n);
    }

    @Override
    public Ret<List<ProdItem>> prodList(@RequestBody InParamProd p) {
        String userid = "123";
        ResultEntry<List<MyWatchPo>> watchs_ret = userFeignInterface.getProdWatchList(userid);
        //logger.info(JSONObject.toJSONString(watchs_ret));

        Ret<List<ProdItem>> prolist_ret = this.queryService.prodList(p);
        if(watchs_ret.ok() && prolist_ret.ok()){

            List<MyWatchPo> watchPoList = watchs_ret.getData();
            List<ProdItem> prolist = prolist_ret.d;

            for(ProdItem item:prolist){

                MyWatchPo po = new MyWatchPo();
                po.setProid(item.tid);

                if(watchPoList.contains(po)){
                    item.setFav(true);
                }else{
                    item.setFav(false);
                }
            }
            return prolist_ret;

        }else {
            return Ret.getNewFailure("sys error!");
        }
    }

    @Override
    public Ret<List<ShopItem>> shopList(@RequestBody  InParam p) {

        String userid = "123";
        ResultEntry<List<MyWatchPo>> watchs_ret = userFeignInterface.getShopWatchList(userid);
        logger.info(JSONObject.toJSONString(p));
        Ret<List<ShopItem>> shoplist_ret = this.queryService.shopList(p);

        if(watchs_ret.ok() && shoplist_ret.ok()){

            List<MyWatchPo> watchPoList = watchs_ret.getData();
            List<ShopItem> shoplist = shoplist_ret.d;

            for(ShopItem item:shoplist){


                MyWatchPo po = new MyWatchPo();
                po.setShopid(item.tid);

                if(watchPoList.contains(po)){
                    item.setFav(true);
                }else{
                    item.setFav(false);
                }
            }
            return shoplist_ret;

        }else {
            return Ret.getNewFailure("sys error!");
        }
    }

    @Override
    public void toShop(String id) {
        this.queryService.toShop(id);
    }



    @GetMapping("/test")
    @ResponseBody
    public Ret test(){
        try{
            Ret<Brief> result = this.queryService.brief();
            return result;
        }catch (FeignException e){
            e.printStackTrace();
        }
        return Ret.getNewFailure("system error");
    }


}
