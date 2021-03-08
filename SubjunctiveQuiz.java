import java.util.*;
import java.io.*;

public class SubjunctiveQuiz
{
  public int numQs;
  public String[] sentences;
  public String[] ARverbStems;
  public String[] IRverbStems;
  public String[] ERverbStems;
  public String[] ARsubEndings;
  public String[] IRsubEndings;
  public String[] ARindicEndings;
  public String[] IRindicEndings;
  public String[] questions;
  public int[] indexCheatSheet;
  public int[] questionGrid;
  public int correct;
  public SQGUI quizGUI;

  public SubjunctiveQuiz() //constructor method
  {
    numQs = 0;
    correct = 0;
    quizGUI = new SQGUI();

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

    File ERverbStemFile = new File("ERstems.txt");
    ERverbStems = readInFile(ERverbStemFile, ":");

    //set up file to draw examples from - can easily be changed to provide different quiz questions
    File novel = new File("MalaHierba.txt");
    try{
      Scanner novelScanner = new Scanner(novel);
      String fullText = "";
      while(novelScanner.hasNextLine())
      {
        fullText += novelScanner.nextLine() + " ";
      }
      sentences = fullText.split("[-\\t;.?!:@\\[\\](){}_*/]");
    } catch(FileNotFoundException e) //necessary error handling for working with a File
    {
      System.out.println("File not found");
    }
  }

  public String[] readInFile(File f, String splitOn) //sets up Files the program works with
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

