package pub.imba.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private  final static Logger logger = LoggerFactory.getLogger(FeignBasicAuthRequestInterceptor.class);


    @Override
    public void apply(RequestTemplate requestTemplate) {
        //logger.info("-------------------------------------------------------------------------------");
        // 传递请求头
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();

        //logger.info(JSONObject.toJSONString(request.getHeader("content-type")));

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }

        // 传递请求参数

         //Enumeration<String> bodyNames = request.getParameterNames();
        //logger.info(JSONObject.toJSONString(bodyNames));
         /* StringBuffer body =new StringBuffer();
          if (bodyNames != null) {
              while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
              }
          }
         if(body.length()!=0) {
            body.deleteCharAt(body.length()-1);
            //template.body(body.toString());
            logger.info("feign interceptor body:{}",body.toString());
        }*/

    }
}
