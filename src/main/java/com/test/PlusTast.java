package com.test;

public class PlusTast implements Runnable {
	static volatile int i = 0;

	@Override
	public void run() {
		for (int k = 0; k < 10000; k++) {
			i++;
		}
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new PlusTast());
			threads[i].start();
		}

		for (int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(i);
	}

}
