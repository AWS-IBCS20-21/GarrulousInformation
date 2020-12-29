import java.util.*;
import java.io.*;

public class Herb
{

  public String name;
  public String[] scientific;
  public String[] folkName;
  public String planet;
  public String element;
  public String[] deities;
  public String[] powers;


  public Herb(String name, String scientific[], String folkName[], String planet,
  String element, String[] deities, String[] powers)
  {
    this.name = name;
    this.scientific = scientific;
    this.folkName = folkName;
    this.planet = planet;
    this.element = element;
    this.deities = deities;
    this.powers = powers;
  }

  public Herb() //default constructor
  {
    name = "";
    scientific = new String[1];
    folkName = new String[1];
    planet = "";
    element = "";
    deities = new String[1];
    powers = new String[1];
  }

  public String returnName()
  {
    return name;
  }

  public String[] returnScientific()
  {
    return scientific;
  }

  public String[] returnfolkName()
  {
    return folkName;
  }

  public String returnPlanet()
  {
    return planet;
  }

  public String[] returnDeities()
  {
    return deities;
  }

  public String[] returnPowers()
  {
    return powers;
  }

  public String returnElement()
  {
    return element;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setScientific(String[] scientific)
  {
    this.scientific = scientific;
  }

  public void setfolkName(String[] folkName)
  {
    this.folkName = folkName;
  }

  public void setPlanet(String planet)
  {
    this.planet = planet;
  }

  public void setElement(String element)
  {
    this.element = element;
  }

  public void setDeities(String[] deities)
  {
    this.deities = deities;
  }

  public void setPowers(String[] powers)
  {
    this.powers = powers;
  }
}
