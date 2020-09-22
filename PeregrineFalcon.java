/** Lu Henry-Mitchell
9-18-20
6th period **/
import java.util.*;

public class PeregrineFalcon
{
  String scientificName;
  String habitat;
  int size;
  String prey;
  String funFact;

  public PeregrineFalcon()
  {
    scientificName = "Falco peregrinus";
    habitat = "Peregrine falcons live on all continents except Antartica";
    size = 18;
    prey = "ducks, pigeons, songbirds, and sometimes bats";
    funFact = "The peregrine falcon is the fastest bird in the world and has \none of the fastest visual processing speeds of any animal";
  }

  public static void main(String[] args)
  {
    int userIn;
    Scanner scanny = new Scanner(System.in);

    PeregrineFalcon test = new PeregrineFalcon();
    System.out.println("This program is about peregrine falcons. Enter a number for information");
    System.out.println("1. Scientific Name\n2. Habitat\n3. Size\n4. Similar Animals\n5. What makes them special");
    userIn = scanny.nextInt();
    if(userIn == 1){
      System.out.println("Scientific name: " + test.getScientificName());
    } else if (userIn == 2){
      System.out.println("Habitat: " + test.getHabitat());
    } else if (userIn == 3){
      System.out.println("Average size: " + test.getSize() + " in");
    } else if (userIn == 4){
      System.out.println("Prey: " + test.getprey());
    } else if (userIn == 5){
      System.out.println("Fun fact: " + test.getfunFact());
    } else {
      System.out.println("You don't seem to have entered one of the available options");
    }
  }

  public String getScientificName()
  {
    return scientificName;
  }

  public String getHabitat()
  {
    return habitat;
  }

  public int getSize()
  {
    return size;
  }

  public String getprey()
  {
    return prey;
  }

  public String getfunFact()
  {
    return funFact;
  }
}
