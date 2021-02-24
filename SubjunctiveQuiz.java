import java.util.*;
import java.io.*;

public class SubjunctiveQuiz
{
  public int numQs;
  public String[] sentences;
  public String[] ARverbStems;
  public String[] IRverbStems;
  public String[] ARsubEndings;
  public String[] IRsubEndings;
  public String[] ARindicEndings;
  public String[] IRindicEndings;
  public int correct;
  public File f;

  public SubjunctiveQuiz()
  {
    numQs = 0;
    correct = 0;
    //f = new File("<file name.txt>");
    //ARverbStems =
    //IRverStems =

    File ARsubEndFile = new File("ARsubEndings.txt");
    ARsubEndings = readInFile(ARsubEndFile, ":");

    File IRsubEndFile = new File("IRsubEndings.txt");
    IRsubEndings = readInFile(IRsubEndFile, ":");

    for(int i = 0; i<IRsubEndings.length; i++)
    {
      System.out.println(IRsubEndings[i]);
    }
  }

  public String[] readInFile(File f, String splitOn)
  {
    String[] empty;
    try{
      Scanner scanny = new Scanner(f);
      String fullText = "";
      while (scanny.hasNextLine())
      {
        fullText += scanny.nextLine();
      }
      empty = fullText.split(splitOn);
    } catch (FileNotFoundException e)
    {
      System.out.println("File not found");
      empty = new String[1];
    }
    return empty;
  }

  public static void main(String[] args)
  {
    SubjunctiveQuiz test = new SubjunctiveQuiz();
  }

  public void runQuiz()
  {

  }

  public String[] generateQs(int numQs)
  {
    String[] temp = {""};
    return temp;
  }

  public boolean isSubjuntive(String sentence)
  {
    return true;
  }

  public boolean isIndicative(String sentence)
  {
    return true;
  }
}
