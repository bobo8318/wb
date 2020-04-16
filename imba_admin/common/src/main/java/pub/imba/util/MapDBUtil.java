package pub.imba.util;

import java.util.Map;

public class MapDBUtil {

    private String map_name;
    private String file_path;

    //DB db = null;
    //需要的数据格式
    Map<String,String> statMap = null;

    //public DB getStatMapDB(){
        //return null;
    //}

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
