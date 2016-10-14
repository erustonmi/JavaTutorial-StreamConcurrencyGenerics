package compfuture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

public class CFExample {
	public static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ie) {
		}
	}

	public static CompletableFuture<String> getFileContents(final String filename) {
		final CompletableFuture<String> cfs = new CompletableFuture<>();
		AsynchronousFileChannel afc = null;
		try {
			afc = AsynchronousFileChannel.open(Paths.get(filename), StandardOpenOption.READ);	
		} catch (IOException ioe) {
			cfs.completeExceptionally(ioe);
			return cfs;
		}
		
		ByteBuffer bb = ByteBuffer.allocate(4096);
		afc.read(bb, 0, new Object[]{afc, bb}, new CompletionHandler<Integer, Object[]>(){

			@Override
			public void completed(Integer result, Object[] attachment) {
				AsynchronousFileChannel afc = (AsynchronousFileChannel)(attachment[0]);
				ByteBuffer bb = (ByteBuffer)(attachment[1]);
				System.out.println("Read " + result + " bytes!");
				bb.flip();
				CharBuffer cb = Charset.defaultCharset().decode(bb);
				cfs.complete(cb.toString());
				try {
					afc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, Object[] attachment) {
				System.out.println("It broke!!!!");
				AsynchronousFileChannel afc = (AsynchronousFileChannel)(attachment[0]);
				cfs.completeExceptionally(exc);
				try {
					afc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		return cfs;
	}

//	public static CompletableFuture<String> getFileContents(final String filename) {
//		final CompletableFuture<String> cfs = new CompletableFuture<>();
//		
//		new Thread(()-> {
//			delay(1000);
//			cfs.complete("This is the contents of the file: " + filename);
//		}).start();
//		return cfs;
//	}
	
	public static void main(String[] args) throws Throwable {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(()-> {
			delay(1000);
			return "a.txt";
		});
		System.out.println("Stage One configured");
		cf = cf.thenCompose(CFExample::getFileContents);
		System.out.println("Fake file reade configured");
		cf = cf.thenApply(x -> {
			delay(1000);
			return x + " Crunched again ";
		});
		System.out.println("Stage two configured");
		cf = cf.exceptionally(e -> "uh oh, stuff broke, " + e.getMessage());
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
