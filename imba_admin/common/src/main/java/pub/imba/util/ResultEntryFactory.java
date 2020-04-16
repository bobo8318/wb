package pub.imba.util;

public class ResultEntryFactory {

	public static ResultEntry getInstance(){
		return new ResultEntry();
	}

	public static ResultEntry getTimeOutEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "time out";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.TIME_OUT);
		return entry;
	}

	public static ResultEntry getSuccessEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "success";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.SUCCESS);
		return entry;
	}

	public static ResultEntry getParamInvalidate(String s) {
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(s)){
			s = "invalidate param";
		}
		entry.setMsg(s);
		entry.setStatus(ResultEntry.ERROR_PARAM);
		return entry;
	}

	public static ResultEntry getSMSErrorEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "sms error";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.SMS_ERROR);
		return entry;
	}

	public static ResultEntry getErrorEntry(String msg){
		ResultEntry entry = new ResultEntry();
		if(TextUtil.isEmpty(msg)){
			msg = "sys error";
		}
		entry.setMsg(msg);
		entry.setStatus(ResultEntry.SYS_ERROR);
		return entry;
	}
}
