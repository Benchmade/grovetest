package org.grove.common.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class TestAtomicInteger {

	public static AtomicInteger ai = new AtomicInteger();
	
	public static class XXX extends Thread{
		@Override
		public void run() {
			for(int i=0;i<100000;i++){
				ai.decrementAndGet();
			}
		}
	}
	
	public static class ADD extends Thread{
		@Override
		public void run() {
			for(int i=0;i<100000;i++){
				ai.incrementAndGet();
			}
		}
	}
	
	@Test
	public void test()throws Exception{
		
		XXX x = new XXX();
		x.start();
		ADD a = new ADD();
		a.start();
		
		Thread.sleep(10000);
		
		System.out.println(ai.get());
	}
	
}
