package openui.tools;

public class TextUtil {

	public static boolean isEmpty(String text){
		if(text==null||"".equals(text)||"null".equals(text)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isEmpty(Object text){
		if(text==null||"".equals(text.toString())||"null".equals(text)){
			return true;
		}else{
			return false;
		}
	}
}
