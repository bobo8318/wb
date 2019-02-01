package cn.openui.opentask.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.openui.model.Cards;
import cn.openui.opentask.R;
import cn.openui.opentask.service.MainService;


public class AtyCard extends Activity{
	private MainService service;
	private TextView text;
	private AtyCardListAdapter atlAdapter;
	private ListView lv;
	private int currentPage;
	private int totalPage;
	private int allCount;
	private String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_card);
		atlAdapter = new AtyCardListAdapter(AtyCard.this);
		text = (TextView)findViewById(R.id.card_pageInfo);
		service = new MainService();
		currentPage = 1;
		key = "";
		getCardsData(currentPage, key);
		lv = (ListView) findViewById(android.R.id.list);
		lv.setAdapter(atlAdapter);
		
		//设置scroll
		/*lv.setOnScrollListener(new OnScrollListener(){
			private int lastItemIndex;//当前ListView中最后一个Item的索引  
	            //当ListView不在滚动，并且ListView的最后一项的索引等于adapter的项数减一时则自动加载（因为索引是从0开始的）  
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE  
                        && lastItemIndex == atlAdapter.getCount() - 1) {  
                   	 //加载数据代码，此处省略了
					getCardsData(++currentPage);
                }  
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
					int totalItemCount) {
				// TODO Auto-generated method stub
				//ListView 的FooterView也会算到visibleItemCount中去，所以要再减去一  
                lastItemIndex = firstVisibleItem + visibleItemCount - 1 -1;  
				
			}
			
		});*/
		 Button btnNext = (Button)findViewById(R.id.btn_card_next);
		 btnNext.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentPage = currentPage+1>totalPage?totalPage:currentPage+1;
				//Log.d("next on click",""+currentPage);
				getCardsData(currentPage, key);
				atlAdapter.notifyDataSetChanged();
			}
			 
		 });
		 
		Button btnPre = (Button)findViewById(R.id.btn_card_pre);
		 btnPre.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentPage = currentPage-1<1?1:currentPage-1;
				getCardsData(currentPage, key);
				atlAdapter.notifyDataSetChanged();
			}
			 
		 });
		 
		 
		 Button searchBtn = (Button)this.findViewById(R.id.btn_card_search);
		 searchBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et = (EditText)findViewById(R.id.card_key);
				key = et.getText().toString();
				getCardsData(1, key);
			}
			 
		 });
		
		
	}
	
	private void getCardsData(int page, String key){
		service.getCards(page,key,new MainService.successCallBack() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				try {
					JSONObject jsonobject = new JSONObject(result);
					
					//设置数据
					
					List<Cards> data = new ArrayList<Cards>();
					 Iterator it = jsonobject.keys(); 
					 while(it.hasNext()){
						 String key = (String) it.next();
			             String value = jsonobject.getString(key);
			             if(key.equals("totalpage")){
			            	 totalPage = Integer.parseInt(value);
			             }
			             if(key.equals("allCount")){
			            	 allCount = Integer.parseInt(value);
			             }if(key.equals("page")){
			            	 currentPage = Integer.parseInt(value);
			             }
			             
			             if(key.equals("data")){
				             JSONArray array = jsonobject.getJSONArray(key);
				             for(int i=0;i<array.length();i++){
				            	 JSONObject obj = array.getJSONObject(i);  
								 Cards card = new Cards(obj.getString("cardName"),obj.getString("cardSerial"),obj.getString("cardDesc"));
								 data.add(card);
								 //Log.d("www.openui.cn cardName",obj.getString("cardName"));
							}
			             }
					 }	
					text.setText("总"+totalPage+"页  当前"+currentPage+"页");
					atlAdapter.setData(data);
					
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}, new MainService.failCallBack() {
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				Toast.makeText(AtyCard.this, "数据加载失败", Toast.LENGTH_LONG);
			}
		});
		
	}
}
