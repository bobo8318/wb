package cn.openui;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by My on 2017/6/27.
 */
public class SocketUtils {

    public static void sendFile(SocketChannel sc, File file) throws IOException {
        FileInputStream fis = null;
        FileChannel channel = null;
        try {
            fis = new FileInputStream(file);
            channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            int size = 0;
            while ((size = channel.read(buffer)) != -1) {
                buffer.rewind();
                buffer.limit(size);
                sc.write(buffer);
                buffer.clear();
            }
            sc.socket().shutdownOutput();
        } finally {
            try {
                channel.close();
                fis.close();
            } catch (Exception ex) {
            }

        }
    }

    public static String sendData(SocketChannel sc, String data){
        return "";
    }

    public static void  receiveFile(SocketChannel sc, File file)throws IOException {
        FileOutputStream fos = null;
        FileChannel channel = null;

        try {
            fos = new FileOutputStream(file);
            channel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            int size = 0;
            while ((size = sc.read(buffer)) != -1) {
                buffer.flip();
                if (size > 0) {
                    buffer.limit(size);
                    channel.write(buffer);
                    buffer.clear();
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch(Exception ex) {}
            try {
                fos.close();
            } catch(Exception ex) {}
        }
    }

    public static void CreateDir(File directory) {
        System.out.println("create dir: "+directory.getName());
    }


    public static void updateDir(File directory) {
        System.out.println("update dir: "+directory.getName());
    }

    public static void removeDir(File directory) {
        System.out.println("remove dir: "+directory.getName());
    }

    public static void createFile(File file) {
        System.out.println("create file: "+file.getName());
    }

    public static void updateFile(File file) {
        System.out.println("update file: "+file.getName());
    }

    public static void removeFile(File file) {
        System.out.println("remove file: "+file.getName());
    }
}
