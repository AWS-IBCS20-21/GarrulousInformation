
import java.util.*;

public class ArrayListExample
{
  public static void main (String[] args)
  {
    ArrayList<String> movieTickets = new ArrayList<String>(); //I collect movie tickets
    movieTickets.add("Bladerunner");
    movieTickets.add("Avengers: Endgame");
    movieTickets.add("47 Meters Down: Uncaged");
    movieTickets.add("A Quiet Place");

    for(int i=0; i<movieTickets.size(); i++)
    {
      System.out.println(movieTickets.get(i));
    }
  }
}
