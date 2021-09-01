package factory;

import java.util.HashMap;
import java.util.Map;

public class Course {
  private static Map<String, Course> pool = new HashMap<>();
  private String name;

  private Course(String name) {
    this.name = name;
  }

  // Factory--use a method instead of a constructor
  // Pooled objects must be immutable, but can a) save memory
  // b) can use identity as a proxy for value (i.e. use == instead
  // of .equals(other)
  // this kind of pool is key part of Gang of Four "Flyweight"
  // in their pattern objects have a large piece that is immutable
  // and shared across a smaller number of instances, AND a mutable
  // instance specific part.
  public static Course of(String name) {
    Course rv = pool.get(name);
    if (rv == null) {
      rv = new Course(name);
      pool.put(name, rv);
    }
    return rv;
  }

  @Override
  public String toString() {
    return "Course{" +
        "name='" + name + '\'' +
        '}';
  }
}
