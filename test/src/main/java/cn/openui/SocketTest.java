package cn.openui;

import cn.openui.downUtil.DownUtil;
import cn.openui.excelHelper.ExcelHelper;
import cn.openui.spider.Spider;

import javax.swing.*;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2017/6/27.
 */
public class SocketTest {
    public static void main(String[] args) throws Exception {
     //   System.out.println( InetAddress.getLocalHost());
        //监控线程
        //DirUtils m = new DirUtils(5000);
       // m.monitor("E:\\pdf",new DirListener());
       // m.start();
        //通信服务器线程 用于接收其他客户端发过来的数据
       // OpenSynClient osc = new OpenSynClient();
       // osc.ScanClient("");//scan others client
        /*new Thread(new OpenSynClient()).start();*/

       // UI ui = new UI();

       //System.out.println(util.getNameFromUrl("http://127.0.0.1:8087/test.docx")) ;



        // if (args.length >= 2)
        // minColumns = Integer.parseInt(args[1]);


        /*ExcelHelper helper = new ExcelHelper();
        helper.setDataHandler(new ExcelHelper.DataHandler() {//2007文件
            public void handler(List data, int status) {
                // TODO Auto-generated method stub

            }//handler end
        });
        helper.initSheet("G:\\精选优质商品清单(内含优惠券)-2017-07-26.xls");
       // helper.initSheet("G:\\房山加密会议高清改造报价V1.0(2).xlsx");
        //helper.setTitleStart(1);
        //helper.setColEnd(15);
        String[] titles = helper.getTitles();
        List<String[]> data = helper.importData();
        System.out.println(titles[0]+titles.length);
        String test = "66c74e39d0c04f0a9103bcc8159b65ca";
        System.out.println(test.length());
        //ExcelCompareUtil compare = new ExcelCompareUtil("E:\\x信通处\\2017\\移动警务\\移动警务终端汇总","手机使用人,使用人身份证号码","E:\\x信通处\\2017\\移动警务\\花名册.xls","姓名,出生日期",0);
        */
        UI ui = new UI();



    }
}
