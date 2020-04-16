package pub.imba.feignInterface;

import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.*;
import com.luo.erlei.comm.service.QueryService;
import com.netflix.ribbon.proxy.annotation.Http;
import feign.Headers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pub.imba.impl.QueryFeignClientFallBack;
import pub.imba.impl.UserFeignClientFallBack;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 未使用
 */
//@FeignClient(name="user-service", fallback = QueryFeignClientFallBack.class)
//@FeignClient(name="user-service")
//@Component
public interface QueryFeignInterface  extends QueryService{

   /* @Override
    @PostMapping({"shop/list"})
    @Headers("Content-Type: application/json")
    Ret<List<ShopItem>> shopList(@RequestBody InParam p);*/

    /*@Override
    @GetMapping({"cat"})
    Ret<Map<String, String>> cats(@RequestParam("p") String p);

    @Override
    @GetMapping({"brief"})
    Ret<Brief> brief();

    @Override
    @GetMapping({"cat/s-volume"})
    Ret<Map<String, BigDecimal>> sVolume(@RequestParam("n") int n);

    @Override
    @GetMapping({"cat/s-value"})
    Ret<Map<String, BigDecimal>> sValue(@RequestParam("n") int n);

    @Override
    @GetMapping({"cat/new"})
    Ret<Map<String, BigDecimal>> nProd(@RequestParam("c1") String c1, @RequestParam("c2") String c2, @RequestParam("n") int n);

    @Override
    @GetMapping({"trend/sale/{n}"})
    Ret<List<DailySales>> catSalesTrend(@RequestParam("c1") String c1, @RequestParam("c2") String c2, @PathVariable("n") int n);

    @Override
    @GetMapping({"prod/hot{n}"})
    Ret<List<TopOnes>> hot( @PathVariable("n") int n);

    @Override
    @GetMapping({"shop/hot{n}"})
    Ret<List<TopOnes>> hots(@PathVariable("n") int n);

    @Override
    @PostMapping({"prod/list"})
    Ret<List<ProdItem>> prodList(@RequestBody InParamProd p);

    @Override
    @PostMapping({"shop/list"})
    Ret<List<ShopItem>> shopList(@RequestBody InParam p);

    @Override
    @GetMapping({"gs/{id}"})
    void toShop(@PathVariable("id") String id);*/
}
