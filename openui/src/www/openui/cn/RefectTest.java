package www.openui.cn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefectTest {
	
	public void Test(String str, String str2){
		System.out.println(str+str2);
	}
	

	public static void main(String[] arg) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		RefectTest test = new RefectTest();
		//Method mtd = RefectTest.class.getMethod("Test" ,String.class,String.class);
		//
		//mtd.invoke(test, "123","abd");
		test.firstLetterUp("asd");
	}
	
	public List<Object> autoInsertData(ResultSet rs, String[] columns, Class className) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		List result = new ArrayList();
		while(rs.next()){
			Object instance = className.newInstance();
			for(String column:columns){
				if(column!=null&&!"".equals(column)){
					Method method = className.getMethod("set"+firstLetterUp(column), String.class);
					method.invoke(instance, rs.getString(column));
					
				}
			}
			result.add(instance);
		}
		return null;
		
	}
	
	public String firstLetterUp(String str){
		if(str!=null&&!"".equals(str)){
			Character first = Character.toUpperCase(str.charAt(0));
			str = str.replaceFirst(""+str.charAt(0), ""+first);
			
		}
		System.out.println(str);
		return str;
	}

}
