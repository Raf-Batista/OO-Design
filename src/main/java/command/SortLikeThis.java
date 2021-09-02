package command;

import java.util.*;

class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  public Student(String name, double gpa, String ... courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = Arrays.asList(courses);
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    // Proxy
    return Collections.unmodifiableList(courses);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}

class OrderByName implements Comparator<Student> {
//  compare( int a, int b ) {
//    return a - b;
//  }
  @Override
  public int compare(Student o1, Student o2) {
//    String name1 = o1.getName();
//    String name2 = o2.getName();
//    // incomplete, since Albert and Axel count as "same position"
//    return name1.charAt(0) - name2.charAt(0);
    return o1.getName().compareTo(o2.getName());
  }
}

public class SortLikeThis {
  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("-------------------");
  }
  public static void main(String[] args) {
    List<Student> roster = new LinkedList<>();
    roster.add(new Student("Jim", 2.8, "Journalism"));
    roster.add(new Student("Fred", 3.2, "Math", "Physics"));
    roster.add(new Student("Sheila", 3.8, "Math", "Physics", "Quantum Mechanics", "Astrophysics"));

    showAll(roster);
    roster.sort(new OrderByName());
    showAll(roster);
//    roster.sort( (a, b) -> b.getCourses().size() - a.getCourses().size() );
    Comparator<Student> descendingByCourseCount =
        (a, b) -> Integer.compare(b.getCourses().size(), a.getCourses().size());
    roster.sort(descendingByCourseCount);
    showAll(roster);
  }
}
