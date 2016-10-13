package exceptionhider;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

interface EFunction<T,U,E extends Exception> {
	U apply(T t) throws E;
	
	static <T,U,E extends Exception> Function<T,U> wrap(EFunction<T,U,E> fn, Supplier<U> otherwise) {
		return x -> {
			try {
				return fn.apply(x);
			} catch (Throwable t) {
				System.err.println("FAILED! " + t.getMessage());
				return otherwise.get();
			}
		};
	}
}


public class ExceptionHider {
	public static void main(String[] args) {
		Stream.of("a.txt", "b.txt", "c.txt")
		.flatMap(EFunction.wrap(x->Files.lines(Paths.get(x)), Stream::empty))
		.forEach(System.out::println);
	}
}
