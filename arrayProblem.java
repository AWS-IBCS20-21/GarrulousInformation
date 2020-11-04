/* Lu Henry-Mitchell
11-4-20
6th period*/

import java.util.*;

public class arrayProblem
{

  
  public arrayProblem()
  {
    int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  }

  public int[] getArray()
  {
    return testArray;
  }

  public static void main (String[] args)
  {
    arrayProblem test = new arrayProblem();
    System.out.println(test.middleAverage(test.getArray));

  }

  public double middleAverage(int[] myArray)
  {
    double sum = 0;
    double avg = 0;
    int start = myArray.length/4;
    int end = myArray.length - myArray.length/4;

    for(int i = start; i < end; i++)
    {
      sum += myArray[i];
      System.out.println("index: " + i);
    }
      avg = sum/(end - start);
      return avg;

  }

}
