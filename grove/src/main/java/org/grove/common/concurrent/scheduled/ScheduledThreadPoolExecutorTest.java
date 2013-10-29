package org.grove.common.concurrent.scheduled;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 测试ScheduledThreadPoolExecutor执行定时任务,
 * @author xiaolin.mxl
 *
 */
public class ScheduledThreadPoolExecutorTest {

	/**
	 * 测试只执行一次.
	 * @throws Exception
	 */
	@Test
	public void onceExecutor() throws Exception{
		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(2);
		ScheduledFuture sf = stpe.scheduleAtFixedRate(new TimeTaskThread(), 1L, 1L, TimeUnit.SECONDS);
		ScheduledFuture sf2 = stpe.scheduleAtFixedRate(new TimeTaskThread2(), 1L, 1L, TimeUnit.SECONDS);
		sf2.get();
	}

	/**
	 * 测试ScheduledThreadPoolExecutor的poolsize设置不同的值,执行的情况,如果pool=1那么必须等待上一个执行完,下一个任务才能执行.
	 * @author xiaolin.mxl
	 *
	 */
	public static class TimeTaskThread implements Runnable{
		@Override
		public void run() {//run的内容必须加上try,如果没有try,抛出runtimeexceptioin后,任务将会和终止
			try{
				Thread.sleep(3000);
				System.out.println("xxx");
				//throw new NullPointerException("youwenti");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static class TimeTaskThread2 implements Runnable{
		@Override
		public void run() {
			System.out.println("ooo");
		}
	}
	
	
	

}
