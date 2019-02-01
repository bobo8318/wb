package cn.openui.opentask.service;

import org.json.JSONException;
import org.json.JSONObject;

import cn.openui.opentask.Config;
import cn.openui.opentask.net.HttpMethod;
import cn.openui.opentask.net.NetConnection;

public class MainService {
	
	public static interface successCallBack{
		void onSuccess(String result);
	}
	public static interface failCallBack{
		void onFail();
	}
	
	/*public void getCode(String phone, final successCallBack success, final failCallBack fail ){
		new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonobject = new JSONObject(result);
					switch(jsonobject.getInt(Config.KEY_STATUS)){
					case Config.RESULT_STATUS_SUCCESS:
						if(success!=null)
							success.onSuccess(result);
						break;
					//case Config.RESULT_STATUS_FAIL:break;
					//case Config.RESULT_STATUS_INVALID_TOKEN:break;
					default:
						if(fail!=null)
							fail.onFail();
						break;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(fail!=null)
						fail.onFail();
				}
				
			}
		});
	}*/
	
	/**
	 * 
	 * @param username
	 * @param pwd
	 * @param success
	 * @param fail
	 */
	public void login(String username, String pwd, final successCallBack success, final failCallBack fail){
		NetConnection nc = new NetConnection(Config.API_URL_LOGIN, HttpMethod.GET, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				if(success!=null){
					success.onSuccess(result);
				}
			}
		}, null,username,pwd,"token");
		nc.execute(1000);
		
	}
	/**
	 * 
	 */
	public void getLastData(final successCallBack success, final failCallBack fail){
			NetConnection nc = new NetConnection(Config.API_URL_GETLAST, HttpMethod.GET, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				if(success!=null){
					success.onSuccess(result);
				}
			}
		}, null);
		nc.execute(1000);
		
	}
	/**
	 * get card data
	 */
	public void getCards(int page,String key,final successCallBack success, final failCallBack fail){
		String url = "".equals(key)?Config.API_URL_CardData+"/"+page:Config.API_URL_CardData+"/"+key+"/"+page;
		NetConnection nc = new NetConnection(url, HttpMethod.GET, new NetConnection.SuccessCallback() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(success!=null){
							success.onSuccess(result);
						}
					}
		}, null);
		nc.execute(1000);
	}
	
}
