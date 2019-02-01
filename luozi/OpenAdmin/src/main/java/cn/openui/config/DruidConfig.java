package cn.openui.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DruidConfig {

    @Bean
    @Primary
    //@ConfigurationProperties(prefix = "write.datasource")
    public DataSource getDatasource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    //动态绑定数据源
    @Bean("DataSource")
    public AbstractDataSource getDynamicDataSource(){

        MyDynamicDataSource datasource = new MyDynamicDataSource();
        Map<Object,Object> datasourcemap = Maps.newHashMap();
        datasourcemap.put(DataSourceHolder.DataSourceGloble.MASTER,getDatasource());
        datasource.setTargetDataSources(datasourcemap);
        return datasource;
    }


}
