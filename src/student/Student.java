package student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Student implements Comparable<Student> {
	private String name;
	private float gpa;
	private Set<String> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public Set<String> getCourses() {
		return courses;
	}

	public void setCourses(Set<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", gpa=" + gpa + ", courses=" + courses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private Student() {
	}

	public static Student fromNameGpaCourseList(String name, float gpa, String... courses) {
		Student rv = new Student();
		rv.name = name;
		rv.gpa = gpa;
		rv.courses = new HashSet<>(Arrays.asList(courses));
		return rv;
	}

	@Override
	public int compareTo(Student o) {
		return this.name.compareTo(o.name);
	}

	public static Predicate<Student> getSmartnessPredicate(final float threshold) {
//		threshold += 0.1F;
//		int s;
		return s -> s.getGpa() > threshold;
	}
	
	// private static final Comparator<Student> gpaComparator = new
	// /*GpaComparator();
	// private static class GpaComparator implements */ Comparator<Student>() {
	//
	// @Override
	// public int compare(Student o1, Student o2) {
	// System.out.println("gpa comp...");
	// return Float.compare(o1.getGpa(), o2.getGpa());
	// }
	// };

	private static final Comparator<Student> gpaComparator = 
			(o1, o2) -> Float.compare(o1.getGpa(), o2.getGpa());

//	private static final Comparator<Student> gpaComparator = (Student o1, Student o2) -> {
//		System.out.println("gpa comp...");
//		return Float.compare(o1.getGpa(), o2.getGpa());
//	};
//
	// private static final Comparator<Student> gpaComparator = /*new*/
	// /*GpaComparator();
	// private static class GpaComparator implements */ /*Comparator<Student>()
	// {*/
	//
	// /*@Override
	// public int compare*/(Student o1, Student o2) -> {
	// System.out.println("gpa comp...");
	// return Float.compare(o1.getGpa(), o2.getGpa());
	// }
	// /*}*/;

	public static Comparator<Student> getGpaComparator() {
		return gpaComparator;
	}

	// private static class GpaComparator implements Comparator<Student> {
	//
	// @Override
	// public int compare(Student o1, Student o2) {
	// System.out.println("gpa comp...");
	// return Float.compare(o1.getGpa(), o2.getGpa());
	// }
	//
	// }
}
