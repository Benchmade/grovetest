package org.grove.common.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);  
		Runnable task1 = new Runnable() {  
		    @Override  
		    public void run(){  
		    	for(int i=0;i<10;i++){
		        	System.out.println("task1 :" + i);
		        	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }
		    }  
		}; 
		
		Runnable task2 = new Runnable() {  
		    @Override  
		    public void run(){  
		        for(int i=0;i<10;i++){
		        	System.out.println("task2 :" + i);  
		        	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }
		    }  
		};
		
		Runnable task3 = new Runnable() {  
		    @Override  
		    public void run(){  
		        for(int i=0;i<20;i++){
		        	System.out.println("task3 :" + i);  
		        	try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }
		    }  
		};  
		
		executorService.execute(task1);
		executorService.execute(task2);
		
		executorService.shutdown();
		
		try {
			while(!executorService.awaitTermination(2, TimeUnit.SECONDS)){
				System.out.println("hehe");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("************");
		  
		/*executor = Executors.newScheduledThreadPool(10);  
		ScheduledExecutorService scheduler = (ScheduledExecutorService) executor;  
		scheduler.scheduleAtFixedRate(task, 10, 10, TimeUnit.SECONDS);  */
	}
	
}