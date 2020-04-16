package pub.imba.util;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

	public static final int STRICT = 0;
	public static final int SIMILAR = 1;

	public static final int BIGLETTER = 10;

	/**
	 * sin 2013-9-4
	 */
	public static String FileRenameByTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String filename = sdf.format(new Date());
		return filename;
	}

	public static String firstLetterUp(String str){
		if(str!=null&&!"".equals(str)){
			Character first = Character.toUpperCase(str.charAt(0));
			str = str.replaceFirst(""+str.charAt(0), ""+first);

		}
		return str;
	}

	/**
	 *
	 * @param keys
	 * @return
	 */
	public static List<String> keySperator(String keys){
		System.out.println("keys:"+keys);
		List<String> result = new ArrayList<String>();
		if(keys!=null&&!"".equals(keys)){
			//if(keys.indexOf(",")>=0){//
			String[] key = keys.trim().split(",");
			for(int i=0;i<key.length;i++){
				if((key[i]).trim().indexOf(" ")>=0){//
					String[] result_key = (key[i]).trim().split(" ");
					//
					for(int j=0;j<result_key.length;j++){

						if(!"".equals(result_key[j].trim())){
							System.out.println(result_key[j].trim());
							result.add(result_key[j].trim());
						}
					}
				}else{

					if(!"".equals(key[i].trim())){
						System.out.println(key[i].trim());
						result.add(key[i].trim());
					}
				}
			}
			//}

			return result;
		}else {
			return null;
		}
	}

	public static int isNumber(String value){
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException e){
			return -1;
		}
	}

	public static boolean isEmpty(String text){
		if(text==null||"".equals(text)||"null".equals(text)){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isEmpty(Object text){
		String test = String.valueOf(text);
		return isEmpty(test);
	}

	public static boolean inArrayStr(String source, String dest, int mode, String... splites){
		if(!isEmpty(source)&&!isEmpty(dest)){
			String[] splite_str = splite(dest,splites);
			if(splite_str!=null&&splite_str.length>0){
				for(String dd:splite_str){
					if(mode == STRICT){
						if(source.equals(dd)){
							return true;
						}
					}else if(mode == SIMILAR){
						if(source.contains(dd)){
							return true;
						}
					}

				}
			}
		}

		return false;
	}

	public static boolean inArrayStr(String source, String[] dest, int mode){
		if(!isEmpty(source)&&null!=dest){
			for(String splite:dest){
				if(mode == STRICT){
					if(source.equals(splite)){
						return true;
					}
				}else if(mode == SIMILAR){
					if(source.contains(splite)){
						return true;
					}
				}
			}
		}

		return false;
	}

	public static String[] splite(String source,String...splites){
		if(!isEmpty(source)){
			if(splites.length>0){
				List<String> result = new ArrayList<String>();
				result.add(source.trim());
				for(String splite:splites){//
					List<String> newresult = new ArrayList<String>();
					for(String str:result){//
						String[] arrays = str.split(splite);
						for(String array:arrays){
							if(!isEmpty(array.trim())){
								newresult.add(array.trim());
							}
						}
					}
					result.clear();
					result.addAll(newresult);
				}
				return result.toArray(new String[result.size()]);
			}else{
				String[] result = new String[1];
				result[0] = source;
				return result;
			}
		}else {
			return null;
		}
	}

	public static boolean beginWith(String source, int mode){
		boolean flag = false;
		switch (mode){
			case BIGLETTER:
				if(!isEmpty(source)&&source.length()>2){
					char first = source.charAt(0);
					if(Character.isUpperCase(first)){
						flag = true;
					}

				}
				break;
			default:
		}

		return flag;
	}

	public static String getParentPath(String currentPath) {
		if(isEmpty(currentPath)){
			return "";
		}else if(currentPath.contains("/")){
			return currentPath.substring(0,currentPath.lastIndexOf("/"));
		}else {
			return "";
		}

	}

	public static String[] splitPath(String path){
		if(!TextUtil.isEmpty(path)){
			if(!path.contains("/")) {
				return new String[]{path};
			}else{
				if(path.startsWith("/")) {
					path = path.substring(1);
				}
				return path.trim().split("/");
			}
		}else {
			return null;
		}

	}
    /*public static String hash(String value){
		int arraySize = 11113;
		int hashcode = 0;
		for(int i=0;i<value.length();i++){

		}
	}*/

	public static String getRandomCoder(int length){
		String randomChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMDOPQRSTUVWXYZ1234567890";
		if(length>randomChar.length()) {
			return "";
		}else{
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<length;i++){
				int index = (int)Math.floor(Math.random()*randomChar.length());
				//System.out.println(randomChar.length()+" "+index);
				sb.append(randomChar.charAt(index));
			}
			return sb.toString();
		}
	}

	/**
	 * @param idcard
	 */
	public static String standardIdcarNo(String idcard){

		if(!isEmpty(idcard)){
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(idcard);
			idcard = m.replaceAll("").trim();
			if(idcard.length()==17) {
				return idcard + "x";
			}else {
				return idcard;
			}
		}else {
			return "";
		}

	}

	public static String trimString(String idcard){

		if(!isEmpty(idcard)){
			return idcard.
					replaceAll("	", "").
					replace("\r", "").
					replace("\n", "").
					trim();

		}else {
			return "";
		}

	}

	public  static int positionInArray(List<String> list, String value, int mode){
		for(int i=0;i<list.size();i++){
			//System.out.println(value+":"+list[i]);
			if(mode == SIMILAR){
				if(value.contains(list.get(i)) || list.get(i).contains(value)){
					return i;
				}
			}else{
				if(value.equals(list.get(i))){
					return i;
				}
			}

		}
		return -1;
	}

	public static String getFileNameByPath(String file) {
		if(!isEmpty(file)){
			if(!file.contains("/")) {
				return file;
			}else {
				return file.substring(file.lastIndexOf("/") + 1);
			}
		}else {
			return null;
		}
	}

	/**
	 *
	 * @param:
	 * @return:
	 * @auther: sin
	 * @date: 2018/9/30 22:53
	 **/
	public static String getRequestPathData(Map<String,String> datas){
		/*if(datas!=null){
			return Joiner.on("&").withKeyValueSeparator("=").join(datas);
		}else {
			return "";
		}*/
		return "";
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString; //含html标签的字符串
		String textStr ="";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); //过滤script标签

			p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); //过滤style标签

			p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签

			textStr = htmlStr;

		}catch(Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;//返回文本字符串
	}


}
