package openui.cn.tucao.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

import cn.openui.www.caodian.config.Config;

public class NetConnection extends AsyncTask<Integer, Integer, String> {

	private HttpMethod method;
	private String url;
	private SuccessCallback success;
	private FailCallback fail;
	private String param;
	
	public NetConnection(String url, HttpMethod method, final SuccessCallback success,final FailCallback fail, String...params){
		this.method = method;
		this.url = url;
		this.success = success;
		this.fail = fail;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<params.length;i++){
			sb.append("/").append(params[i]);
		}
		param = sb.toString();
	}
	@Override
	protected String doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		try {
			URLConnection uc;
			switch(this.method){
			case POST:
				uc = new URL(this.url).openConnection();
				uc.setDoOutput(true);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream()));
				bw.write(sb.toString());
				break;
			default:
				Log.v("debug", this.url+param);
				uc = new URL(this.url+param).openConnection();
				break;
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
			StringBuffer result = new StringBuffer();
			String line = null;
			while((line = br.readLine())!=null){
				result.append(line);
			}
			return result.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		this.success.onSuccess(result);
	}


	public static interface SuccessCallback{
		void onSuccess(String result);
	}
	
	public static interface FailCallback{
		void onFail();
	}
}
