package pub.imba.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class DruidDataSourceConfig {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druid(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
}
