package com.test;

public class demo2 extends Thread {
	volatile boolean stopme = false;
	public static User u = new User(0, "0");

	public void stopMe() {
		stopme = true;
	}

	@Override
	public void run() {
		while (true) {
			if (stopme) {
				System.out.println("exit by stop me");
				break;
			}
			synchronized (u) {
				int v = (int) (System.currentTimeMillis() / 1000);
				u.setId(v);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				u.setName(String.valueOf(v));
			}
			Thread.yield();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interruted");
						break;
					}
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}
}
