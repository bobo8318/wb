package org.hao.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper{
	private final int INT_COLUMN = 1;
	private final int String_COLUMN = 2;

	public <T> List<T> autoInsertData(ResultSet rs, String[][] columns, Class<T> className){
		List<T> result = new ArrayList<T>();
		try {
			while(rs.next()){
				T instance = className.newInstance();
				for(String[] column:columns){
					
					int type = 0;
					String columnName = "";
					if(column.length>=1){
						if(column.length==1){
							type = String_COLUMN;
							columnName = column[0];
						}else{
							type = Integer.parseInt(column[0]);
							columnName = column[1];
						}
						
						if(columnName!=null&&!"".equals(columnName)){
							Method method;
							//System.out.println("insert data@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
							switch(type){
							case INT_COLUMN:
								method = className.getMethod("set"+CommonTools.firstLetterUp(columnName), int.class);
								method.invoke(instance, rs.getInt(columnName));
								break;
							case String_COLUMN:
								method = className.getMethod("set"+CommonTools.firstLetterUp(columnName), String.class);
								method.invoke(instance, rs.getString(columnName));
								break;
							default:
								method = className.getMethod("set"+CommonTools.firstLetterUp(columnName), String.class);
								method.invoke(instance, rs.getString(columnName));
								//System.out.println("insert data------------------------");	
						}
					}
					}
				}
				result.add(instance);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}

	public int tableNNameRouter(String splite, int mod){
		mod = mod <0?0:mod;
		if(splite!=null&&!"".equals(splite)){


			return Integer.parseInt(splite)%mod;
		} else {
			return -1;
		}
	}
	
	
}
