package pub.imba.util;


import java.util.HashMap;
import java.util.Map;

public class ResultEntry<T> {

    private int status;
	private String msg;

	private String param0;
	private String param1;
	private T data;


	public static final int SUCCESS = 200;

	public static final int SYS_ERROR = 301;

	public static final int TIME_OUT = 401;
	public static final int LOGIN_OUT = 402;

	public static final int SMS_ERROR = 501;

	public static final int USER_REGISTED = 601;

	public static final int USER_DENY = 701;

	public static final int ERROR_PARAM = 801;


	public ResultEntry(int status, String msg){
		this.status = status;
		if(msg!=null) {
			this.msg = msg;
		}
	}


	public ResultEntry(){
		this(0,null);
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getParam0() {
		return param0;
	}

	public void setParam0(String param0) {
		this.param0 = param0;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public boolean ok() {
		return this.status == SUCCESS;
	}
}
