package pub.imba.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pub.imba.sms.SmsUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter({DruidDataSourceConfig.class})
@EnableTransactionManagement
public class MybatisConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);

    //@Resource(name="masterDataSource")
    @Autowired
    private DataSource dataSource;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        //return super.sqlSessionFactory(roundRobinDataSourceProxy());
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = bean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 事物管理器
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
