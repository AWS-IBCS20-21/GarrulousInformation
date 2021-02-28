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

    System.out.println(test.isSubjuntive("Ella quiere que hables"));
    System.out.println(test.isIndicative("Ella quiere que hables"));

    System.out.println(test.isIndicative("Ella habla conmigo"));
    System.out.println(test.isSubjuntive("Ella habla conmigo"));


    //test.runQuiz();
  }

  public void runQuiz()
  {
    for(int i = 0; i < sentences.length; i++)
    {
      if(isSubjuntive(sentences[i]))
      {
        System.out.println("Subjunctive: " + sentences[i]);
      }
    }
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
    boolean hasQue = false;
    boolean subVerb = false;
    String temp = "";

    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++)
    {
      //95% of subjunctive sentences have 'que,' so check for that to screen out false positives
      if(words[i].equals("que"))
      {
        hasQue = true;
      }

      for(int j = 0; j < ARsubEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].endsWith(ARsubEndings[j]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-ARsubEndings[j].length());
          //System.out.println(temp); //for debugging
        }
      }
      for(int k = 0; k < ARverbStems.length; k++)
      {
        if(words[i].contains(ARverbStems[k]))
        {
          stem = true;
          temp = temp.replaceFirst(ARverbStems[k], "");
          //System.out.println("What's left: " + temp); //for debugging
        }
      }
      if(ending && stem && temp.length() == 0)
      {
        subVerb = true;
      } else{
        ending = false;
        stem = false;
        temp = "";
      }

      for(int l = 0; l < IRsubEndings.length; l++)
      {
        if(words[i].length() > 3 && words[i].endsWith(IRsubEndings[l]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-IRsubEndings[l].length());
        }
      }
      for(int m = 0; m < IRverbStems.length; m++)
      {
        if(words[i].contains(IRverbStems[m]))
        {
          stem = true;
          temp = temp.replaceFirst(IRverbStems[m], "");
        }
      }
      if(stem && ending && temp.length() == 0)
      {
        subVerb = true;
      }
      stem = false;
      ending = false;
      temp = "";
    }
    if(subVerb && hasQue)
    {
      return true;
    } else {
      return false;
    }
  }

  public boolean isIndicative(String sentence) //might want to chop off the parts it identifies and then check that nothing is left
  {
    boolean stem = false;
    boolean ending = false;

    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++)
    {
      for(int j = 0; j < ARindicEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].endsWith(ARindicEndings[j]))
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
        if(words[i].length() > 3 && words[i].endsWith(IRindicEndings[l]))
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
