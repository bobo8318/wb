package org.hao.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CommonTools {

	public static void showList(List list){
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().toString());
		}
	}
	
	public static String ListToString(List list){
		StringBuffer sb = new StringBuffer();
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			sb.append(iterator.next().toString());
			sb.append(",");
		}
		return sb.toString();
	}
	/**
	 * sin 2013-8-13
	 * 屏蔽关键字
	 * @param source
	 * @return 经过处理的字符串
	 */
	public static String StringParser(String source){
		String[] strban = Parameter.WORDSBAN.split(",");
		for(int i=0;i<strban.length;i++){
			source.replaceAll(strban[i], "**");
		}
		return source;
	}
	/**
	 * sin 2013-9-4
	 * 取时间  用于文件重命名
	 */
	public static String FileRenameByTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String filename = sdf.format(new Date());
		return filename;
	}
	/**
	 * sin 2016-10-27
	 * 
	 */
	public static String getRate(String son, String mother, String fmt){
		if(fmt==null||"".equals(fmt))
			fmt = "#.00";
		DecimalFormat df = new DecimalFormat(fmt);
		int sonInt=0,motherInt=0;
		if(son!=null&&!"".equals(son.trim())){
			sonInt = Integer.parseInt(son.trim());
		}
		if(mother!=null&&!"".equals(mother.trim())){
			motherInt = Integer.parseInt(mother.trim());
		}
		
		if(sonInt==0||motherInt==0) return "0";
		String result = df.format(((double)sonInt/motherInt)*100);
		return result;
	}
	
	public static String firstLetterUp(String str){
		if(str!=null&&!"".equals(str)){
			Character first = Character.toUpperCase(str.charAt(0));
			str = str.replaceFirst(""+str.charAt(0), ""+first);
			
		}
		return str;
	}

	public static String getPinYinFirst(String str){
		//char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		//没有声调
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		char[] ch = str.trim().toCharArray();
		StringBuffer buffer = new StringBuffer("");
		try {
			for (int i = 0; i < ch.length; i++) {
				// unicode，bytes应该也可以.
				if (Character.toString(ch[i]).matches("[\u4e00-\u9fa5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							ch[i], defaultFormat);
					buffer.append(temp[0].trim().charAt(0));// :结果"?"已经查出，但是音调是3声时不显示myeclipse8.6和eclipse
				} else {
					//buffer.append(Character.toString(ch[i]));
					continue;
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
