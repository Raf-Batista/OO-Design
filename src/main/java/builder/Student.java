package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student() {}

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  public static StudentBuilder builder() {
    return new StudentBuilder();
  }

  public static class StudentBuilder {
//    private String name;
//    private double gpa;
//    private List<String> courses;
    private Student target = new Student();
    {
      target.courses = new ArrayList<>();
    }

    public StudentBuilder name(String n) {
      if (n == null) throw new IllegalArgumentException("name must not be null");
      target.name = n;
      return this;
    }

    public StudentBuilder gpa(double gpa) {
      target.gpa = gpa;
      return this;
    }

    public StudentBuilder course(String course) {
      target.courses.add(course);
      return this;
    }

    public Student build() {
      // validate!!!!

      target.courses = Collections.unmodifiableList(target.courses);
      Student result = target;
      target = null;
      // OR clone the target object, and be able to stamp out multiples
      // with small variations!?
      return result;
    }
  }

  public static void main(String[] args) {
    Student s = Student.builder()
        .name("Fred")
        .gpa(3.2)
        .course("Math")
        .course("Physics")
        .build();
    System.out.println(s);
  }
}
