package cn.openui.aty;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import cn.openui.killhighdog.MainActivity;
import cn.openui.killhighdog.R;
import cn.openui.model.Dot;

public class BackGround extends SurfaceView implements OnTouchListener{

	private int WIDTH = 30;
	private int ROW = 10;
	private int COL = 10;
	private int BLOCKS = 12;
	private Dot[][] matrix = new Dot[COL][ROW];
	private Dot cat;
	public static int STEPS;
	private CallBack  callBack;
	private int status;
	
	private static final int START = 1;
	private static final int WIN = 2;
	private static final int LOSE = 3;
	private static final int PAUSE = 4;
	
	
	public BackGround(Context context, AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		this.getHolder().addCallback(callback);
		setOnTouchListener(this);
		init();
	}
	
	private void redraw(){
		Canvas canvas = this.getHolder().lockCanvas();
		canvas.drawColor(Color.LTGRAY);
		
		
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		for(int i=0;i<ROW;i++){
			int offset = 0;
			if(i%2==1){
				offset = WIDTH/2;
			}
			for(int j=0;j<COL;j++){
				Dot one = matrix[i][j];
				switch(one.getStatus()){
				case Dot.STATUS_ON:paint.setColor(0XFFFAA00);break;
				case Dot.STATUS_OFF:paint.setColor(0XFFEEEEEE);break;
				case Dot.STATUS_IN:paint.setColor(0XFFFF0000);break;
				case Dot.STATUS_TEST:paint.setColor(0XFFEEE000);break;
				default:;
				}
				canvas.drawOval(new RectF(one.getX()*WIDTH+offset,
										  one.getY()*WIDTH,
										  (one.getX()+1)*WIDTH+offset,
										  (one.getY()+1)*WIDTH), paint);
			}
			
		}
		//cat
		//BitmapFactory.Options options = new BitmapFactory.Options();
		//options.inJustDecodeBounds = true;
		//options.outWidth = options.outHeight = WIDTH;
		Bitmap bm = BitmapFactory.decodeResource(this.getResources(),R.drawable.hd2);
		bm = Bitmap.createScaledBitmap(bm, WIDTH, WIDTH, true);
		if(bm!=null){
			int offset = 0;
			if(cat.getY()%2==1){
				offset = WIDTH/2;
			}else{
				offset = 0;
			}
			canvas.drawBitmap(bm, cat.getX()*WIDTH+offset,cat.getY()*WIDTH,paint); 
		}
		this.getHolder().unlockCanvasAndPost(canvas);
	}
	
