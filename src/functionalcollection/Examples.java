package functionalcollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Examples {

	public static void main(String[] args) {
		List<String> ls = new ArrayList<>(Arrays.asList("Fred", "Jim", "Sheila", "Toni", "Alice", "Algernon"));

		// Iterator behavior:
		ls.forEach(s->System.out.println(s));
		
		// Collection behavior
		ls.removeIf(s->s.charAt(0) == 'F');
		ls.forEach(s->System.out.println(s));

		// List behavior
		ls.replaceAll(s->s.toUpperCase());
		ls.forEach(s->System.out.println(s));

		Map<Integer, String> mis = new HashMap<>();
		
		mis.compute(3, (k,v)-> v == null ? "New entry for " + k : v + ", updated.");
		mis.forEach((k,v)-> System.out.println(k + " : " + v));

		mis.compute(3, (k,v)-> v == null ? "New entry for " + k : v + ", updated.");
		mis.forEach((k,v)-> System.out.println(k + " : " + v));
		
		mis.compute(3, (k,v)-> v == null ? "New entry for " + k : v + ", updated.");
		mis.forEach((k,v)-> System.out.println(k + " : " + v));

		mis.computeIfAbsent(3, k-> "New entry for " + k);
		mis.forEach((k,v)-> System.out.println(k + " : " + v));
		
		mis.computeIfAbsent(4, k-> "New entry for " + k);
		mis.forEach((k,v)-> System.out.println(k + " : " + v));

		mis.replaceAll((k,v)-> v.toUpperCase());
		mis.forEach((k,v)-> System.out.println(k + " : " + v));
	}

}
