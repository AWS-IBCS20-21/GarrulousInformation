/**Lu Henry-Mitchell
9-10-20
6th period
**/
import java.util.*;


public class CoffeeShop
{
  public static void main (String[] args)
  {
    Scanner scanny = new Scanner(System.in);
    String name;
    String order;
    int time;

    time = (int)(Math.random()*60);
    System.out.println("Welcome to Batdorf & Bronson Coffee"); //my favorite coffee shop in Olympia
    System.out.println("May I take your order?");
    order = scanny.nextLine();
    System.out.println("And your name?");
    name = scanny.nextLine();
    System.out.println("So that's one " + order + " for " + name +
    ". That'll be up in about " + time + " minutes");
  }
}
