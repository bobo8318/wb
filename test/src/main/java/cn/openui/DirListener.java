package cn.openui;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * Created by My on 2017/6/29.
 */
public class DirListener implements FileAlterationListener {

    private String updatedir;
    private Callback callback;
    public DirListener(String updatedir, Callback callback){
        this.updatedir  = updatedir;
        this.callback = callback;
    }

    public void onStart(FileAlterationObserver observer) {
        if(callback!=null)
            callback.onCallBack(1,"starting monitor dir......");
    }

    public void onDirectoryCreate(File directory) {
       // SocketUtils.CreateDir(directory);
    }

    public void onDirectoryChange(File directory) {
        //SocketUtils.updateDir(directory);
    }

    public void onDirectoryDelete(File directory) {
        //SocketUtils.removeDir(directory);

    }

    public void onFileCreate(File file) {
        //SocketUtils.createFile(file);
        if(callback!=null)
            callback.onCallBack(1,"create file......"+file.getName());
    }

    public void onFileChange(File file) {
        //SocketUtils.updateFile(file);
        if(callback!=null)
            callback.onCallBack(1,"modify file......"+file.getName());
    }

    public void onFileDelete(File file) {
        //SocketUtils.removeFile(file);
        if(callback!=null)
            callback.onCallBack(1,"remove file......"+file.getName());
    }

    public void onStop(FileAlterationObserver observer) {
        if(callback!=null)
            callback.onCallBack(1,"ending monitor dir......");
    }

    public  interface Callback{
        public  void onCallBack(int what,String msg);
    };
}
