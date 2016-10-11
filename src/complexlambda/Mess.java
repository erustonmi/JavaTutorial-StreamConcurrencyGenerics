package complexlambda;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Mess {

	public static void doStuff(Predicate<String> ps) {
		System.out.println("doStuff(Predicate<String>)");
	}

	public static void doStuff(Function<String, Object> ps) {
		System.out.println("doStuff(Function<String, Object>)");
	}

	public static void doStuff(UnaryOperator<String> ps) {
		System.out.println("doStuff(UnaryOperator<String>)");
	}

	public static void main(String[] args) {
		doStuff(String::toString);
		
		doStuff((String x) -> x.toString());
		
		
		Function<String, Object> bos =((x) -> x.toString()); 
		doStuff(bos);

		doStuff((Function<String, Object>)((x) -> x.toString()));
		
		System.out.println(((Function<String, Boolean>)((x) -> x.length() > 3)).apply("hello"));

	}

}
