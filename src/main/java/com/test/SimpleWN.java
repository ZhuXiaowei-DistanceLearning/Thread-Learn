package com.test;

public class SimpleWN {
	final static Object object = new Object();
	final static Object object2 = new Object();
	
	public static class T1 extends Thread {

		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis() + ":T1 start!");
				try {
					System.out.println(System.currentTimeMillis()
							+ ":T1 wait for object!");
					object.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + ":T1 end!");
			}
		}

	}
	
	public static class T3 extends Thread {

		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis() + ":T3 start!");
				try {
					System.out.println(System.currentTimeMillis()
							+ ":T3 wait for object!");
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + ":T3 end!");
			}
		}

	}

	public static class T2 extends Thread {

		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis() + ":T2 start!");
				object.notify();
				System.out.println(System.currentTimeMillis() + ":T2 end!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		Thread t3 = new T3();
		t1.start();
		t3.start();
		t2.start();

	}
}
