package cn.openui;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by My on 2017/6/27.
 */
public class OpenSynClient implements Runnable{

    private boolean status = true;
    private int model;
    private String[] host;//客户端主机列表
    private String idcode;//客户端标识

    public OpenSynClient(){
        this.idcode = "qwer";
    }

    public void ScanClient(String scope) throws UnknownHostException {
        //String localIp = InetAddress.getLocalHost().toString();

    }

    public void run() {
        //

        //
        Selector selector = null;
        SocketChannel socketChannel = null;
        try {
            socketChannel.configureBlocking(false);
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while(this.status) {

            }

            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
