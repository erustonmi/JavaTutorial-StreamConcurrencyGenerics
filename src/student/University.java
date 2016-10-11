package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class University {

	public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
		return x -> first.test(x) && second.test(x);
	}
	
	public static <E> Predicate<E> or(Predicate<E> first, Predicate<E> second) {
		return x -> first.test(x) || second.test(x);
	}
	
	public static <E> Predicate<E> not(Predicate<E> first) {
		return x -> !first.test(x);
	}
	
	public static <E> Comparator<E> andThenCompare(Comparator<E> first, Comparator<E> second) {
		return (a,b) -> {
			int t1 = first.compare(a, b);
			if (t1 == 0) {
				return second.compare(a, b);
			} else {
				return t1;
			}
		};
	}
	
	public static <E> List<E> getByCriterion(Iterable<E> input, Predicate<E> criterion) {
		List<E> rv = new ArrayList<>();
		for (E s : input) {
			if (criterion.test(s)) {
				rv.add(s);
			}
		}
		return rv;
	}

	public static void showIterable(Iterable i) {
		for (Object o : i) {
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		Set<Student> roster = new HashSet<>();
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Alan", 2.8F, "Math", "Physics", "Chemistry"));
		roster.add(Student.fromNameGpaCourseList("Sarah", 3.0F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Alice", 3.0F, "Math", "Physics", "Biology"));
		roster.add(Student.fromNameGpaCourseList("Denise", 3.0F, "Math", "Physics","Biology", "Organic Chemistry"));
		roster.add(
				Student.fromNameGpaCourseList("Sheila", 3.8F, new String[] { "Biology", "Physics", "Bio-chemistry" }));
		roster.add(Student.fromNameGpaCourseList("Jim", 3.9F, "Art"));
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));

		for (Student s : roster) {
			System.out.println(s);
		}

		List<Student> lRoster = new ArrayList<>(roster);

		System.out.println("Before:");
		System.out.println(lRoster);
		lRoster.sort(Student.getGpaComparator());
		System.out.println("After:");
		System.out.println(lRoster);

		System.out.println("-----------");
		System.out.println("Smart students" + getByCriterion(roster, (s) -> s.getGpa() > 3.0F));

		System.out.println(
				"> " + getByCriterion(Arrays.asList("Banana", "Orange", "Mango", "Kiwi"), (s) -> s.length() > 5));

		System.out.println("-----------");
		Comparator<Student> gpa = Student.getGpaComparator();
		Comparator<Student> courseCount = (a,b) -> a.getCourses().size() - b.getCourses().size();
		Comparator<Student> combined = andThenCompare(gpa, courseCount);
		lRoster.sort(combined);
		showIterable(lRoster);
		
		System.out.println("-----------");
		Predicate<Student> smart = s -> s.getGpa() >= 3.0F;
		Predicate<Student> keen = s -> s.getCourses().size() > 3;
		Predicate<Student> notSmart = not(smart);
		Predicate<Student> overachiever = and(smart, keen);
		
		System.out.println("smart: " + getByCriterion(lRoster, smart));
		System.out.println("notSmart: " + getByCriterion(lRoster, notSmart));
		System.out.println("keen: " + getByCriterion(lRoster, keen));
		System.out.println("overachiever: " + getByCriterion(lRoster, overachiever));
		
		System.out.println("-----------------------------");
		
		Comparator<Student> byName = Comparator.comparing(s -> s.getName());
		Comparator<Student> byCourseCount = Comparator.comparingInt(s -> s.getCourses().size());
		Comparator<Student> byGpa = Comparator.comparingDouble(s -> s.getGpa());
		Comparator<Student> byGpaCount = byGpa.thenComparing(byCourseCount);
		
		lRoster.sort(byGpaCount.reversed());
		lRoster.forEach(s->System.out.println(s));
		

		Comparator<Student> c1 = Comparator.comparingDouble(s -> s.getGpa());
		
		lRoster.sort(c1.thenComparingInt(s -> s.getCourses().size()));
		
		lRoster.sort(Comparator.<Student>comparingDouble(s -> s.getGpa()).thenComparingInt(s -> s.getCourses().size()));
		
	}
}
