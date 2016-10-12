package streamstuff;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Averager {

	public static void main(String[] args) {
		Supplier<double[]> sda = () -> new double[2];
		BiConsumer<double[], Double> accumulate = 
				(bucket, value) -> {
					bucket[0] += value;
					bucket[1]++;
				};
		BiConsumer<double[], double[]> combine = 
				(bucket, other) -> {
					bucket[0] += other[0];
					bucket[1] += other[1];
				};

		double [] data = Stream.<Double>generate(() -> ThreadLocalRandom.current().nextDouble())
			.limit(100_000)
			.collect(sda, accumulate, combine);
		System.out.printf("Sum: %f, Count: %f, Mean: %f\n", 
				data[0], data[1], data[0]/data[1]);
	}
}