    test.runQuiz();
  }

  public void runQuiz()
  {
    Scanner scanny = new Scanner(System.in);
    String answer = "";

    numQs = quizGUI.getNumQs(); //retrieve user input for number of questions from GUI

    indexCheatSheet = new int[numQs];
    questions = generateQs(numQs);

    //process for each question
    for(int i = 0; i < numQs; i++)
    {
      //create a String containing the currect question to be passed as parameter to GUI to display
      String completeQ = "";

      String[] words = questions[i].split(" ");

      for(int k = 0; k < indexCheatSheet[i]; k++)
      {
        completeQ += words[k] + " ";
      }

      completeQ += "________ ";
      completeQ += "(" + this.getInfinitive(words[indexCheatSheet[i]]) + ") ";

      for(int m = indexCheatSheet[i] + 1; m < words.length; m++)
      {
        completeQ += words[m] + " ";
      }

      //get user input for answer from GUI as return value
      answer = quizGUI.runQuiz(completeQ.trim());

      //check if answer is correct by seeing if it matches against corresponding index of
      //the array keeping track of indices of the correct verb in the sentence
      if(answer.toLowerCase().equals(words[indexCheatSheet[i]].toLowerCase()))
      {
        quizGUI.showImmediateResult(true, "");
        correct ++; //keep track of number of correct answers
      } else {
        quizGUI.showImmediateResult(false, words[indexCheatSheet[i]]); //display correct answer if user input incorrect
      }
    }
    quizGUI.showFinalResult(correct); //display final number correct out of total
  }

  public String getInfinitive(String verb)
  {
    //if verb matches AR stem, return stem + ar, forming infinitive


    for(String s: ARverbStems)
    {
      if(verb.startsWith(s))
      {
        return s + "ar";
      }
    }

    //if verb matches IR stem, return stem + ir, forming infinitive
    for(String s: IRverbStems)
    {
      if(verb.startsWith(s))
      {
        return s + "ir";
      }
    }

    //if verb matches ER stem, return stem + er, forming infinitive
    for(String s: ERverbStems)
    {
      if(verb.startsWith(s))
      {
        return s + "er";
      }
    }
    //otherwise return empty String
    return "";
  }

  public String[] generateQs(int numQs)
  {
    String[] myQuestions = new String[numQs];
    int randomPosition = 0;


    //create an array of randmo numbers 1 or 0, with each corresponding to subjunctive or indicative,
    //to determine random pattern of questions
    questionGrid = new int[numQs];
    for(int i = 0; i < numQs; i++)
    {
      questionGrid[i] = (int)((Math.random())*2);
    }

    for(int k = 0; k < numQs; k++)
    {
      //0 corresponds to indicative sentence
      if(questionGrid[k] == 0)
      {
        do{
          randomPosition = (int)((Math.random())*(sentences.length));
          if(this.isIndicative(sentences[randomPosition]) != -1) //check random sentence to see if it's indicative
          {
            myQuestions[k] = sentences[randomPosition];
            //to keep track of which word in each indicative sentence is the indicative verb
            indexCheatSheet[k] = this.isIndicative(sentences[randomPosition]);
          }
        } while (myQuestions[k] == null); //== not .equals for null
      } else if (questionGrid[k] == 1) //1 corresponds to subjunctive sentence
      {
        do{
          randomPosition = (int)((Math.random())*(sentences.length));
          if(this.isSubjunctive(sentences[randomPosition]) != -1) //check random sentence to see if it's indicative
          {
            myQuestions[k] = sentences[randomPosition];
            //to keep track of which word in each subjunctive sentence is the subjunctive verb
            indexCheatSheet[k] = this.isSubjunctive(sentences[randomPosition]);
          }
        } while (myQuestions[k] == null);
      }
    }
    return myQuestions;
  }

  //return index of subjunctive verb in string[] of words in sentence, -1 if not subjunctive
  public int isSubjunctive(String sentence)
  {
    boolean stem = false;
    boolean ending = false;
    boolean hasQue = false;
    boolean subVerb = false;
    boolean isNoun = false;
    int verbIndex = -1;
    String temp = "";

    String[] words = sentence.split(" ");

    //go through each word in the sentence
    for(int i = 0; i < words.length; i++)
    {
      //Nearly all subjunctive sentences have 'que,' so check for that to screen out false positives
      if(words[i].equals("que"))
      {
        hasQue = true;
      }

      for(int j = 0; j < ARsubEndings.length; j++)
      {
        //check if the word has an AR subjunctive verb ending
        //if so, keep track of what is left of the word without the ending
        if(words[i].length() > 3 && words[i].endsWith(ARsubEndings[j]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-ARsubEndings[j].length());
        }
      }
      //check if the word has an AR verb stem
      //if so, remove this stem from temporary String established above
      for(int k = 0; k < ARverbStems.length; k++)
      {
        if(words[i].contains(ARverbStems[k]))
        {
          stem = true;
          temp = temp.replaceFirst(ARverbStems[k], "");
        }
      }

      if(i > 0) //check for preceding words that indicate the word is actually functioning as a noun in this case
      {
        if(words[i-1].toLowerCase().equals("el") || words[i-1].toLowerCase().equals("la") || words[i-1].toLowerCase().equals("los")
        || words[i-1].toLowerCase().equals("los") || words[i-1].toLowerCase().equals("mi") || words[i-1].toLowerCase().equals("tu")
        || words[i-1].toLowerCase().equals("su") || words[i-1].toLowerCase().contains("nuestr") || words[i-1].toLowerCase().contains("un"))
        {
          isNoun = true;
        }
      }

      //if the word has the right stem, the right ending, and nothing in between, and is not a noun,
      //word is subjunctive and its index in the sentence is recorded
      if(ending && stem && temp.length() == 0 && isNoun == false)
      {
        subVerb = true;
        verbIndex = i;
      } else{ //otherwise reset the variables to keep track of measures of whether verb is subjunctive
        ending = false;
        stem = false;
        temp = "";
        isNoun = false;
      }

      //repeat process w IR and ER verbs
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
      //IR and ER verbs have same endings, so check if either IR or ER verb w these endings
      for(int n = 0; n < ERverbStems.length; n++)
      {
        if(words[i].contains(ERverbStems[n]))
        {
          stem = true;
          temp = temp.replaceFirst(ERverbStems[n], "");
        }
      }

      if(i > 0) //check again to see if it's actually a noun
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

  public int isIndicative(String sentence)
  {
    boolean stem = false;
    boolean ending = false;
    boolean isNoun = false;
    String temp = "";

    String[] words = sentence.split(" ");

    //go through each word in sentence
    for(int i = 0; i < words.length; i++)
    {
      //test if word has AR indicative verb ending
      for(int j = 0; j < ARindicEndings.length; j++)
      {
        if(words[i].length() > 3 && words[i].endsWith(ARindicEndings[j]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-ARindicEndings[j].length());
        }
      }

      //test if word has AR verb stem
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
      } else{ //else reset variables
        ending = false;
        stem = false;
        temp = "";
        isNoun = false;
      }

      //check to see if word has IR indicative verb ending
      for(int l = 0; l < IRindicEndings.length; l++)
      {
        if(words[i].length() > 3 && words[i].endsWith(IRindicEndings[l]))
        {
          ending = true;
          temp = words[i].substring(0, words[i].length()-IRindicEndings[l].length());
        }
      }

      //check to see if word has IR verb stem
      for(int m = 0; m < IRverbStems.length; m++)
      {
        if(words[i].contains(IRverbStems[m]))
        {
          stem = true;
          temp = temp.replaceFirst(IRverbStems[m], "");
        }
      }
      //IR and ER verbs have same endings, so check if either IR or ER verb w these endings
      for(int n = 0; n < ERverbStems.length; n++)
      {
        if(words[i].contains(ERverbStems[n]))
        {
          stem = true;
          temp = temp.replaceFirst(ERverbStems[n], "");
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
