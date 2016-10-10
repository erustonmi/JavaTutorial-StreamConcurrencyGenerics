package student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class University {

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
	}

}
