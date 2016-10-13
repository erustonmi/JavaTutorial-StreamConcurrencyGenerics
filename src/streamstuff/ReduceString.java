package streamstuff;

import java.util.stream.IntStream;

public class ReduceString {

	public static void main(String[] args) {
		IntStream.iterate(1, x -> x + 1)
			.parallel()
			.unordered()
			.limit(1000)
			.mapToObj(x -> "" + x)
			.reduce((a,b) -> a + "," + b)
			.ifPresent(System.out::println);
			;
	}

}
