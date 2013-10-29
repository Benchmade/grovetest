package org.grove.common.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDeadLock {

	public synchronized void a2(int i) {
		System.out.println("+++++++++++" + i);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("***********" + i);
	}

	public static void main(String[] args) {
		TestDeadLock t = new TestDeadLock();
		ExecutorService ex = Executors.newFixedThreadPool(2);
		ex.execute(new A(t,1));
		ex.execute(new A(t,2));
		ex.shutdown();
	}

}


class A implements Runnable {
	
	TestDeadLock i;
	int z;
	public A(TestDeadLock i,int z) {
		this.i = i;
		this.z = z;
	}
	
	@Override
	public void run() {
		i.a2(z);
	}

}
