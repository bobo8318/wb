package pub.imba.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
	public static String getString(Object[] source, String split){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<source.length;i++){
			if(i>0) {
				sb.append(split);
			}
			sb.append(source[i]);
		}
		return sb.toString();
	}
	/**
	 * sin 2013-8-13
	 *
	 * @param source
	 * @return
	 */
	public static String StringParser(String source){
		String[] strban = "".split(",");
		for(int i=0;i<strban.length;i++){
			source.replaceAll(strban[i], "**");
		}
		return source;
	}
	/**
	 * sin 2013-9-4
	 *
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
		if(fmt==null||"".equals(fmt)) {
			fmt = "#.00";
		}
		DecimalFormat df = new DecimalFormat(fmt);
		int sonInt=0,motherInt=0;
		if(son!=null&&!"".equals(son.trim())){
			sonInt = Integer.parseInt(son.trim());
		}
		if(mother!=null&&!"".equals(mother.trim())){
			motherInt = Integer.parseInt(mother.trim());
		}

		if(sonInt==0||motherInt==0){ return "0";}
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



	public static String jsonParser(String s){
		StringBuffer sb = new StringBuffer();
		if(s!=null){
			for (int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				switch (c){
					case '\"':
						sb.append("\\\"");
						break;
					case '\\':
						sb.append("\\\\");
						break;
					case '/':
						sb.append("\\/");
						break;
					case '\b':
						sb.append("\\b");
						break;
					case '\f':
						sb.append("\\f");
						break;
					case '\n':
						sb.append("\\n");
						break;
					case '\r':
						sb.append("\\r");
						break;
					case '\t':
						sb.append("\\t");
						break;
					default:
						sb.append(c);
				}
			}
		}
		return sb.toString();
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
					//store to list
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

	private static String getKey(String key){
		if(key.trim().indexOf(":")>=0&&key.trim().length()>2)
			return key.trim().substring(key.trim().indexOf(":")+1, key.trim().length());
		return "";
	}

	public static int isNumber(String value){
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException e){
			return -1;
		}
	}

	public static int isPersonID(String value){
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException e){
			return -1;
		}
	}

	public static String getRandomCoder(int length){
		String randomChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMDOPQRSTUVWXYZ1234567890";
		if(length>randomChar.length())
			return "";
		else{
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
	 *
	 * @param dir
	 * @param extendName
	 * @param childrenDir
	 * @param filt
	 * @param path
	 * @return
	 */

	public static List<String> getFileNames(String dir,boolean extendName, boolean childrenDir, String filt, boolean path,FileCallBack callback){
		File root = new File(dir);
		List<String> result = new ArrayList<String>();
		if(!root.isDirectory()) {
			//System.out.println("dir:"+dir);
			result.add(dir);
			return result;
		}
		//if()
		File[] files = root.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				//System.out.println(files[i].getName());
				if(childrenDir){//
					result.addAll(getFileNames(files[i].getAbsolutePath(),extendName,childrenDir,filt,path,callback));
				}else{
					continue;
				}
			}else{

				File file = files[i];
				String filename = file.getName();
				String Pathstr = file.getAbsolutePath();

				//FileInputStream fileStream = new FileInputStream(file);
				long modifiedTime = file.lastModified();
				//System.out.println(DateTools.getDate(new Date(modifiedTime), "yyyy-MM-dd hh:mm:ss"));

				if(filt!=null&&!"".equals(filt)){//
					if(filename.contains(filt)){
						if(extendName){
							if(path) {
								result.add(Pathstr);
							}else{
								result.add(filename);
							}
							if(callback!=null){
								callback.dofile(Pathstr);
							}
						}
						else{
							result.add(filename.substring(0,filename.indexOf(".")));
						}
					}
				}else{
					if(callback!=null){
						callback.dofile(Pathstr);
					}
					if(extendName){
						if(path) {
							result.add(Pathstr);
						}else {
							result.add(filename);
						}
					}
					else{
						result.add(filename.substring(0,filename.indexOf(".")));
					}
				}
			}
		}

		return result;
	}


	/**
	 *
	 * @return
	 */
	public static String getExtendName(String filename){
		if(filename.indexOf(".")>0) {
			return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		}else {
			return "";
		}
	}

	/**
	 *
	 */
	public  static int positionInArray(String[] list, String value){
		for(int i=0;i<list.length;i++){
			//System.out.println(value+":"+list[i]);
			if(value.equals(list[i])){
				return i;
			}
		}
		return -1;
	}



    public static interface FileCallBack{
		public void dofile(String file);
	}

	public static String CuteString(String delHTMLTag, int i, String string) {
		// TODO Auto-generated method stub
		if(delHTMLTag.length()<=i) {
			return delHTMLTag;
		}else{
			String cutestr = delHTMLTag.substring(0,i);
			return cutestr+string;
		}

	}

	public <T> T[] arrayRemove (T[] array,int index){

		if(index < 0 || array == null || index>array.length){
			return array;
		}else{
			for(int i =index;i<array.length-1;i++){
				array[i] = array[i+1];
			}
			T[] newarray = Arrays.copyOf(array,array.length-1);
			return newarray;
		}

	}


}
