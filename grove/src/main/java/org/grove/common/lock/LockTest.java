package org.grove.common.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(2);
		LockTest lt = new LockTest();
		ex.submit(new PO(lt.lock));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ex.submit(new PO1(lt.lock));
		ex.shutdown();
	}
}

class PO implements Runnable {

	ReentrantLock lock;

	public PO(ReentrantLock lock) {
		this.lock = lock;

	}

	@Override
	public void run() {
		lock.lock();
		for (int i = 0; i < 10; i++) {
			System.out.println("1 : " + i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
}


class PO1 implements Runnable {

	ReentrantLock lock;

	public PO1(ReentrantLock lock) {
		this.lock = lock;

	}

	@Override
	public void run() {
		boolean l;
		try {
			l = lock.tryLock(5,TimeUnit.SECONDS);
			System.out.println(l);
			if(l){
				for (int i = 0; i < 10; i++) {
					System.out.println("2 : " + i);
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.unlock();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}