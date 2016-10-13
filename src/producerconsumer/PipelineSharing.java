package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class PipelineSharing {
	private int a, b;

	public void increment() {
		a++;
		if (a >= 10) {
			b++;
			if (b >= 10) {
				b = 0;
			}
			a = 0;
		}
	}

	public boolean isValid() {
		return a >= 0 && a < 10 && b >= 0 && b < 10;
	}

	public static void main(String[] args) {
		BlockingQueue<PipelineSharing> queue = new ArrayBlockingQueue<>(10);

		new Thread(() -> {
			int ticker = 0;
			while (true) {
				PipelineSharing ps = new PipelineSharing();
				int target = ThreadLocalRandom.current().nextInt(0, 150);
				for (int i = 0; i < target; i++) {
					ps.increment();
					if (!ps.isValid()) {
						System.err.println("INVALID!!!!");
					}
				}
				try {
					queue.put(ps);
					ps = null;
				} catch (InterruptedException ie) {
				}
				if (++ticker % 10_000 == 0) {
					ticker = 0;
					System.out.print(".");
					System.out.flush();
				}
			}
		}).start();

		new Thread(() -> {
			while (true) {
				try {
					PipelineSharing ps = queue.take();
					if (!ps.isValid()) {
						System.err.println("INVALID!!!!");
					}
				} catch (InterruptedException ie) {
				}
			}
		}).start();
	}
}
