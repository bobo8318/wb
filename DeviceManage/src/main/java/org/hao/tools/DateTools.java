package main.java.org.hao.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	/**
	 * sin 2012-12-25
	 * @return 周期的最后一天
	 */
	public static String getLastLoopEnd(Date date, int endday, Parameter.DateFormate format){
		Calendar calendar = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfday = new SimpleDateFormat("dd");
		//SimpleDateFormat sdfmonth = new SimpleDateFormat("MM");
		String day = sdfday.format(date);
		//System.out.println("day:"+day);
		int year = 0;
		int month = 0;
		if(day!=null&&!"".equals(day)){
			if(Integer.parseInt(day)>=7){
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, 1);
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH)+1;
				//System.out.println("year:"+year+" month:"+month);
			}else{
				calendar.setTime(date);
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH)+1;
			}
		}
		if(year!=0&&month!=0){
			if(format == Parameter.DateFormate.yyyy_MM_dd)
				return year+"-"+month+"-"+endday;
			else if((format == Parameter.DateFormate.yyyy_MM)) return year+"-"+month;
			else if((format == Parameter.DateFormate.yyyy)) return ""+year;
			else if((format == Parameter.DateFormate.MM)) return ""+month;
			else if((format == Parameter.DateFormate.MM_dd)) return month+"-"+endday;
			else if((format == Parameter.DateFormate.dd)) return ""+endday;
			else
				return year+"-"+month+"-"+endday;
		}
		else return null;
	}
	
	/**
	 * 2013-3-11 sin
	 * 通过身份证号 取得生日
	 */
	public static String getBirthdayByID(final String personid, String format){
		String fmt = (format==null||"".equals(format))?"yyyy-MM-dd":format;
		if(personid==null||"".equals(personid)){
			return null;
		}else{
			/*StringBuffer sb = new StringBuffer(50);
			sb.append(personid.substring(6, 10));//year
			sb.append("-");
			sb.append(personid.substring(10, 12));//month
			sb.append("-");
			sb.append(personid.substring(12, 14));//date*/
			StringBuffer sb = new StringBuffer(fmt);
			if(sb.indexOf("yyyy")>=0) sb.replace(sb.indexOf("yyyy"), sb.indexOf("yyyy")+4, personid.substring(6, 10));
			if(sb.indexOf("MM")>=0) sb.replace(sb.indexOf("MM"), sb.indexOf("MM")+2, personid.substring(10, 12));
			if(sb.indexOf("dd")>=0) sb.replace(sb.indexOf("dd"), sb.indexOf("dd")+2, personid.substring(12, 14));

			return sb.toString();
			
		}
	}
	/**
	 * 
	 * 通过生日取年龄
	 */
	public static int getAge(final String birthday){
		if(null!=birthday&&!"".equals(birthday)){
			Date now = new Date();
			//SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			SimpleDateFormat month = new SimpleDateFormat("MM");
			
			int nowYear = Integer.parseInt(year.format(new Date()));
			int nowmonth = Integer.parseInt(month.format(new Date()));
			
			int birthdayYear = Integer.parseInt(birthday.substring(0,birthday.indexOf("-")));
			int birthdayMonth = Integer.parseInt(birthday.substring(birthday.indexOf("-")+1,birthday.lastIndexOf("-")));
			
			int age = nowmonth>birthdayMonth?(nowYear-birthdayYear):(nowYear-birthdayYear-1);
			return age;
		}else{
			return -1;
		}
	}
	
	/**
	 * 根据格式取当前日期
	 */
	
	public static String getDate(Date date, String format){
		if(date == null)
			date = new Date();
		if(format ==null || "".equals(format))
			format = "yyyy-MM-dd";
		
		SimpleDateFormat smf = new SimpleDateFormat(format);
		return smf.format(date);
	}
}
