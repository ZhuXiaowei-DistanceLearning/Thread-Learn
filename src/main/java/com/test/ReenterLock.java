package com.test;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();

	public static int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 100; j++) {
			lock.lock();
			if(lock.tryLock()){
				System.out.println(i);
			}
			try {
				i++;
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLock tl = new ReenterLock();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

}
