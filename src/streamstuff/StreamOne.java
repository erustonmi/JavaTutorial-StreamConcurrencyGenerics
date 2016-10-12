package streamstuff;

import java.util.LinkedList;
import java.util.List;

import student.Student;

public class StreamOne {

	public static void main(String[] args) {
		List<Student> roster = new LinkedList<>();
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Alan", 2.8F, "Math", "Physics", "Chemistry"));
		roster.add(Student.fromNameGpaCourseList("Sarah", 3.0F, "Math", "Physics"));
		roster.add(Student.fromNameGpaCourseList("Alice", 3.0F, "Math", "Physics", "Biology"));
		roster.add(Student.fromNameGpaCourseList("Denise", 3.0F, "Math", "Physics","Biology", "Organic Chemistry"));
		roster.add(
				Student.fromNameGpaCourseList("Sheila", 3.8F, new String[] { "Biology", "Physics", "Bio-chemistry" }));
		roster.add(Student.fromNameGpaCourseList("Jim", 3.9F, "Art"));
		roster.add(Student.fromNameGpaCourseList("Fred", 2.8F, "Math", "Physics"));

		
		roster.stream()
		.peek(System.out::println)
		.filter(s->s.getCourses().size() > 2)
		.peek(s->System.out.println("stage 2: " + s))
		.map(s-> s.getName() + " : " + s.getGpa())
//		.forEach(System.out::println)
		;
		
//		.forEach(s->System.out.println(s.getName()));

//		.map(Student::getName)
//		.forEach(System.out::println);
		
		System.out.println(" ------------------ ");
		roster.stream()
		.flatMap(s->s.getCourses().stream())
		.distinct()
		.sorted()
		.forEachOrdered(System.out::println);
	}

}
