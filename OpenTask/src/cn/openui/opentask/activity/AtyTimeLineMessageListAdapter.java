package cn.openui.opentask.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import cn.openui.opentask.R;
import cn.openui.opentask.data.Info;

public class AtyTimeLineMessageListAdapter extends BaseAdapter {
	//private Context context;
	private LayoutInflater mInflater;
	private List<Info> data;

	public AtyTimeLineMessageListAdapter(Context context){
		//this.context = context;
		this.mInflater = LayoutInflater.from(context);
		data = new ArrayList<Info>();
		for(int i=0;i<3;i++){
			Info info = new Info();
			info.setId(i);
			info.setContent("内容"+i);
			info.setTitle("标题"+i);
			data.add(info);
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		 if (convertView == null) {
             convertView = mInflater.inflate(R.layout.item,null);
             holder = new ViewHolder();
            /*得到各个控件的对象*/                    
            holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
            holder.text = (TextView) convertView.findViewById(R.id.ItemText);
            holder.title.setText(data.get(position).getTitle());
            holder.text.setText(data.get(position).getContent());
            convertView.setTag(holder);//绑定ViewHolder对象                 
		 }
         else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            holder.title.setText(data.get(position).getTitle());
            holder.text.setText(data.get(position).getContent());
        }
		return convertView;
	}
	
	public final class ViewHolder{
	    public TextView title;
	    public TextView text;
	    public Button   bt;
	}
	
}
