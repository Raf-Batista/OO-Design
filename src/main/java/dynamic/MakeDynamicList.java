package dynamic;

import java.io.FileReader;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;

class MoreThanTen implements Predicate<Double> {
  public MoreThanTen() {}
  @Override
  public boolean test(Double aDouble) {
    return aDouble > 10;
  }
}

class IsEven implements Predicate<Double> {
  public IsEven() {}
  @Override
  public boolean test(Double aDouble) {
    return aDouble % 2 == 0;
  }
}

class IsNegative implements Predicate<Double> {
  public IsNegative() {}
  @Override
  public boolean test(Double aDouble) {
    return aDouble < 0;
  }
}

public class MakeDynamicList {
  public static void main(String[] args) throws Throwable {
    Properties testsRequired = new Properties();
    testsRequired.load(new FileReader("Tests.properties"));
    testsRequired.forEach((k, v) -> System.out.println(k + " : " + v));

    double d = 9 + (int)(Math.random() * 4);
    System.out.println("Testing number: " + d);

    for (Object name : testsRequired.values()) {
      String className = (String)name;
      Class<?> cl = Class.forName(className);
      Predicate<Double> theTest = (Predicate<Double>)cl.getConstructor().newInstance();
      boolean passes = theTest.test(d);
      System.out.println("Test " + cl.getName() + " passes? " + passes);
    }

  }
}
