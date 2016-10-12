package streamstuff;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NormalCurve {
	public static final int SCREEN_WIDTH = 100;
	public static final int SAMPLE_SIZE = 1_000_000_000;
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		Map<Integer, Long> table = Stream.generate(()-> 
				ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7) 
				+ ThreadLocalRandom.current().nextInt(1, 7))
		.parallel()
		.limit(SAMPLE_SIZE)
		.collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));
		long end = System.nanoTime();
		
		int max = table.values().stream().max(Comparator.naturalOrder()).get().intValue();
		
		table.entrySet().stream()
		.sorted(Map.Entry.comparingByKey())
		.forEach(e -> System.out.printf("%2d : %s\n", 
				e.getKey(), 
				Stream.generate(() -> "*")
					.limit(e.getValue() * SCREEN_WIDTH / max)
					.collect(Collectors.joining())))
		;
		
		System.out.printf("Time was %8.6f ms", (end - start)/1_000_000F);
	}
}
