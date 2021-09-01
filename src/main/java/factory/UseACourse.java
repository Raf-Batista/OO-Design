package factory;

public class UseACourse {
  public static void main(String[] args) {
//    Course c = new Course("Math");
//    Course c2 = new Course("Math");
    Course c = Course.of("Math");
    Course c2 = Course.of("Math");
    System.out.println("courses are equal? " + (c == c2));
  }
}
