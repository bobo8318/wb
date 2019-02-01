package openui.dao;

import openui.bean.LoginLog;
import openui.mapper.LogMapper;
import openui.tools.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogDao {
    private static final Logger logger = LoggerFactory.getLogger(LogDao.class);

    @Autowired
    private LogMapper mapper;

    public List<LoginLog> listLogInfo(String name, int offset, int pagesize, String role){
        if(pagesize>0){
            logger.info(name+"-"+role);
            if("role_admin".equals(role.toLowerCase()))
                name = "%"+name+"%";

            return mapper.listLogByPage(name, offset, pagesize);
        }else
            return null;
    }

    public int getLogCount(String name,  String role) {
        if("role_admin".equals(role.toLowerCase()))
            name = "%"+name+"%";
        return mapper.getCount(name);
    }

    public int addLog(LoginLog log) {
        mapper.addLog(log);
        return log.getId();
    }

    public void logout(int logid, String formatDate) {
        mapper.logout(logid, formatDate);
    }
}
