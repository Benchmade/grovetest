package org.grove.common.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class QueueBenchmark {

	static SynchronousQueue<String>  queue = new SynchronousQueue<String>();
	
	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(10);
		long st = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			Thread a = new Thread(new Consumer());
			ex.execute(a);
		}
		for (int i = 0; i < 5; i++) {
			Thread b = new Thread(new Producer());
			ex.execute(b);
		}
		ex.shutdown();
		try {
			while (!ex.awaitTermination(10, TimeUnit.MILLISECONDS)) {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - st);
	}
	
	
	static class Consumer implements Runnable {
		
		@Override
		public void run() {
			for(int i=0;i<10000000;i++){
				queue.offer(String.valueOf(i));
			}
			queue.offer("end");
		}
		
	}
	
	
	static class Producer implements Runnable{
		@Override
		public void run() {
			String s ;
			while(!"end".equals((s=queue.poll()))){
				//System.out.println(s);
			}
			System.out.println("end");
		}
	}
}
