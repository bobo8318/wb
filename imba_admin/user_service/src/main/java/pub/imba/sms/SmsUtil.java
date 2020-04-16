package pub.imba.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pub.imba.model.SmsConfig;
import pub.imba.model.SmsResponseData;

@Component
public class SmsUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

    @Autowired
    private SmsConfig smsConfig;

    //随机生成len位随机数
    public String getRandomCoder(int len){
        StringBuffer stringBuffer=new StringBuffer();
        for (int x=0;x<len;x++) {
            int random = (int) (Math.random() * (10 - 1));
            stringBuffer.append(random);
        }
        String string = stringBuffer.toString();
        return string;
    }

    public String sendSms(String phoneno){
        String code = this.getRandomCoder(6);

        //DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneno);
        request.putQueryParameter("SignName", smsConfig.getSignName());
        request.putQueryParameter("TemplateCode", smsConfig.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        //request.putQueryParameter("SmsUpExtendCode", "5");
        //request.putQueryParameter("OutId", "6");
        try {
            CommonResponse response = client.getCommonResponse(request);
            if(response.getHttpStatus() == 200){
                SmsResponseData data = JSONObject.parseObject(response.getData(),SmsResponseData.class);
                if(data.getCode().equals("OK")){
                    return code;
                }
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        SmsUtil util = new SmsUtil();
        util.sendSms("15901159310");
    }
}
