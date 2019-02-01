package cn.openui.opentask.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import cn.openui.opentask.R;
import cn.openui.opentask.service.MainService;

public class AtyCP extends Activity {

private MainService service;
	
	private TextView lastNo;
	private TextView lastData;
	private TextView lastDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_cp);
		service = new MainService();
		
		lastNo = (TextView) findViewById(R.id.lastNo);
		lastData = (TextView) findViewById(R.id.lastData);
		lastDate = (TextView) findViewById(R.id.lastDate);
		
		service.getLastData(new MainService.successCallBack() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				//json
				try {
					//JSONArray json = new JSONArray(result);
					//for(int i=0;i<json.length();i++){
						//JSONObject jsonObject = (JSONObject)json.get(i);
						JSONObject jsonObject = new JSONObject(result);
						lastNo.setText(jsonObject.getString("lotteryNo"));
						lastData.setText(jsonObject.getString("lotteryData"));
						lastDate.setText(jsonObject.getString("lotteryDate"));
					//}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(AtyCP.this, "json parse fail", Toast.LENGTH_LONG).show();
				}
				
				
			}
		}, new MainService.failCallBack() {
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				Toast.makeText(AtyCP.this, "fail", Toast.LENGTH_LONG).show();
			}
		});
	}
}
