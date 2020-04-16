package pub.imba.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pub.imba.tools.BaseParam;
import pub.imba.util.ResultConverter;

import java.io.File;

@EnableWebMvc
@Configuration
public class MyWebConfig  extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/css/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/bootstrap/");
        registry.addResourceHandler("/less/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/less/");
        registry.addResourceHandler("/plugins/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/plugins/");
        registry.addResourceHandler("/images/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/images/");
        registry.addResourceHandler("/ceshi/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/ceshi/");

        super.addResourceHandlers(registry);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/static/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    @ConfigurationProperties("myconfig")
    BaseParam getMyBaseParam(){
        return new BaseParam();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;

    }

   /* @Bean
    public DB getMapDb(){
        DB db = DBMaker.fileDB(new File("mapdb.db"))
                .closeOnJvmShutdown().make();

        return db;
    }*/

   @Bean
    public ResultConverter getConverter(){
       return new ResultConverter();
   }



}
