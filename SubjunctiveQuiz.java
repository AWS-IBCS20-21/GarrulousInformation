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

  public SubjunctiveQuiz()
  {
    numQs = 0;
    correct = 0;

    //set up reference files to determine verb tense
    File ARsubEndFile = new File("ARsubEndings.txt");
    ARsubEndings = readInFile(ARsubEndFile, ":");

    File IRsubEndFile = new File("IRsubEndings.txt");
    IRsubEndings = readInFile(IRsubEndFile, ":");

    File ARindicEndFile = new File("ARindicEndings.txt");
    ARindicEndings = readInFile(ARindicEndFile, ":");

    File IRindicEndFile = new File("IRindicEndings.txt");
    IRindicEndings = readInFile(IRindicEndFile, ":");

    File IRverbStemFile = new File("IRstems.txt");
    IRverbStems = readInFile(IRverbStemFile, ":");

    File ARverbStemFile = new File("ARstems.txt");
    ARverbStems = readInFile(ARverbStemFile, ":");

    //set up file to draw examples from
    File novel = new File("ViajesPorEspaña.txt");
    try{
      Scanner novelScanner = new Scanner(novel);
      String fullText = "";
      while(novelScanner.hasNextLine())
      {
        fullText += novelScanner.nextLine();
      }
      //sentences = fullText.split("\\.");
      sentences = fullText.split("[-\\t;.?!:@\\[\\](){}_*/]");
    } catch(FileNotFoundException e)
    {
      System.out.println("File not found");
    }

    /*for(int i = 0; i<sentences.length; i++) //for debugging
    {
      System.out.println(sentences[i]);
    }*/
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
    System.out.println(test.isSubjuntive("Ella quiere que bebas agua"));
    System.out.println(test.isIndicative("Ella quiere que bebas agua"));

    System.out.println(test.isSubjuntive("Ella quiere que abras la puerta"));
    System.out.println(test.isIndicative("Ella quiere que abras la puerta"));

    System.out.println(test.isIndicative("Ella habla conmigo"));
    System.out.println(test.isSubjuntive("Ella habla conmigo"));
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
    boolean stem = false;
    boolean ending = false;
    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++)
    {
      for(int j = 0; j < ARsubEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].substring(words[i].length() - ARsubEndings[j].length()).equals(ARsubEndings[j]))
        {
          ending = true;
        }
      }
      for(int k = 0; k < ARverbStems.length; k++)
      {
        if(words[i].contains(ARverbStems[k]))
        {
          stem = true;
        }
      }
      if(ending && stem)
      {
        return true;
      } else{
        ending = false;
        stem = false;
      }

      for(int l = 0; l < IRsubEndings.length; l++)
      {
        if(words[i].length() > 3 && words[i].substring(words[i].length() - IRsubEndings[l].length()).equals(IRsubEndings[l]))
        {
          ending = true;
        }
      }
      for(int m = 0; m < IRverbStems.length; m++)
      {
        if(words[i].contains(IRverbStems[m]))
        {
          stem = true;
        }
      }
      if(stem && ending)
      {
        return true;
      }
      stem = false;
      ending = false;
    }
    return false;
  }

  public boolean isIndicative(String sentence)
  {
    boolean stem = false;
    boolean ending = false;
    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++)
    {
      for(int j = 0; j < ARindicEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].substring(words[i].length() - ARindicEndings[j].length()).equals(ARindicEndings[j]))
        {
          ending = true;
        }
      }
      for(int k = 0; k < ARverbStems.length; k++)
      {
        if(words[i].contains(ARverbStems[k]))
        {
          stem = true;
        }
      }
      if(ending && stem)
      {
        return true;
      } else{
        ending = false;
        stem = false;
      }

      for(int l = 0; l < IRindicEndings.length; l++)
      {
        if(words[i].length() > 3 && words[i].substring(words[i].length() - IRindicEndings[l].length()).equals(IRindicEndings[l]))
        {
          ending = true;
        }
      }
      for(int m = 0; m < IRverbStems.length; m++)
      {
        if(words[i].contains(IRverbStems[m]))
        {
          stem = true;
        }
      }
      if(stem && ending)
      {
        return true;
      }
      stem = false;
      ending = false;
    }
    return false;
  }
}
