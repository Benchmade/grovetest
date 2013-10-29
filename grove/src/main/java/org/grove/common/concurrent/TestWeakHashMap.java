package org.grove.common.concurrent;

import java.util.WeakHashMap;


public class TestWeakHashMap {

	public static void main(String[] args) {
		
		WeakHashMap<String,String> map = new WeakHashMap<String, String>(10240);
		//HashMap<String, String> map = new HashMap<String, String>(10240);
		
		for(int i=0;i<10000000;i++){
			map.put(i+"", i+"");
		}
		
		
		System.out.println(map.size());
	}
	
}
