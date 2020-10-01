package com.example.Thread;

public class SyncThreadExample {

	public static void main(String[] args) {
		
		SyncThreadClass sync = new SyncThreadClass();
		SyncThreadClass sync1 = new SyncThreadClass();
		new Thread(sync, "ONE").start();;
		new Thread(sync1, "TWO").start();
	}

}


class SyncThreadClass implements Runnable{

	SyncPrintClass p = new SyncPrintClass();
	@Override
	public void run() {
		try {
			p.printNumbers(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class SyncPrintClass {

	void printNumbers(int a) throws InterruptedException {
//		System.out.println("with thread : "+ a );
		synchronized (this) {
			for (int i = 0; i <= a; i++) {
				System.out.println(Thread.currentThread().getName() +": : " + i);
//				Thread.sleep(1000);
			}
		}
	}
}
