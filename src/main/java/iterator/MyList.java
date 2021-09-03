package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<E> implements Iterable<E> {
  private E[] data = (E[])new Object[10];
  private int count = 0;

  public void add(E e) {
    data[count++] = e;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MyList[");
    for (int i = 0; i < count; i++) {
      sb.append(data[i]).append(", ");
    }
    sb.setLength(sb.length() - 2);
    sb.append("]");
    return sb.toString();
  }
  @Override
  public Iterator<E> iterator() {
    return new MyIterator();
  }
  private class MyIterator implements Iterator<E> {
    int progress = 0;

    private MyIterator(){}

    public boolean hasNext() {
      return progress < count;
    }

    public E next() {
      if (hasNext()) {
        return data[progress++];
      } else {
        throw new NoSuchElementException();
      }
    }
  }

  public static void main(String[] args) {
    MyList<String> mls = new MyList<>();
    mls.add("Fred");
    mls.add("Jim");
    mls.add("Sheila");
    System.out.println(mls);

//    Iterator<String> is = mls;
//    while (is.hasNext()) {
//      System.out.println(">> " + is.next());
//    }
//
//    Iterator<String> is2 = mls;
//    while (is.hasNext()) {
//      System.out.println(">> " + is.next());
//    }

    Iterator<String> is = mls.iterator();
//    while (is.hasNext()) {
//      System.out.println(">> " + is.next());
//    }
//
    Iterator<String> is2 = mls.iterator();
//    while (is2.hasNext()) {
//      System.out.println("++ " + is2.next());
//    }

    System.out.println("is -> " + is.next());
    System.out.println("is2                    -> " + is2.next());
    System.out.println("is2                    -> " + is2.next());
    System.out.println("is -> " + is.next());
    System.out.println("is -> " + is.next());
    System.out.println("is2                    -> " + is2.next());
  }
}
