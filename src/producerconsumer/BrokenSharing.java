package producerconsumer;

public class BrokenSharing {
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
		BrokenSharing bs = new BrokenSharing();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					bs.increment();
					if (!bs.isValid()) {
						System.err.println("PRODUCER INVALID!!!!");
					}
				}
			}
		}).start();

		new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					if (!bs.isValid()) {
						System.err.println("INVALID!!!!");
					}
				}
			}
		}).start();
	}
}
