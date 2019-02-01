package openui.service;

import openui.bean.LoginLog;

import java.util.List;

public interface LogSevice {

    public List<LoginLog> listLogInfo(String name, int page, int pagesize, String role);
    public int getPageCount(String name, int pagesize , String role);
    public int getLogCount(String name,  String role);
    public void addLog(LoginLog log);

    void LogOut(int logid);
}
