import java.util.*;
import java.io.*;

//NEXT: have searching by name display rest of properties, make layout readable
//THIS IS A TEST (W GUI) - og version available in main branch or under OldWitchyDatabase.java

public class WitchyDatabase
{

  public ArrayList<Herb> herbsList;
  public WDGUI herbGUI;

  public WitchyDatabase()
  {
    herbsList = new ArrayList<Herb>();
    herbGUI = new WDGUI();
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
      System.out.println("Name: " + toBePrinted.get(i).returnName());

      System.out.print("Scientific name: ");
      for(int j = 0; j < toBePrinted.get(i).returnScientific().length - 1; j++)
      {
        System.out.print(toBePrinted.get(i).returnScientific()[j] + ", ");
      }
      System.out.println(toBePrinted.get(i).returnScientific()[toBePrinted.get(i).returnScientific().length - 1]);

      System.out.print("Folk name(s): ");
      for(int k = 0; k < toBePrinted.get(i).returnfolkName().length - 1; k++)
      {
        System.out.print(toBePrinted.get(i).returnfolkName()[k] + ", ");
      }
      System.out.println(toBePrinted.get(i).returnfolkName()[toBePrinted.get(i).returnfolkName().length - 1]);

      System.out.println("Planet: " + toBePrinted.get(i).returnPlanet());
      System.out.println("Element: " + toBePrinted.get(i).returnElement());

      System.out.print("Deities: ");
      for(int m = 0; m < toBePrinted.get(i).returnDeities().length - 1; m++)
      {
        System.out.print(toBePrinted.get(i).returnDeities()[m] + ", ");
      }
      System.out.println(toBePrinted.get(i).returnDeities()[toBePrinted.get(i).returnDeities().length - 1]);

      System.out.print("Powers: ");
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
        herb1.setfolkName(info[2].split(","));
        herb1.setPlanet(info[3]);
        herb1.setElement(info[4]);
        herb1.setDeities(info[5].split(","));
        herb1.setPowers(info[6]. split(","));

        herbsList.add(herb1);
        //System.out.println("added: " + herb1.returnName()); for debugging
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }

  public void find()
  {
    ArrayList<Herb> matches = new ArrayList<Herb>();
    String property = "";
    String target = "";

    herbGUI.selectProperty();
    while(herbGUI.selectedProperty == false)
    {
      try{
        Thread.sleep(100); //timing wasn't working out right - kept grabbing stuff before it was updated w user input
      } catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
    }
    property = herbGUI.property;
    System.out.println("Exited property loop");
    System.out.println("Property: " + property);

    herbGUI.enterParameters();
    target = herbGUI.target.toLowerCase();
    System.out.println("Target: " + target);

    //NEXT: link more logically to printResults method in GUI

    //System.out.println("Welcome to the Witchy Database of Herbs");
    //System.out.println("You can search by these properties: \n1. Name\n2. Scientific name\n3. Folk name");
    //System.out.println("4. Planet\n5. Element\n6. Deity\n7. Power");
    //System.out.println("*Name will return all properties of the herb; all other search options will return the name of the herb(s)");
    //System.out.println("Enter the number corresponding to the property you want to search for:");
    //property = Integer.parseInt(miniScanny.nextLine()); //so doesn't have the error later w nextLine

    if(property.equals("Name"))
    {
      //System.out.println("Enter the name of the herb you would like to find:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++)
      {
        if(herbsList.get(i).returnName().toLowerCase().equals(target))
        {
          matches.add(herbsList.get(i));
        }
      }
      this.print(matches);
      herbGUI.printResults(matches, property, target);
    } else if(property.equals("Scientific Name"))
    {
      //System.out.println("Enter the scientific name of the herb you would like to find:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnScientific().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnScientific()[j].toLowerCase().equals(target))
          {
            matches.add(herbsList.get(i));
          }
        }
      }
      if(matches.size() == 0)
      {
        System.out.println("No matches"); //maybe have printResults method w alt parameters? (Overloaded?)
      } else {
        for(int k = 0; k < matches.size() - 1; k++) //print results
        {
          System.out.println(matches.get(k).returnName());
        }
        System.out.println(matches.get(matches.size()-1).returnName());
        herbGUI.printResults(matches, property, target);
      }
    } else if(property.equals("Folk Name"))
    {
      //System.out.println("Enter the folk name of the herb you would like to find:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnfolkName().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnfolkName()[j].toLowerCase().equals(target))
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
        herbGUI.printResults(matches, property, target);
      }
    } else if(property.equals("Planet"))
    {
      //System.out.println("Enter the planet to which you would like these herbs to correspond:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        if(herbsList.get(i).returnPlanet().toLowerCase().equals(target))
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
        herbGUI.printResults(matches, property, target);
      }
    } else if(property.equals("Element"))
    {
      //System.out.println("Enter the element to which you would like these herbs to correspond:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        if(herbsList.get(i).returnElement().toLowerCase().equals(target))
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
        herbGUI.printResults(matches, property, target);
      }
    } else if(property.equals("Deities"))
    {
      //System.out.println("Enter the deity to which you would like these herbs to correspond:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnDeities().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnDeities()[j].toLowerCase().equals(target))
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
        herbGUI.printResults(matches, property, target);
      }
    } else if(property.equals("Powers"))
    {
      //System.out.println("Enter the power to which you would like these herbs to correspond:");
      //target = miniScanny.nextLine().toLowerCase();
      for(int i = 0; i < herbsList.size(); i++) //search
      {
        for(int j = 0; j < herbsList.get(i).returnPowers().length; j++) //nested for loop because it's an array
        {
          if(herbsList.get(i).returnPowers()[j].toLowerCase().equals(target))
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
        herbGUI.printResults(matches, property, target);
      }
    } else {
      System.out.println("You don't seem to have entered one of the valid choices");
    }
  }
}
