package pub.imba.feignInterface;

import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.*;
import com.luo.erlei.comm.service.QueryService;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pub.imba.impl.UserFeignClientFallBack;
import pub.imba.model.MyProdItem;
import pub.imba.model.MyRet;
import pub.imba.model.MyUser;
import pub.imba.util.ResultEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//@FeignClient(name="user-service")
//@Component 暂停使用
public interface DataFeignInterface {


    //@RequestMapping(value = "/q/brief", method = RequestMethod.GET)
    //@RequestLine("GET /user/test?param={param}")
    //public ResultEntry test(@Param("param") String param);

    @RequestLine("GET /q/brief")
    public Ret<Brief> brief();

    @RequestLine("GET /q/cat?p={p}")
    public Ret<Map<String,String>> cat(@Param("p") String p);

    @RequestLine("GET /q/cat/new")
    public Ret<Map<String,BigDecimal>> catNew(@Param("c1") String c1, @Param("c2")  String c2, @Param("n") int days);

    @RequestLine("GET /q/cat/s-value/{n}")
    public Ret<Map<String,BigDecimal>> svalue(@Param("n") int days);

    @RequestLine("GET /q/cat/s-volume/{n}")
    public Ret<Map<String,BigDecimal>> svolume(@Param("n") int days);

    @RequestLine("GET /q/gs/{id}")
    public Ret<Map<String,BigDecimal>> gs(@Param("id") String id);

    @RequestLine("GET /q/prod/hot{n}")
    public Ret<List<TopOnes>> proHot(@Param("n") int n);

    @RequestLine("POST /q/prod/list")
    @Headers("Content-Type: application/json")
    public Ret<List<MyProdItem>> proList(@RequestBody  InParamProd inparam);

    @RequestLine("GET /q/shop/hot{n}")
    public Ret<List<TopOnes>> shopHot(@Param("n") int n);

    @RequestLine("POST /q/shop/list")
    @Headers("Content-Type: application/json")
    // 这里JSON格式需要的花括号居然需要转码，有点蛋疼了。
    //@Body("%7B\"size\": \"10\", \"sort\": \"\"%7D")
    public MyRet<List<ShopItem>> shopList(@RequestBody  InParam inparam);

    @RequestLine("GET /test/shop?p=3")
    public MyRet<List<ShopItem>> shopListTest();

    @RequestLine("GET /q/trend/sale/{n}?c1={c1}&c2={c2}")
    public Ret<List<DailySales>> trendSale(@Param("n") int  n, @Param("c1") String c1, @Param("c2") String c2);


}
