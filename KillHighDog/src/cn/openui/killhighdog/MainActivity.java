package cn.openui.killhighdog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.openui.aty.BackGround;
import cn.openui.aty.BaseDialog;

public class MainActivity extends Activity {

	private TextView stepts;
	private TextView totalInfo;
	private TextView msg;
	private ProgressDialog pd;
	private Dialog dia;
	
	public final static int STEPTS = 0;
	public final static int MSG = 1;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//setContentView(new BackGround(this));
		setContentView(R.layout.activity_main);
		BackGround gameView = (BackGround)findViewById(R.id.backGround);
				
		stepts = (TextView)findViewById(R.id.info);
		totalInfo =  (TextView)findViewById(R.id.totalInfo);
		gameView.setCallBack(new BackGround.CallBack(){//设置回调接口实现类
            @Override
            public void setTextView(String str, int position) {
            
            	switch(position){
            	case MainActivity.STEPTS:
            		stepts.setText(str);break;
            	case MainActivity.MSG:
            		showDialog(str);break;
            		default:break;
            	}
            	//如果是在thread线程中调用的，这里包装一层handle再更新textview的值
            }           
        });
		
		
		//gameView.BackGround(this);
		dia = new Dialog(MainActivity.this, R.style.dialog_style);
		
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
	    View customView = inflater.inflate(R.layout.mydialog, null);
	    msg = (TextView)customView.findViewById(R.id.msg);
	  
	    //Toast.makeText(this, msg.getText().toString(), Toast.LENGTH_LONG).show();
	    dia.setContentView(customView);
	    dia.show();
		dia.setCanceledOnTouchOutside(true); 
		//showDialog("test");

	}
	private void showDialog(String text){
		 msg.setText(text);
		 dia.show();
		 //dia.setCanceledOnTouchOutside(true); 
		
		/*dia = new BaseDialog(MainActivity.this,  R.style.dialog_style);
		dia.setContentView(R.layout.mydialog);
		LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
	    View customView = inflater.inflate(R.layout.mydialog, null);
	    msg = (TextView)customView.findViewById(R.id.msg);
	    msg.setText("ffffffffffffffff");
	    
		dia.show();
		dia.setCanceledOnTouchOutside(true);*/
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
