package pub.imba;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pub.imba.dao.UserDao;
import pub.imba.model.UserPo;
import pub.imba.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTests {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceTests.class);

    @Autowired
    UserDao dao;

    @Autowired
    UserService service;

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    @Test
    public void contestLoad() throws Exception {
        //List<UserPo> result =  dao.test();
        //LOGGER.info(result.get(0).getPhoneno());
        //Map<String, String> logininfo = new HashMap<>();
       // logininfo.put("phoneno","13820152792");
        //service.login(logininfo);
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/getCode")
                         .content("{\"phoneno\":\"15901159310\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        //.param("phone","15901159310")
                )
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = result.getResponse().getStatus();                 //得到返回代码
        String content = result.getResponse().getContentAsString();    //得到返回结果
        System.out.print(content);
    }
}
