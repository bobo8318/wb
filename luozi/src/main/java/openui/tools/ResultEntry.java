package openui.tools;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ResultEntry {

	private Integer status;
	private String msg;
	private Map<String, Object> datas;
	
	
	public static final Integer SUCCESS = 200;
	public static final Integer TIME_OUT = 401;
	public static final Integer LOGIN_OUT = 402;
	public static final Integer SYSTEM_ERROR = 501;
	
	public static final Integer USER_REGISTED = 601;
	
	public static final Integer USER_DENY = 701;
	
	public ResultEntry(Integer status, String msg){
		datas = new HashMap<String,Object>();
		if(status!=null)
			this.status = status;
		if(msg!=null)
		this.msg = msg;
	}
	
	public void clear(){
		this.status = null;
		this.msg = null;
		this.datas.clear();
	}
	
	public ResultEntry(){
		this(null,null);
	}
	
	public void addData(String key, Object data){
		datas.put(key, data);
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getJsonData(){
		datas.put("msg", msg);
		datas.put("status", status);
		return JSONObject.toJSONString(datas);
	}
}