	private void init(){
		STEPS = 0;
		show(getResources().getString(R.string.tx0),MainActivity.MSG);
		show("步数：0",MainActivity.STEPTS);
		status = BackGround.START;
		//init dot
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				Dot one = new Dot(j,i);
				matrix[i][j] = one;
			}
			
		}
		//cat
		int catX = (COL-1)/2;
		int catY = (ROW-1)/2;
		cat = new Dot(catX, catY);
		getDot(catX, catY).setStatus(Dot.STATUS_IN);
		
		//init block
		for(int i=0;i<BLOCKS;){
			int blockX = (int) ((Math.random()*1000)%ROW);
			int blockY = (int) ((Math.random()*1000)%COL);
			Dot one = matrix[blockY][blockX];
			if(one.getStatus()==Dot.STATUS_OFF){
				one.setStatus(Dot.STATUS_ON);
				i++;
			}
			
		}
		
		
	}
	
	private boolean isEdge(Dot dot){
		int x = dot.getX();
		int y = dot.getY();
		if(x*y==0||x+1==COL||y+1==ROW)
			return true;
		else
			return false;
	}
	private Dot getNeighbour(Dot dot, int diration){
			int x = dot.getX();
			int y = dot.getY();
			int flag = y%2;
			
			switch(diration){
			case 1:return getDot(x-1,y);
			case 2:
				if(flag==0)
					return getDot(x-1,y-1);
				if(flag==1)
					return getDot(x,y-1);
			case 3:
				if(flag==0)
					return getDot(x,y-1);
				if(flag==1)
					return getDot(x+1,y-1);
			case 4:return getDot(x+1,y);
			case 5:
				if(flag==0)
					return getDot(x,y+1);
				if(flag==1)
					return getDot(x+1,y+1);
			case 6:
				if(flag==0)
					return getDot(x-1,y+1);
				if(flag==1)
					return getDot(x,y+1);
			default:break;
		
		}
		return null;
	}
	private int getDistance(Dot dot, int diration){
		int distance = 0;
		Dot ori = dot,next;
		while(true){
			//Log.i("ori", ori.getX()+":"+ori.getY());
			if(isEdge(ori)){
				return 0;
			}
			next  = this.getNeighbour(ori, diration);
			
			//Log.i("next", next.getX()+":"+next.getY());
			if(next.getStatus()==Dot.STATUS_ON){
				return distance*-1;
			}
			if(isEdge(next)){
				return ++distance;
			}
			distance++;
			ori = next;
			
		}
	}
	private void moveCat(Dot toDot){
		if(toDot == null){
			//Toast.makeText(this, "You Kill High DOG ", Toast.LENGTH_LONG).show();
			status = BackGround.WIN;
		}else{
			this.getDot(cat.getX(), cat.getY()).setStatus(Dot.STATUS_OFF);
			cat.setX(toDot.getX());
			cat.setY(toDot.getY());
			this.getDot(cat.getX(), cat.getY()).setStatus(Dot.STATUS_IN);
			if(isEdge(cat)){
				//Toast.makeText(this, " High DOG Escape", Toast.LENGTH_LONG).show();
				status = BackGround.LOSE;
			}
		}
		
	}
	
	private Dot getMoveToDot(Dot cat){
		boolean flag = false;
		int shortest = 100;//向边界方向走
		int shortestDir = 0;
		int unshortest = 0;//没有边界方向 向最远距离走
		int unshortestDir = 0;
		for(int i=1;i<7;i++){
			int distance = this.getDistance(cat, i);
			if(distance!=0)
				flag = true;
			if(distance>0){
				if(shortest>distance){
					shortest = distance;
					shortestDir = i;
				}
			}
			if(distance<0){
				if(unshortest>distance){
					unshortest = distance;
					unshortestDir = i;
				}
			}
		}
		if(!flag)
			return null;
		else{
			//Log.i("yyy", shortest+":"+unshortest);
			
			if(shortest<100){//没有边界方向
				return this.getNeighbour(cat, shortestDir);
			}
			if(unshortest<0){
				return this.getNeighbour(cat, unshortestDir);
			}
			return null;
		}
		
	}
	
	Callback callback = new Callback(){

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			redraw();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			// TODO Auto-generated method stub
			WIDTH = width/(COL+1);
			redraw();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			
		}
		
	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_UP){
			//Toast.makeText(this, event.getX()+":"+event.getY(), Toast.LENGTH_LONG).show();
			int x,y;
			y = (int) (event.getY()/WIDTH);
			if(y%2==0){
				x= (int) (event.getX()/WIDTH);
			}else{
				x= (int) ((event.getX()-WIDTH/2)/WIDTH);
			}
			if((x+1>COL)||(y+1>ROW)){
				init();
			}else{
				if(status!=BackGround.START){
					init();
				}else{
					if(getDot(x,y).getStatus()==Dot.STATUS_OFF){
						
						getDot(x,y).setStatus(Dot.STATUS_ON);
						Dot moveDot = this.getMoveToDot(cat);
						moveCat(moveDot);
						//判断游戏是否结束
						switch(status){
							case BackGround.START:
								STEPS++;
								show("步数："+STEPS,MainActivity.STEPTS);
								if(STEPS>=6)
									show(getResources().getString(R.string.tx),MainActivity.MSG);break;
							
							case BackGround.WIN:show(getResources().getString(R.string.win),MainActivity.MSG);break;
							case BackGround.LOSE:show(getResources().getString(R.string.lose),MainActivity.MSG);break;
								
						}
					}
				}
				//Dot neighbour = this.getNeighbour(getDot(x,y), 6);
				//neighbour.setStatus(Dot.STATUS_TEST);
				//int distance = this.getDistance(getDot(x,y), 2);
				//Log.i("distance:",  "diration:"+2+":"+distance);
				/*for(int i=1;i<7;i++){
					int distance = this.getDistance(getDot(x,y), i);
					Log.i("distance:",  "diration:"+i+":"+distance);
				}*/
				//Log.i("touch position",  "width:"+WIDTH+" "+event.getX()+":"+event.getY()+" "+x+":"+y);
			}
			redraw();
		}
		return true;
	}
	
	private Dot getDot(int x, int y){
		if((x+1>COL)||(y+1>ROW)||x<0||y<0){
			return null;
		}else
			return matrix[y][x];
	}

	public void setCallBack(CallBack cb) {
		// TODO Auto-generated method stub
		this.callBack = cb;
	}
	
	public static interface CallBack{//定义一个回调接口
		public void setTextView(String str, int position);
	}
	public void show(String str, int position){//回调显示
        if(this.callBack!=null) this.callBack.setTextView(str,position);
    }
	
	
}
