package pub.imba.impl;

import com.luo.erlei.comm.in.InParam;
import com.luo.erlei.comm.in.InParamProd;
import com.luo.erlei.comm.out.*;
import com.luo.erlei.comm.service.QueryService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class QueryFeignClientFallBack implements QueryService {
    @Override
    public Ret<Map<String, String>> cats(String p) {
        return null;
    }

    @Override
    public Ret<Brief> brief() {
        return null;
    }

    @Override
    public Ret<Map<String, BigDecimal>> sVolume(int n) {
        return null;
    }

    @Override
    public Ret<Map<String, BigDecimal>> sValue(int n) {
        return null;
    }

    @Override
    public Ret<Map<String, BigDecimal>> nProd(String c1, String c2, int n) {
        return null;
    }

    @Override
    public Ret<List<DailySales>> catSalesTrend(String c1, String c2, String c3, int n) {
        return null;
    }

    @Override
    public Ret<List<TopOnes>> hot( int n) {
        return null;
    }

    @Override
    public Ret<List<TopOnes>> hots( int n) {
        return null;
    }

    @Override
    public Ret<List<ProdItem>> prodList( InParamProd p) {
        return null;
    }

    @Override
    public Ret<List<ShopItem>> shopList( InParam p) {
        return null;
    }

    @Override
    public void toShop(String id) {

    }
}
