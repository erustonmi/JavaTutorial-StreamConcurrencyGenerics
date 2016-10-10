package student;

import java.util.HashSet;
import java.util.Set;

public class University {

	public static void main(String[] args) {
		Set<Student> roster = new HashSet<>();
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Sheila", 3.8F, new String[] {"Biology", "Physics", "Bio-chemistry"}));
		roster.add(Student.fromNameGpaCourseList("Jim", 2.9F, "Art"));
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
	
		for (Student s : roster) {
			System.out.println(s);
		}
		
		
	}

}
