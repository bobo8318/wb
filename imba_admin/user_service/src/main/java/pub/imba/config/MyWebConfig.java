package pub.imba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pub.imba.model.SmsConfig;

@Configuration
public class MyWebConfig {

    @Bean
    @ConfigurationProperties("smsconfig")
    SmsConfig getSmsConfig(){
        return new SmsConfig();
    }
}
