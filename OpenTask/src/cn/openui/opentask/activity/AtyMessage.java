package cn.openui.opentask.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import cn.openui.opentask.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AtyMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_message);
		List<String> data = new ArrayList<String>();
		data.add("11111");
		data.add("22222");
		data.add("33333");
		
		MyAdapter adapter = new MyAdapter();
		adapter.setData(data);
		
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_msg);
		recyclerView.setAdapter(adapter);
		
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
		
		
	}
	
	public class MyAdapter extends RecyclerView.Adapter{
		
		private class MyViewHolder extends RecyclerView.ViewHolder{
			
			private View root;
			private TextView msgContent;
			private TextView date;
			
			public MyViewHolder(View itemView) {
				super(itemView);
				// TODO Auto-generated constructor stub
				root = itemView;
				msgContent = (TextView) root.findViewById(R.id.msgContent);
				date = (TextView) root.findViewById(R.id.msgDate);
			}
			
			public TextView getMsg(){
				return msgContent;
			}
			public TextView getDate(){
				return date;
			}
			
		}
		
		private List data;
		public void setData(List data){
			this.data = data;
		}
		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public void onBindViewHolder(ViewHolder vh, int positon) {
			// TODO Auto-generated method stub
			MyViewHolder myvh = (MyViewHolder)vh;
			myvh.getMsg().setText(data.get(positon).toString());
			myvh.getDate().setText(data.get(positon).toString());
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
			// TODO Auto-generated method stub
			 View view = View.inflate(viewGroup.getContext(), R.layout.aty_msg, null);
		        // 创建一个ViewHolder
			 MyViewHolder holder = new MyViewHolder(view);
		     return holder;
		}
		
	}
	
}
