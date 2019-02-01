package cn.openui.spider;

import cn.openui.downUtil.DownUtil;

/**
 * Created by My on 2017/7/23.
 */
public class Ant implements Runnable{

    private final static int WORKING = 1;
    private final static int IDLE = 0;

    private int status;
    private int getCount = 0;
    private String working_url;

    public void run() {
        DownUtil util = new DownUtil();
        util.down("");
    }
}
