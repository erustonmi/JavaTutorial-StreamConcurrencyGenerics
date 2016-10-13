package threadstuff;

public class BrokenRunnables {
	static class MyJob implements Runnable {
		private int i = 0;
		public void run() {
			System.out.println(Thread.currentThread().getName() + " started");
			for ( ; i < 10_000; i++) {
				System.out.println("i is " + i + " " 
					+ Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable r = new MyJob();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
		System.out.println("Main exiting");
	}

}
