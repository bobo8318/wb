package cn.openui.opentask.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.openui.opentask.Config;
import cn.openui.opentask.R;

public class AtyTimeLine extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_timeline);
		//Bundle bundle = this.getIntent().getExtras();
		//String token = bundle.getString(Config.TOKEN);
		//String  token = Config.getCacheToken(this);
		//Toast.makeText(this, "time line"+token, Toast.LENGTH_LONG).show();
		ListView lv = (ListView) findViewById(android.R.id.list);
		AtyTimeLineMessageListAdapter atlAdapter = new AtyTimeLineMessageListAdapter(this);
		lv.setAdapter(atlAdapter);
		//lv.setAdapter(new ArrayAdapter<String>(this,
               // android.R.layout.simple_list_item_1, strs));

   
	
	}
	
	private static final String[] strs = new String[] {
		    "first", "second", "third", "fourth", "fifth"
	};
}
