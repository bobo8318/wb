package pub.imba.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {


	/**
	 *
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
	 */

	public static String getDate(Date date, String format){
		if(date == null)
			date = new Date();
		if(format ==null || "".equals(format))
			format = "yyyy-MM-dd";

		SimpleDateFormat smf = new SimpleDateFormat(format);
		return smf.format(date);
	}

	/**
	 *
	 * @param:
	 * @return:
	 * @auther: sin
	 * @date: 2018/10/19 15:27
	 **/
	public String formatDateString(String source, String sourcefomat, String destformat){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateByString(source,sourcefomat));
		return getDate(calendar.getTime(),destformat);
	}
	/**
	 */
	public static String getWeekByDate(Date date){
		if(date!=null){
			String[] weekdaysName = {"sunday","monday","tuesday","wensday","thirsday","friday","satday"};
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int week = calendar.get(Calendar.DAY_OF_WEEK)-1;

			return weekdaysName[week];
		}else
			return null;
	}

	public static Date getDateByString(String date,String format){
		if(format==null || "".equals(format)) format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}

	public static Long getMillionByDate(String date, String format){
		if(TextUtil.isEmpty(format))
			format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return calendar.getTimeInMillis();
	}

	public static Long getMillionByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.getTimeInMillis();
	}

	public static Long getDateLong(Date date, String format){
		if(TextUtil.isEmpty(format))
			format = "yyyyMMddHHmmss";
		String date_str = getDate(date, format);
		return Long.valueOf(date_str);
	}

	public static String getDateByMillion(long milliondate){
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(milliondate);
		return df.format(date.getTime());
	}

	public static long getMillionByStringDate(String... data){
		String datetime = "";
		String formate = "yyyy-MM-dd HH:mm:ss";
		if(data.length >=1 ){
			datetime = data[0];

			if(TextUtil.isEmpty(datetime)){
				return 0;
			}

			if(data.length >=2 ){
				formate = data[1];
			}

			SimpleDateFormat df =  new SimpleDateFormat(formate);
			Calendar date = Calendar.getInstance();
			try {
				date.setTime(df.parse(datetime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date.getTimeInMillis();
		}else {
			return 0;
		}


	}

}
