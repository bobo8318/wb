package cn.openui.opentask.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import cn.openui.model.Cards;
import cn.openui.opentask.R;

public class AtyCardListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Cards> data;
	
	public AtyCardListAdapter(Context context){
		//this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}
	public void setData(List<Cards> data){
		this.data = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(data!=null)
			return data.size();
		else 
			return 0;
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
           
           holder.title.setText(data.get(position).getName());
           holder.text.setText(data.get(position).getSerial());
           
           convertView.setTag(holder);//绑定ViewHolder对象                 
		 }
        else{
           holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
           holder.title.setText(data.get(position).getName());
           holder.text.setText(data.get(position).getSerial());
       }
		return convertView;
	}

	public final class ViewHolder{
	    public TextView title;
	    public TextView text;
	    public Button   bt;
	}
}
