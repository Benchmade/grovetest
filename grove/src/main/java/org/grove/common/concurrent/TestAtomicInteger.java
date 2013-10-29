package org.grove.common.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class TestAtomicInteger {

	
	@Test
	public void test(){
		
		AtomicInteger ai = new AtomicInteger();
		
		for(int i=0;i<100;i++){
			System.out.println(ai.incrementAndGet());
			System.out.println(" - " + ai.get());
		}
		
		AtomicLong al = new AtomicLong();
		for(int j=0;j<100;j++){
			al.addAndGet(j);
		}
		System.out.println(al.get());
		
	}
	
}
