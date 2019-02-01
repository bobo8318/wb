import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootApplication.class)
public class SpringbootmvcApplicationTests {
    //private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootmvcApplicationTests.class);
    @Autowired
    DataSource datasource;
    @Test
    public void contextLoads() {
        System.out.println(String.valueOf(datasource.getClass()));


        //LOGGER.info(String.valueOf(datasource.getClass()));

    }

}
