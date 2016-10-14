package compfuture;

import java.util.concurrent.CompletableFuture;

public class CFExample {
	public static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ie) {
		}
	}

	public static void main(String[] args) throws Throwable {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(()-> {
			delay(1000);
			return "This is the beginning";
		});
		System.out.println("Stage One configured");
		cf = cf.thenApply(x -> {
			delay(1000);
			return x + " Crunched again ";
		});
		System.out.println("Stage two configured");
		cf = cf.thenApply(x -> {
			delay(1000);
			return x + " post processed";
		});
		System.out.println("Stage thee configured");
		CompletableFuture<Void> cfv = cf.thenAccept(System.out::println);
		System.out.println("Final stage configured");
		cfv.get();
		System.out.println("Jobs all finished, bye!");
	}

}
