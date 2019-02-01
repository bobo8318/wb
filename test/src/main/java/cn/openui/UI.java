package cn.openui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by My on 2017/6/29.
 */
public class UI extends JFrame implements DirListener.Callback{
    private JTextArea internaval;//客户端地址列表
    private JTextArea info;//客户端信息显示

    private JTextArea localDir;//本地需同步目录列表
    private JTextArea remoteDir;//远程同步目录列表

    private JToggleButton startBtn;

    private DirUtils m = null;

    public UI(){
        setLayout(new GridLayout());
        //设置窗体中显示的字体样式
        setFont(new Font("Helvetica",Font.PLAIN, 14));
        JPanel root = new JPanel();


        //btn = new JButton("启动...");
        startBtn = new JToggleButton("启动");
        startBtn.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if(startBtn.getText().equals("启动")){//启动client

                    startScan(Integer.valueOf(internaval.getText()),localDir.getText(),remoteDir.getText());

                    startBtn.setText("停止");
                } else if(startBtn.getText().equals("停止")){//停止client

                    if(m!=null)
                        try {
                            m.stop();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    startBtn.setText("启动");
                }
            }
        });
        root.add(startBtn);



        internaval = new JTextArea("5000");
        internaval.setColumns(10);
        internaval.setRows(2);
        root.add(internaval);


        localDir = new JTextArea("E:\\pdf");
        localDir.setColumns(10);
        localDir.setRows(2);
        root.add(localDir);

        remoteDir = new JTextArea("同步到");
        remoteDir.setColumns(10);
        remoteDir.setRows(2);
        root.add(remoteDir);

        info = new JTextArea("信息");
        info.setColumns(40);
        info.setRows(20);
        root.add(info);

        add(root);
        setSize(460, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    private void startScan(int internal,String scanDir, String updateDir) {
        try {
            m = new DirUtils(internal);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.monitor(scanDir,new DirListener(updateDir,this));
    }

    public void onCallBack(int what, String msg) {
        switch (what){
            case 1://show log
                if(info.getText().length()>=400)
                    info.setText("");
                info.append(msg+"\n");
                break;
        }
    }
}
