package org.grove.common.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QueueTest {
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(90);
	
	public static void main(String[] args) throws Exception{
		
		/*Thread t2 = new Thread(new Consumer());
		Thread t1 = new Thread(new Producer(t2));
		t1.start();
		t2.start();*/
		/*ExecutorService es = Executors.newFixedThreadPool(5);
		es.submit(new Consumer());
		es.submit(new Producer());
		es.shutdown();
		es.awaitTermination(3, TimeUnit.SECONDS);*/
		
		System.out.println((0|4)|128);
		
	}

	static class Producer implements Runnable {

		public void run() {
			for(int i=0;i<100;i++){
				try {
					queue.put(i+"");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Producer done");
		}

	}
	
	static class Consumer implements Runnable {

		public void run() {
			String str;
			try {
				while(!Thread.currentThread().isInterrupted()){
					str = queue.take();
					System.out.println(str);
				}
			} catch (InterruptedException e) {
			}
			System.out.println("Consumer done");
		}

	}

	
}
