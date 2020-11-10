/*Lu Henry-Mitchell
11-9-20
6th period */

public class BubbleSort
{
  public BubbleSort()
  {

  }

  public static void main (String[] args)
  {
    BubbleSort test = new BubbleSort();
    int[] toBeSorted = test.randomArray(10);
    double start;
    double end;
    int[] sorted;

    System.out.println("Array to be sorted:");
    test.printArray(toBeSorted);
    start = System.nanoTime();
    sorted = test.sort(toBeSorted);
    end = System.nanoTime();
    System.out.println("Sorted array: ");
    test.printArray(sorted);
    System.out.println("Sort time: " + (end-start)/1000000000 + " seconds"); //since nanotime
  }

  public int[] sort(int[] myArray) //method to sort array w bubble sort
  {
    int temp = 0;
    for(int i = 0; i<myArray.length; i++)
    {
      for(int k = 0; k<myArray.length-i-1; k++) //since going to i+1
      {
        if(myArray[k]>myArray[k+1]) //checks if one spot ahead is greater
        {
          temp = myArray[k]; //and if so swaps values
          myArray[k] = myArray[k+1];
          myArray[k+1] = temp;
        }
      }
    }
    return myArray;
  }

  public int[] randomArray(int size) //method to create random array
  {
    int[] random = new int[size];
    for(int a = 0; a<size; a++)
    {
      random[a] = (int)(Math.random()*100);
    }
    return random;
  }

  public void printArray(int[] toBePrinted) //method to print array
  {
    for(int j = 0; j <toBePrinted.length; j++)
    {
      System.out.print(toBePrinted[j] + " ");
    }
    System.out.println();
  }
}
