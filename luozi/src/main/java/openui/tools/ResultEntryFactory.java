package openui.tools;

public class ResultEntryFactory {

	public static ResultEntry getInstance(){
		return new ResultEntry();
	}
	
	public static ResultEntry getTimeOutEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "登录超时，请重新登录";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.TIME_OUT);
		return entry;
	}
	
	public static ResultEntry getSuccessEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "操作成功";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.SUCCESS);
		return entry;
	}
}
