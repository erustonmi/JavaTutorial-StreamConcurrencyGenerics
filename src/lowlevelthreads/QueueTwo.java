package lowlevelthreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueTwo<E> {
	private int count = 0;
	private E[] data = (E[]) (new Object[10]);
	private ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public void put(E item) {
		System.err.println("putting " + item);
		lock.lock(); try {
			while (count >= data.length) {
				try {
					notFull.await();
				} catch (InterruptedException ie) {
					// nothing to do, loop round and try again
				}
			}
			data[count++] = item;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
		System.err.println("put finished");
	}

	public E take() {
		System.err.println("in take...");
		lock.lock(); try {
			while (count <= 0) {
				try {
					notEmpty.await();
				} catch (InterruptedException ie) {
					// nothing to do, loop round and try again
				}
			}

			E rv = data[0];
			System.arraycopy(data, 1, data, 0, --count);;
			notFull.signal();
			System.err.println("take returning " + rv);
			return rv;
		} finally {
			lock.unlock();
		}
	}

	public static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ie) {
		}
	}

	public static void main(String[] args) {
		QueueTwo<Integer> q = new QueueTwo<>();

		Runnable producer = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10_000; i++) {
					q.put(i);
				}
				for (int i = 10_000; i < 20_000; i++) {
					q.put(i);
					delay(1);
				}
			}
		};

		Runnable consumer = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10_000; i++) {
					delay(1);
					int read = q.take();
					if (i != read)
						System.err.println("BROKEN! expected " + i + " got " + read);
				}
				for (int i = 10_000; i < 20_000; i++) {
					int read = q.take();
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
