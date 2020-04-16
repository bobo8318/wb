package pub.imba.config;

import com.luo.erlei.comm.service.QueryService;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pub.imba.feignInterface.DataFeignInterface;
import pub.imba.feignInterface.QueryFeignInterface;
import pub.imba.interceptor.FeignBasicAuthRequestInterceptor;

@Configuration
public class TestFeignConfig {

    /*@Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }*/

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("dev1", "asfhunpjqw832h");
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignBasicAuthRequestInterceptor();
    }
    /*@Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }*/

    /**
     * FeignClient 配置
     * @return
     */
   /* @Bean
    DataFeignInterface dataFeignClient() {
        return Feign.builder()
                //.client(new OkHttpClient())
                //.logger(new TestApiFeignLogger())
               // .logLevel(Logger.Level.FULL)
                .requestInterceptor(basicAuthRequestInterceptor())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(DataFeignInterface.class, "http://laoluo.pagekite.me");
    }*/


    @Bean(name="queryfeign")
    @Scope("prototype")
    QueryService queryFeignClient() {
        return //HystrixFeign.builder()
                //.client(new OkHttpClient())
                //.logger(new TestApiFeignLogger())
                // .logLevel(Logger.Level.FULL)
                Feign.builder()
                .requestInterceptor(basicAuthRequestInterceptor())
                .requestInterceptor(requestInterceptor())
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(QueryFeignInterface.class, "http://laoluo.pagekite.me");
    }
}
