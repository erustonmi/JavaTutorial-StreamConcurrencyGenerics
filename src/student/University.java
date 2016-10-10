package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class University {

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
		
	}

}
