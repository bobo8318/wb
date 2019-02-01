package www.openui.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;


public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Test test = new Test();
		//System.out.println(test.getWsd());
		//test.getWsd();
		URLConnection uc = new URL("http://www.openui.cn/api/login/123/123/123").openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
		StringBuffer result = new StringBuffer();
		String line = null;
		while((line = br.readLine())!=null){
			result.append(line);
		}
		System.out.println(result);
	}
	
	public String getWsd() throws IOException{
			/*System.setProperty("sun.net.client.defaultConnectTimeout","20000");
	        System.setProperty("sun.net.client.defaultReadTimeout","20000");
	        // URL连接
	        //URL url = new URL("http://127.0.0.1/WebService.asmx/Add");
	        URL url = new URL("http://localhost:8083/index.html");
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Accept", "application/json"); 
	        conn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
	        conn.setDoOutput(true);
	        conn.setConnectTimeout(20000);
	        // 请求输入内容
	        BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
	        byte[] bytes = new byte[1024];
	        out.write(bytes);
	        out.flush();
	        out.close();
	        // 请求返回内容
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line=null;
	        StringBuilder sb = new StringBuilder();
	        while((line=in.readLine())!=null){
	        	  sb.append(line + "\n");
	        }

	        in.close();
	        return sb.toString();*/
		HttpClient hc = new DefaultHttpClient();
		String url = "http://218.92.108.33:5300/travelannualcard/page/GetCardInfoByIDCN.aspx?idcn=320721198810101411&index=1";
		 String body = null;
	        try {
	            // Get请求
	            HttpGet httpget = new HttpGet(url);
	           

	            // 设置参数
	            //String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
	           // httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
	            httpget.setURI(new URI(httpget.getURI().toString() ));
	            // 发送请求
	            HttpResponse httpresponse = hc.execute(httpget);
	            // 获取返回数据
	            HttpEntity entity = httpresponse.getEntity();
	            body = EntityUtils.toString(entity);
	            if (entity != null) {
	                entity.consumeContent();
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
        System.out.println(body);
        
        
       /* Parser parser = new Parser();       
        try {
			parser.setEncoding(parser.getEncoding());
			parser.setURL("http://localhost:8085/zbxx.htm");   
		    NodeFilter filter = new NodeClassFilter(LinkTag.class);       
		    NodeList list = parser.extractAllNodesThatMatch(filter);       
		        for (int i = 0; i < list.size(); i++) {           
		            LinkTag node = (LinkTag) list.elementAt(i);   
		            String link = node.extractLink();
		            if(checkLink(link)){
		            	System.out.println(node.extractLink()); 
		            	getDocLink(link);
		            }
		        }
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		return "";
	}
	
	private boolean checkLink(String link){
		if(link.contains("zbxx")&&!link.contains("index")){
			return true;
		}else{
			return false;
		}
	}
//	private String getDocLink(String doclink){
//		Parser parser = new Parser();
//		try {
//			parser.setEncoding(parser.getEncoding());
//			parser.setURL(doclink);   
//		    NodeFilter filter = new NodeClassFilter(LinkTag.class);       
//		    NodeList list = parser.extractAllNodesThatMatch(filter);       
//		    for (int i = 0; i < list.size(); i++) {           
//		      LinkTag node = (LinkTag) list.elementAt(i);   
//		      String link = node.extractLink();
//		      if(link.contains(".doc"))
//		    	  System.out.println(node.extractLink());
//		      break;
//		   }  
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

}
