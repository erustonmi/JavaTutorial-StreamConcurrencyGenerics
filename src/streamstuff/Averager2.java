package streamstuff;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class MeanInProgress {
	private double sum = 0;
	private int count = 0;

	public double getSum() {
		return sum;
	}

	public double getMean() {
		return sum / count;
	}

	public int getCount() {
		return count;
	}

	public void addData(double d) {
		sum += d;
		count++;
	}

	public void combine(MeanInProgress mip) {
		sum += mip.sum;
		count += mip.count;
	}
}

public class Averager2 {

	public static void main(String[] args) {
		MeanInProgress mip = 
				Stream.<Double>generate(() -> ThreadLocalRandom.current().nextDouble())
				.limit(100_000)
				.collect(MeanInProgress::new, // () -> new MeanInProgress()
						MeanInProgress::addData, // (bucket, nextValue) -> bucket.addData(nextValue)
						MeanInProgress::combine); // (bucket, otherbucket) -> bucket.combine(otherBucket)
		System.out.printf("Sum: %f, Count: %d, Mean: %f\n", 
				mip.getSum(), mip.getCount(), mip.getMean());
	}
}
