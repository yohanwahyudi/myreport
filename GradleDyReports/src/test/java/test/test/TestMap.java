package test.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	
	public static void main(String args[]) {
		Map<String, String> map1 = new HashMap<String,String>();
		
		map1.put("a", "str1");
		
		System.out.println(map1.get("a"));
		
		map1.put("a", "str2");
		System.out.println(map1.get("a"));
		
	}

}
