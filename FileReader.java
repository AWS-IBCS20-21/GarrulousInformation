/*Lu Henry-Mitchell
11-11-20
6th period */

import java.util.*;
import java.io.*;

public class FileReader
{
  public static void main (String[] args)
  {
    try {
      File f = new File("TotalRecall.txt");
      Scanner scanny = new Scanner(f);
      while(scanny.hasNextLine())
      {
        System.out.println(scanny.nextLine());
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }
}
