import java.util.*;
import java.io.*;

//NEXT: add more recognized verb stems, display unconjugated verb instead of correct form
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
  public String[] questions;
  public int[] indexCheatSheet;
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
    //File novel = new File("ViajesPorEspaña.txt"); //trying a new one
    File novel = new File("MalaHierba.txt");
    try{
      Scanner novelScanner = new Scanner(novel);
      String fullText = "";
      while(novelScanner.hasNextLine())
      {
        fullText += novelScanner.nextLine() + " ";
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
    /*System.out.println(test.isSubjunctive("Ella quiere que bebas agua"));
    System.out.println(test.isIndicative("Ella quiere que bebas agua"));

    System.out.println(test.isSubjunctive("Ella quiere que hables"));
    System.out.println(test.isIndicative("Ella quiere que hables"));

    System.out.println(test.isIndicative("Ella habla conmigo"));
    System.out.println(test.isSubjunctive("Ella habla conmigo"));*/


    test.runQuiz();
  }

  public void runQuiz()
  {
    /*for(int i = 0; i < sentences.length; i++)
    {
      if(isIndicative(sentences[i]))
      {
        System.out.println("Indicative: " + sentences[i]);
      }
    }*/
    Scanner scanny = new Scanner(System.in);
    String answer = "";

    System.out.println("How many questions?");
    numQs = scanny.nextInt(); //need to add error handling for unexpected type <-- IMPORTANT
    indexCheatSheet = new int[numQs];
    questions = generateQs(numQs);

    for(int i = 0; i < numQs; i++)
    {
      String[] words = questions[i].split(" ");

      for(int k = 0; k < indexCheatSheet[i] + 1; k++)
      {
        System.out.print(words[k] + " ");
      }

      System.out.print("________ ");
      System.out.print("(" + words[indexCheatSheet[i] + 1] + ") "); //1 index behind since has "subjunctive/indicative" at beginning - take out later

      for(int m = indexCheatSheet[i] + 2; m < words.length; m++)
      {
        System.out.print(words[m] + " ");
      }

      System.out.println();
      System.out.println();
      System.out.println("Enter the correct form of the verb");
      answer = scanny.next(); //also needs error handling
      if(answer.toLowerCase().equals(words[indexCheatSheet[i] + 1].toLowerCase()))
      {
        System.out.println("Correct");
        correct ++;
      } else {
        System.out.println("False");
      }
    }
    System.out.println("Correct: " + correct + "/" + numQs);
  }

  public String[] generateQs(int numQs)
  {
    String[] myQuestions = new String[numQs];
    int randomPosition = 0;

    int[] questionGrid = new int[numQs];
    for(int i = 0; i < numQs; i++)
    {
      questionGrid[i] = (int)((Math.random())*2);
      //System.out.println(questionGrid[i]); for debugging
    }

    for(int k = 0; k < numQs; k++)
    {
      if(questionGrid[k] == 0)
      {
        do{
          randomPosition = (int)((Math.random())*(sentences.length));
          if(this.isIndicative(sentences[randomPosition]) != -1) //check random sentence to see if it's indicative
          {
            myQuestions[k] = "Indicative: " + sentences[randomPosition]; //will want to take out "indicative" later
            indexCheatSheet[k] = this.isIndicative(sentences[randomPosition]); //to keep track of which word in each indicative sentence is the indicative verb
          }
        } while (myQuestions[k] == null); //== not .equals
      } else if (questionGrid[k] == 1)
      {
        do{
          randomPosition = (int)((Math.random())*(sentences.length));
          if(this.isSubjunctive(sentences[randomPosition]) != -1) //check random sentence to see if it's indicative
          {
            myQuestions[k] = "Subjunctive: " + sentences[randomPosition];
            indexCheatSheet[k] = this.isSubjunctive(sentences[randomPosition]); //to keep track of which word in each subjunctive sentence is the subjunctive verb
          }
        } while (myQuestions[k] == null);
      }
    }
    return myQuestions;
    //set up int[] w random numbers 1 (indicative) or 0 (subjunctive), then populate from novel
    //according to this random grid
    //also need some way to identify which verb is subjunctive in the sentence... maybe
    //variation on that method? like overloaded but w return type. is that a thing?
  }

  public int isSubjunctive(String sentence) //return index of subjunctive verb in string[] of words in sentence, -1 if not subjunctive
  {
    boolean stem = false;
    boolean ending = false;
    boolean hasQue = false;
    boolean subVerb = false;
    boolean isNoun = false;
    int verbIndex = -1;
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
      if(i > 0) //check to see if it's actually a noun
      {
        if(words[i-1].toLowerCase().equals("el") || words[i-1].toLowerCase().equals("la") || words[i-1].toLowerCase().equals("los")
        || words[i-1].toLowerCase().equals("los") || words[i-1].toLowerCase().equals("mi") || words[i-1].toLowerCase().equals("tu")
        || words[i-1].toLowerCase().equals("su") || words[i-1].toLowerCase().contains("nuestr") || words[i-1].toLowerCase().contains("un"))
        {
          isNoun = true;
        }
      }

      if(ending && stem && temp.length() == 0 && isNoun == false)
      {
        subVerb = true;
        verbIndex = i;
      } else{
        ending = false;
        stem = false;
        temp = "";
        isNoun = false;
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

      if(i > 0) //check again to see if it's actually a noun
      { //might want to screen out for common false positives - ex forma, tomo, entre, como
        if(words[i-1].toLowerCase().equals("el") || words[i-1].toLowerCase().equals("la") || words[i-1].toLowerCase().equals("los")
        || words[i-1].toLowerCase().equals("los") || words[i-1].toLowerCase().equals("mi") || words[i-1].toLowerCase().equals("tu")
        || words[i-1].toLowerCase().equals("su") || words[i-1].toLowerCase().contains("nuestr") || words[i-1].toLowerCase().contains("un"))
        {
          isNoun = true;
        }
      }
      if(stem && ending && temp.length() == 0 && isNoun == false)
      {
        subVerb = true;
        verbIndex = i;
      }
      stem = false;
      ending = false;
      temp = "";
      isNoun = false;
    }
    if(subVerb && hasQue)
    {
      return verbIndex;
    } else {
      return -1;
    }
  }

  public int isIndicative(String sentence) //might want to only include sentences w que? to make test better?
  {
    boolean stem = false;
    boolean ending = false;
    boolean isNoun = false;
    String temp = "";

    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++)
    {
      for(int j = 0; j < ARindicEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].endsWith(ARindicEndings[j]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-ARindicEndings[j].length());
        }
      }
      for(int k = 0; k < ARverbStems.length; k++)
      {
        if(words[i].contains(ARverbStems[k]))
        {
          stem = true;
          temp = temp.replaceFirst(ARverbStems[k], "");
        }
      }

      if(i > 0) //check to see if it's actually a noun
      {
        if(words[i-1].toLowerCase().equals("el") || words[i-1].toLowerCase().equals("la") || words[i-1].toLowerCase().equals("los")
        || words[i-1].toLowerCase().equals("los") || words[i-1].toLowerCase().equals("mi") || words[i-1].toLowerCase().equals("tu")
        || words[i-1].toLowerCase().equals("su") || words[i-1].toLowerCase().contains("nuestr") || words[i-1].toLowerCase().contains("un"))
        {
          isNoun = true;
        }
      }

      if(ending && stem && temp.length() == 0 && isNoun == false)
      {
        return i;
      } else{
        ending = false;
        stem = false;
        temp = "";
        isNoun = false;
      }

      for(int l = 0; l < IRindicEndings.length; l++)
      {
        if(words[i].length() > 3 && words[i].endsWith(IRindicEndings[l]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-IRindicEndings[l].length());
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

      if(i > 0) //check to see if it's actually a noun
      {
        if(words[i-1].toLowerCase().equals("el") || words[i-1].toLowerCase().equals("la") || words[i-1].toLowerCase().equals("los")
        || words[i-1].toLowerCase().equals("los") || words[i-1].toLowerCase().equals("mi") || words[i-1].toLowerCase().equals("tu")
        || words[i-1].toLowerCase().equals("su") || words[i-1].toLowerCase().contains("nuestr") || words[i-1].toLowerCase().contains("un"))
        {
          isNoun = true;
        }
      }

      if(stem && ending && temp.length() == 0 && isNoun == false)
      {
        return i;
      }
      stem = false;
      ending = false;
      temp = "";
      isNoun = false;
    }
      return -1;
  }
}
