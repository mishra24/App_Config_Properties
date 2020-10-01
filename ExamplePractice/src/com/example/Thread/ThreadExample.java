package com.example.Thread;

public class ThreadExample {

	public static void main(String[] args) {
		
		TestThread th =  new TestThread(12);
		th.start();
		
		TestThreadInterfaceImpl thimple = new TestThreadInterfaceImpl(44);
		
		Thread t = new Thread(thimple);
		t.start();
		
		System.out.println("still main thread executing ");
		
		
	}

}


class TestThread extends Thread{
	
	int num;
	TestThread(int n){
		this.num=n;
	}
	
	public void run() {
		System.out.println( "print num  calue  " + num);
	}
	
	
}

class TestThreadInterfaceImpl implements Runnable{
	
	int num;
	TestThreadInterfaceImpl(int n){
		this.num=n;
	}
	

	@Override
	public void run() {
		System.out.println( "print num  calue  " + num);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}