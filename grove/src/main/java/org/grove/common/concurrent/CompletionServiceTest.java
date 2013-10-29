package org.grove.common.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {
	public static void main(String[] args) throws Exception {
		ExecutorService ex = Executors.newFixedThreadPool(5);
		ArrayBlockingQueue<Future<String>> aq = new ArrayBlockingQueue<Future<String>>(10);
		CompletionService<String> cp = new ExecutorCompletionService<String>(ex, aq);
		cp.submit(new Call(10));
		cp.submit(new Call(1000));
		cp.submit(new Call(500));
		cp.submit(new Call(400));
		cp.submit(new Call(200));
		cp.submit(new Call(20));

		ex.shutdown();

		for (int i = 0; i < 6; i++) {
			System.out.println(cp.take().get());
		}

	}
}

class Call implements Callable<String> {
	int time;

	public Call(int time) {
		this.time = time;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(time);
		return String.valueOf(time);
	}
}