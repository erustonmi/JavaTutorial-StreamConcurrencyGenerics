package streamstuff;

import java.util.stream.IntStream;

public class ReduceOne {

	public static void main(String[] args) {
		IntStream.iterate(1, v -> v + 1)
		.limit(10)
		.reduce((a,b) -> a + b)
		.ifPresent(System.out::println);
		//		.forEach(System.out::println);
	}

}
