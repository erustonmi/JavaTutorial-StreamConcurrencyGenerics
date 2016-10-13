package lowlevelthreads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueWithLibrary {
	public static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ie) {
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> q = new ArrayBlockingQueue<>(10);

		Runnable producer = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10_000; i++) {
					try {
						q.put(i);
					} catch (InterruptedException ie) {
					}
				}
				for (int i = 10_000; i < 20_000; i++) {
					try {
						q.put(i);
					} catch (InterruptedException ie) {
					}
					delay(1);
				}
			}
		};

		Runnable consumer = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10_000; i++) {
					delay(1);
					int read = 0;
					try {
						read = q.take();
					} catch (InterruptedException ie) {
					}
					if (i != read)
						System.err.println("BROKEN! expected " + i + " got " + read);
				}
				for (int i = 10_000; i < 20_000; i++) {
					int read = 0;
					try {
						read = q.take();
					} catch (InterruptedException ie) {
					}
					if (i != read)
						System.err.println("BROKEN! expected " + i + " got " + read);
				}
				System.out.println("Consumer ended...");
			}
		};

		Thread prod = new Thread(producer);
		prod.start();
		Thread cons = new Thread(consumer);
		cons.start();

		try {
			cons.join();
			prod.join();
		} catch (InterruptedException ie) {
		}
		System.out.println("Finished");
	}
}
