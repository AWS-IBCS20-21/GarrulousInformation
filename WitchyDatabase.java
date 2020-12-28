import java.util.*;
import java.io.*;

//NEXT: compatible w different capitalization in search terms + a lot of data entry + GUI (later)
//also folkName should be an array, don't know why I didn't think of that earlier

public class WitchyDatabase
{

  public ArrayList<Herb> herbsList;

  public WitchyDatabase()
  {
    herbsList = new ArrayList<Herb>();
  }

  public static void main (String[] args)
  {
    WitchyDatabase herbInfo = new WitchyDatabase();
    herbInfo.buildList();
    //herbInfo.print(herbInfo.getHerbsList());
    herbInfo.find(); //same
  }

  public ArrayList<Herb> getHerbsList() //so I can reference herbsList from the main method
  {
    return herbsList;
  }

  public void print(ArrayList<Herb> toBePrinted)
  {
    for(int i = 0; i < toBePrinted.size(); i++)
    {
      System.out.print(toBePrinted.get(i).returnName() + ": ");

      for(int j = 0; j < toBePrinted.get(i).returnScientific().length; j++)
      {
        System.out.print(toBePrinted.get(i).returnScientific()[j] + ", ");
      }

      System.out.print(toBePrinted.get(i).returnfolkName() + ", " + toBePrinted.get(i).returnPlanet() + ", " + toBePrinted.get(i).returnElement() + ", ");

      for(int m = 0; m < toBePrinted.get(i).returnDeities().length; m++)
      {
        System.out.print(toBePrinted.get(i).returnDeities()[m] + ", ");
      }

      for(int n = 0; n < toBePrinted.get(i).returnPowers().length - 1; n++)
      {
        System.out.print(toBePrinted.get(i).returnPowers()[n] + ", ");
      }
      System.out.print(toBePrinted.get(i).returnPowers()[toBePrinted.get(i).returnPowers().length - 1]);

      System.out.println();
    }
  }

  public String[] individualHerbs; //don't ask

  public void buildList()
  {
    try {
      File f = new File("herbs.txt");
      Scanner scanny = new Scanner(f);
      String fullText = "";

      while (scanny.hasNextLine())
      {
        fullText += scanny.nextLine();
      }

      individualHerbs = fullText.split("!"); //array of Strings where each element contains all info on one herb
      for(int j = 0; j <individualHerbs.length; j++)
      {
        Herb herb1 = new Herb();
        String temp = individualHerbs[j]; //could make this more concise
        String[] info = temp.split(":");

        herb1.setName(info[0]);
        herb1.setScientific(info[1].split(","));
        herb1.setfolkName(info[2]);
        herb1.setPlanet(info[3]);
        herb1.setElement(info[4]);
        herb1.setDeities(info[5].split(","));
        herb1.setPowers(info[6]. split(","));

        herbsList.add(herb1);
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }

  public void find()
  {
    Scanner miniScanny = new Scanner(System.in);
    ArrayList<Herb> matches = new ArrayList<Herb>();
    int property;
    String target;

    System.out.println("Welcome to the Witchy Database of Herbs");
    System.out.println("You can search by these properties: \n1. Name\n2. Scientific name\n3. Folk name");
    System.out.println("4. Planet\n5. Element\n6. Deity\n7. Power");
    System.out.println("(Name will return all properties of the herb; all other search options will return the name of the herb(s))");
    System.out.println("Enter the number corresponding to the property you want to search for");
    property = Integer.parseInt(miniScanny.nextLine()); //so doesn't have the error later w nextLine

    if(property == 1)
    {
      System.out.println("Enter the name of the herb you would like to find");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++)
      {
        if(herbsList.get(i).returnName().equals(target))
        {
          matches.add(herbsList.get(i));
        }
      }
      this.print(matches);
    } else if(property == 2)
    {
      System.out.println("Enter the scientific name of the herb you would like to find");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnScientific().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnScientific()[j].equals(target))
          {
            matches.add(herbsList.get(i));
          }
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else if(property == 3)
    {
      System.out.println("Enter the folk name of the herb you would like to find");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        if(herbsList.get(i).returnfolkName().equals(target))
        {
          matches.add(herbsList.get(i));
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else if(property == 4)
    {
      System.out.println("Enter the planet to which you would like these herbs to correspond");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        if(herbsList.get(i).returnPlanet().equals(target))
        {
          matches.add(herbsList.get(i));
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else if(property == 5)
    {
      System.out.println("Enter the element to which you would like these herbs to correspond");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        if(herbsList.get(i).returnElement().equals(target))
        {
          matches.add(herbsList.get(i));
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else if(property == 6)
    {
      System.out.println("Enter the deity to which you would like these herbs to correspond");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnDeities().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnDeities()[j].equals(target))
          {
            matches.add(herbsList.get(i));
          }
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else if(property == 7)
    {
      System.out.println("Enter the power to which you would like these herbs to correspond");
      target = miniScanny.nextLine();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnPowers().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnPowers()[j].equals(target))
          {
            matches.add(herbsList.get(i));
          }
        }
      }
      if(matches.size() == 0) //so it doesn't have an indexOutOfBounds error
      {
        System.out.println("No matches");
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
      }
    } else {
      System.out.println("You don't seem to have entered one of the valid choices");
    }
  }
}
