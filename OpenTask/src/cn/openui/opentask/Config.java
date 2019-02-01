package cn.openui.opentask;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	
	public static final String KEY_STATUS = "status";
	
	//public static final String API_HOST = "http://10.0.2.2:8083";//android avd
	public static final String API_HOST = "http://10.0.3.2:8083";//genymotion   
	public static final String API_URL_LOGIN = API_HOST+"/api/login";
	public static final String API_URL_GETLAST = API_HOST+"/cp/recent";
	public static final String API_URL_NOTAPPEAR = API_HOST+"/cp/disappear";
	public static final String API_URL_CacheData = API_HOST+"/cp/cache";
	public static final String API_URL_CardData = API_HOST+"/card/listapi";
	public static final String API_URL_Card = API_HOST+"/card/getapi";
	
	public static final String TOKEN = "key_token";
	public static final String APP_ID = "cn.openui.openTask";
	public static final String CHARSET = "utf-8";
	public static final String ACTION_GET_CODE = "send_pass";
	
	public static final int RESULT_STATUS_SUCCESS = 1;
	public static final int RESULT_STATUS_FAIL = 0;
	public static final int RESULT_STATUS_INVALID_TOKEN = 2;

	public static String getCacheToken(Context context){
		return context.getSharedPreferences(APP_ID, context.MODE_PRIVATE).getString(TOKEN, null);
	}
	
	public static void cacheToken(Context context, String token){
		Editor e = context.getSharedPreferences(APP_ID, context.MODE_PRIVATE).edit();
		e.putString(TOKEN, token);
		e.commit();
	}
}
