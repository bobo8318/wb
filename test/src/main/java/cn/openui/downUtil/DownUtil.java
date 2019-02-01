package cn.openui.downUtil;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by My on 2017/7/23.
 */
public class DownUtil{

    public static final int cache = 10 * 1024;
    public static final boolean isWindows;
    public static final String splash;
    public static final String root;
    static {
        if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
            isWindows = true;
            splash = "\\";
            root="D:";
        } else {
            isWindows = false;
            splash = "/";
            root="/search";
        }
    }

    private String filepath;
    private String url;
    private boolean rename = false;

    public void down(String link){
        this.url = link;
            try {

                HttpClient client = new DefaultHttpClient();

                HttpGet httpget = new HttpGet(url);
                HttpResponse response = client.execute(httpget);
               // httpget.setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET, "gbk");

                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();

                if (filepath == null)
                    filepath = getFilePath(response);

                File file = new File(filepath);
                file.getParentFile().mkdirs();
                FileOutputStream fileout = new FileOutputStream(file);
                /**
                 * 根据实际运行效果 设置缓冲区大小
                 */
                byte[] buffer=new byte[cache];
                int ch = 0;
                while ((ch = is.read(buffer)) != -1) {
                    fileout.write(buffer,0,ch);
                }
                is.close();
                fileout.flush();
                fileout.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public  String getFilePath(HttpResponse response) {
        String filepath = root + splash;
        String filename = getFileName(response);
        String filenameurl = getNameFromUrl();

        if (filename != null) {
            filepath += filename;
        } else if(filenameurl!=null&&!"".equals(filenameurl)){
            filepath += filenameurl;
        }else {
            filepath += getRandomFileName();
        }
        return filepath;
    }

    public  String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");

        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return filename;
    }

    public String getNameFromUrl(){
        String filename = null;
        if(url!=null&&!"".equals(url)){
            String newUrl = url.replace("http://","");
            if(newUrl.indexOf("/")>0){
                filename = newUrl.substring(newUrl.lastIndexOf("/")+1,newUrl.length());
            }
        }
        return filename;
    }

    public  String getRandomFileName() {
        return String.valueOf(System.currentTimeMillis());
    }
    public  void outHeaders(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i]);
        }
    }
}
