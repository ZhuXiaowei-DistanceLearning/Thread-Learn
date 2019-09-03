package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	public static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyTask my = new MyTask();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 10; i++) {
//			pool.submit(my);
		}
			service.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(System.currentTimeMillis()/1000);
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, 0, 2, TimeUnit.SECONDS);
	}
}
