
import java.util.*;

public class ArrayListExample
{
  public static void main (String[] args)
  {
    ArrayList<String> test = new ArrayList<String>();
    test.add("hello");
    test.add("does");
    test.add("this");
    test.add("work");

    for(int i=0; i<test.size(); i++)
    {
      System.out.println(test.get(i));
    }
  }
}
