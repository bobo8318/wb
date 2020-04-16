package pub.imba.service;

import com.alibaba.fastjson.JSONObject;
import com.luo.erlei.comm.out.Ret;
import io.swagger.annotations.*;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pub.imba.dao.UserDao;
import pub.imba.model.CollectPro;
import pub.imba.model.LoginLog;
import pub.imba.model.MyWatchPo;
import pub.imba.model.UserPo;
import pub.imba.query.UserQueryInterface;
import pub.imba.sms.SmsUtil;
import pub.imba.util.ResultEntry;
import pub.imba.util.ResultEntryFactory;
import pub.imba.util.TextUtil;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(description = "用户数据接口")
@RestController
public class UserService implements UserQueryInterface {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private UserDao dao;
    /**
     * 根据电话获取验证码 返回
     * @return
     */
    @Override
    public ResultEntry getCode(@RequestParam String  phoneno){
        //LOGGER.info(JSONObject.toJSONString(params));

        //String phoneno = params.get("phoneno");

        if(!TextUtil.isEmpty(phoneno)){
           String code = smsUtil.sendSms(phoneno);
            if(code != null){
                ResultEntry entry = ResultEntryFactory.getSuccessEntry("success");
                entry.setParam0(code);
                entry.setData(code);
                return entry;
            }else{
                ResultEntry entry = ResultEntryFactory.getSMSErrorEntry("");
                return entry;
            }
        }else {
            ResultEntry entry = ResultEntryFactory.getParamInvalidate("invalidate phone no.");
            return entry;
        }
    }


    @Override
    public ResultEntry<List<MyWatchPo>> getProdWatchList(@RequestParam  String userid) {
        ResultEntry<List<MyWatchPo>>  result = ResultEntryFactory.getSuccessEntry("seccess");
        result.setData(dao.getWatchProListByUser(userid));
        return result;
    }


    @Override
    public ResultEntry<List<MyWatchPo>> getShopWatchList(@RequestParam  String userid) {
        ResultEntry<List<MyWatchPo>>  result = ResultEntryFactory.getSuccessEntry("success");
        result.setData(dao.getWatchShopListByUser(userid));
        return result;
    }


    @Override
    public ResultEntry watchProduct(@RequestBody  MyWatchPo watch) {
        //LOGGER.info(JSONObject.toJSONString(watch));
        if(watch.watched){
            //LocalDate today = LocalDate.now();
           // watch.setWatchdate(today.toString());
            this.dao.watchPro(watch);

        }else{
            this.dao.unWatchPro(watch);
        }
        ResultEntry result = ResultEntryFactory.getSuccessEntry("");
        return result;
    }

    @Override
    public ResultEntry watchShop(@RequestBody  MyWatchPo watchPo) {
        if(watchPo.watched){
            //收藏
            this.dao.watchShop(watchPo);
        }else{
            // 取消收藏
            this.dao.unWatchShop(watchPo);
        }
        return ResultEntryFactory.getSuccessEntry("");
    }


    @Override
    public ResultEntry addLoginInfo(@RequestBody Map<String, String> params){
        if(params.containsKey("phoneno")){
            String phoneno = params.get("phoneno");
            //查询是否用户存在
            UserPo user = dao.getUserByPhone(phoneno);
            if(user == null){
                //新用户
                user = new UserPo();
                user.setPhoneno(phoneno);
                user.setUuid(UUID.randomUUID().toString());
                dao.addUser(user);
            }

            LoginLog log = new LoginLog();
            log.setUserid(user.getUuid());
            LocalDateTime now = LocalDateTime.now();
            log.setLogintime(now.toString());
            dao.addLoginLog(log);
            ResultEntry result = ResultEntryFactory.getSuccessEntry("success");
            result.setParam0(user.getUuid());
            return result;
        }
        return ResultEntryFactory.getParamInvalidate("param wrong");
    }


    @Override
    public ResultEntry test(@RequestParam String param){
        ResultEntry result = ResultEntryFactory.getSuccessEntry("success");
        result.setParam0(param);
        return result;
    }
}
