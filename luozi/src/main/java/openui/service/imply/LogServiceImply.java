package openui.service.imply;

import openui.bean.LoginLog;
import openui.dao.LogDao;
import openui.service.LogSevice;
import openui.tools.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImply implements LogSevice {

    @Autowired
    private LogDao dao;

    @Override
    public List<LoginLog> listLogInfo(String name, int page, int pagesize, String role) {
        page = page<=0?1:page;
        if(pagesize>0){
            int offset = getOffset(page,pagesize);
            return dao.listLogInfo(name,offset,pagesize, role);
        }
        return null;
    }

    private int getOffset(int page, int pagesize) {
        if(page>0&&pagesize>0){
            return (page-1)*pagesize;
        }else
            return 0;
    }

    @Override
    public int getPageCount(String name, int pagesize , String role) {
        if(pagesize>0){
            int allcount = dao.getLogCount(name, role);
            return (int) Math.ceil((float)allcount/pagesize);
        }else
            return 0;

    }

    @Override
    public int getLogCount(String name , String role) {
        return dao.getLogCount(name, role);
    }

    @Override
    public void addLog(LoginLog log) {
        dao.addLog(log);
    }

    @Override
    public void LogOut(int logid) {
        dao.logout(logid,DateTools.getFormatDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
    }


}
