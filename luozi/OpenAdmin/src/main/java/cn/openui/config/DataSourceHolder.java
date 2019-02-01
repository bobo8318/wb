package cn.openui.config;

public class DataSourceHolder {

    private static final ThreadLocal<DataSourceGloble> local = new ThreadLocal();

    public enum DataSourceGloble{
        MASTER,SLAVE
    }

    public static void clear(){
        local.remove();
    }

    public static void setDataSource(DataSourceGloble type){
        if(local == null){
            throw new NullPointerException();
        }else{
            local.set(type);
        }
    }

    public static DataSourceGloble getDataSource(){
        if(local == null){
            return DataSourceGloble.MASTER;
        }else{
            return local.get();
        }
    }


}
