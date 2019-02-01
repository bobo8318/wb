package openui.cn.tucao.service;

import android.content.Context;
import android.widget.Toast;

import cn.openui.www.caodian.net.NetConnection;

/**
 * Created by My on 2016/10/30.
 */
public class MainService {

    private Context context;

    public MainService(Context context){
        this.context = context;
    }

    public void test(String test) {
        Toast.makeText(context,test,Toast.LENGTH_LONG).show();
    }

    public int getRandom(int bound){
        int result = (int) Math.random()*bound;
        return result;
    }
}
