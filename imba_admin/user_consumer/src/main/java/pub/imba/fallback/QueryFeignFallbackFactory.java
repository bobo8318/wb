package pub.imba.fallback;

import feign.hystrix.FallbackFactory;
import pub.imba.feignInterface.UserFeignInterface;
import pub.imba.model.MyWatchPo;
import pub.imba.util.ResultEntry;

import java.util.List;
import java.util.Map;

public class QueryFeignFallbackFactory implements FallbackFactory<UserFeignInterface> {
    @Override
    public UserFeignInterface create(Throwable throwable) {
        return new UserFeignInterface() {

            @Override
            public ResultEntry getCode(String phoneno) {
                return null;
            }

            @Override
            public ResultEntry<List<MyWatchPo>> getProdWatchList(String userid) {
                return null;
            }

            @Override
            public ResultEntry<List<MyWatchPo>> getShopWatchList(String userid) {
                return null;
            }

            @Override
            public ResultEntry watchProduct(MyWatchPo watch) {
                return null;
            }

            @Override
            public ResultEntry watchShop(MyWatchPo watchPo) {
                return null;
            }

            @Override
            public ResultEntry addLoginInfo(Map<String, String> params) {
                return null;
            }

            @Override
            public ResultEntry test(String param) {
                return null;
            }
        };

    }
}
