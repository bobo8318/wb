package cn.openui;

import java.io.IOException;

import okhttp3.*;

public class OpenApiSdk {

	private static String HOST = "http://localhost:8086/index.php/cp";
	private static String KEY_value = "controllmyself";
	private static String TOKEN_value = "controllmyself321";
	
	
	
	private OkHttpClient client = new OkHttpClient();
	
	
	public String doGet(RequestEntries entry){
		Request request = new Request.Builder().url(HOST+"/storeBuyLogData/"+entry.getUrl()).build();
		String result = "";
		try {
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()){
				throw new IOException("服务器端故障:" + response);
			}
			//Headers responsheader = response.headers();
			//result = response.body().toString();
			result = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public String doPost(RequestEntries entry){


		FormBody.Builder builder = new FormBody.Builder();
		builder.add("data",entry.getPostData());
		RequestBody formBody = builder.build();

		String result = "";
		try {
			Request request = new Request.Builder().url(HOST+"/storeBuyLogData/"+entry.getUrl()).post(formBody).build();
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()){
				throw new IOException("服务器端故障:" + response);
			}
			//Headers responsheader = response.headers();
			//result = response.body().toString();
			result = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	
	public static void main(String[] args){

		RequestEntries entries = new RequestEntries("controllmyself","controllmyself321");
		entries.addParam("coder", "123");
		entries.addParam("user", "kk");
		entries.addParam("timestamp", "20170101");
		entries.addParam("info", "test");

		entries.setPostData("");

		OpenApiSdk sdk = new OpenApiSdk();

		System.out.println(sdk.doGet(entries));
	}
}
