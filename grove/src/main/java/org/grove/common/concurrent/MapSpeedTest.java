package org.grove.common.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试hashmap和concurrenthashmap的速度相差多少
 * 注释掉没有一起测试,是因为会导致下游测试数据变快.
 * 实际性能相差不多,没有达到相差一个数量级的差别,相差30%左右的性能
 * @author xiaolin.mxl
 *
 */
public class MapSpeedTest {

	public static void main(String[] args) {

		/*ConcurrentHashMap<Integer, Integer> chm = new ConcurrentHashMap<>(1024 * 1024);
		long chs = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			chm.put(i, i);
		}
		System.out.println(System.currentTimeMillis() - chs);
		
		
		long chsRead = System.currentTimeMillis();
		for(int i=0;i<1000000;i+=100){
			chm.get(i);
		}
		System.out.println(System.currentTimeMillis() - chsRead);*/
		
		
		
		
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>(1024 * 1024);
		long hs = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			hashMap.put(i, i);
		}
		System.out.println(System.currentTimeMillis() - hs);
		
		long chsRead = System.currentTimeMillis();
		for(int i=0;i<1000000;i+=100){
			hashMap.get(i);
		}
		System.out.println(System.currentTimeMillis() - chsRead);
	}

}
