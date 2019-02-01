package cn.openui.opentask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.openui.opentask.activity.AtyCP;
import cn.openui.opentask.activity.AtyCard;
import cn.openui.opentask.activity.AtyLogin;
import cn.openui.opentask.activity.AtyMain;
import cn.openui.opentask.activity.AtyTimeLine;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*String token = Config.getCacheToken(this);
		if(token!=null){
			Intent i = new Intent(this, AtyTimeLine.class);
			i.putExtra(Config.TOKEN, token);
			startActivity(i);
		}else{
			startActivity(new Intent(this,AtyLogin.class));
		}*/
		startActivity(new Intent(this,AtyMain.class));
		finish();
		/*videoName = (EditText)this.findViewById(R.id.videoName);
		surfaceView = (SurfaceView)this.findViewById(R.id.videoPanel);
		surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceView.getHolder().setFixedSize(176, 144);
		surfaceView.getHolder().setKeepScreenOn(true);*/
	}
   
	
}
