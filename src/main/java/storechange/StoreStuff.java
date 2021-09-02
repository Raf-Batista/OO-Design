package storechange;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StoreStuff {
  public static void showAll(List<String> als) {
    for (String s : als) {
      System.out.println("> " + s);
    }
  }
  public static void main(String[] args) {
    List<String> names = new ArrayList<>();
    names.add("Fred");
    names.add("Jim");
    names.add("Sheila");
    showAll(names);

    List<String> names2 = new LinkedList<>();
    names.add("Fred");
    names.add("Jim");
    names.add("Sheila");
    showAll(names);
  }
}
