package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class University {

	public static <E> List<E> getByCriterion(Iterable<E> input, Predicate<E> criterion) {
		List<E> rv = new ArrayList<>();
		for (E s : input) {
			if (criterion.test(s)) {
				rv.add(s);
			}
		}
		return rv;
	}

	public static void main(String[] args) {
		Set<Student> roster = new HashSet<>();
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
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

	}

}
