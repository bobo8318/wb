package cn.openui.sql;

/**
 * Created by My on 2017/7/26.
 */
public class SQLUtil {
    public SQLUtil(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
