package cn.openui.opentask.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.openui.opentask.Config;
import cn.openui.opentask.R;
import cn.openui.opentask.service.MainService;

public class AtyLogin extends Activity {
	private MainService service = new MainService();
	
	private EditText name;
	private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);
		//login
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = (EditText)findViewById(R.id.username);
				password = (EditText)findViewById(R.id.password);
				//login
				service.login(name.getText().toString(), password.getText().toString(), new MainService.successCallBack() {
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(result!=null&&!"fail".equals(result)){//¥Ê»Îtoken
							Intent i = new Intent(AtyLogin.this, AtyTimeLine.class);
							//i.putExtra(Config.TOKEN, result);
							startActivity(i);
							Config.cacheToken(AtyLogin.this, result);
							
						}else{
							Toast.makeText(AtyLogin.this, result, Toast.LENGTH_LONG).show();
						}
					}
				},null);
				//Toast.makeText(AtyLogin.this, result, Toast.LENGTH_LONG).show();
			}
		});
		//reset
		findViewById(R.id.btn_code).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = (EditText)findViewById(R.id.username);
				password = (EditText)findViewById(R.id.password);
				
				name.setText("");
				password.setText("");
			}
		});
		
	}
}
