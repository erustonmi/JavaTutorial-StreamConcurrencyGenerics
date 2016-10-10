package student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

interface StudentCriterion {
	boolean test(Student s);
}

public class University {

	public static List<Student> getStudentsByCriterion(Iterable<Student> input, StudentCriterion criterion) {
		List<Student> rv = new ArrayList<>();
		for (Student s : input) {
			if (criterion.test(s)) {
				rv.add(s);
			}
		}
		return rv;
	}
	
	public static void main(String[] args) {
		Set<Student> roster = new HashSet<>();
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Sheila", 3.8F, new String[] {"Biology", "Physics", "Bio-chemistry"}));
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
		System.out.println("Smart students" + getStudentsByCriterion(roster, (s)-> s.getGpa() > 3.0F));
		
	}

}
