/*Lu Henry-Mitchell
11-13-20
6th period*/

import java.util.*;
import java.io.*;

public class FileParser
{

  public FileParser()
  {

  }

  public static void main (String[] args)
  {

    FileParser test = new FileParser();

    try {
      File f = new File("TotalRecall.txt");
      Scanner scanny = new Scanner(f);
      String fullText = "";
      while (scanny.hasNextLine())
      {
        fullText += scanny.nextLine();
      }
      String[] words = fullText.split(" ");
      

      System.out.println("The shortest word is " + test.findShortestWord(words));
      System.out.println("The longest word is " + test.findLongestWord(words));
      System.out.println("The most frequent word is " + test.findMostCommon(words));
      System.out.println("The average word length is " + test.avgLength(words));


    } catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }

  public int avgLength(String[] words)
  {
    int sum = 0;
    for(int i =0; i < words.length; i++)
    {
      sum+= words[i].length();
    }
    return (sum/words.length);
  }

  public String findMostCommon(String[] words)
  {
    int[] frequencies = new int[words.length];
    for(int i = 0; i < words.length; i++) //for each element in array
    {
      for(int j = i+1; j < words.length; j++) //compares to every element after
      {
        if(words[i].equals(words[j]) && words[i].length() > 0)
        {
          frequencies[i]++; //and if finds one the same, adds 1 to corresponding place in frequencies array
        }
      }
    }

    int greatestFrequency = 0;
    int greatestFrequencyIndex = 0;
    for(int n = 0; n < frequencies.length; n++) //finds which index had the greatest frequency
    {
      if(frequencies[n] > greatestFrequency)
      {
        greatestFrequency = frequencies[n];
        greatestFrequencyIndex = n;
      }
    }
    return words[greatestFrequencyIndex];
  }

  public String findShortestWord(String[] words)
  {
    int smallest = words[1].length();
    String smallestWord = words[1];
    for(int i = 0; i < words.length; i++)
    {
      if(words[i].length() < smallest && words[i].length() > 0)
      {
        smallest = words[i].length();
        smallestWord = words[i];
      }
    }
      return smallestWord;
  }

  public String findLongestWord(String[] words)
  {
    int longest = words[1].length();
    String longestWord = "";
    for(int j = 0; j < words.length; j++)
    {
      if(words[j].length() > longest)
      {
        longest = words[j].length();
        longestWord = words[j];
      }
    }
    return longestWord;
  }
}
