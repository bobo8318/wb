package cn.openui.opentask.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.openui.opentask.R;
public class AtyMain extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
		
		
		Button cpbtn = (Button)this.findViewById(R.id.btn_main_cp);
		Button cardbtn = (Button)this.findViewById(R.id.btn_main_card);
		Button msgbtn = (Button)this.findViewById(R.id.btn_main_myMsg);
		
		cpbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AtyMain.this,AtyCP.class));
				//MainActivity.this.finish();
			}
			
		});
		
		cardbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AtyMain.this,AtyCard.class));
				//MainActivity.this.finish();
			}
			
		});
		
		msgbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(MainActivity.this,AtyCard.class));
				//MainActivity.this.finish();
				//Toast.makeText(AtyMain.this, "hello world", Toast.LENGTH_LONG).show();
				startActivity(new Intent(AtyMain.this,AtyMessage.class));
			}
			
		});
		
		
	}
   
}
