package org.hao.tools;

import java.util.HashMap;
import java.util.Map;

public class DataCache {

	private static Map<String, Object> CACHE;
	
	public static Map<String, Object> getCache(){
		if(CACHE==null)
			CACHE = new HashMap<String, Object>();
		return CACHE;
	}
}
