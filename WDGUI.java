import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WDGUI
{
  private JFrame mainFrame; //JFrame contains JPanel
  private JLabel headerLabel;
  private JLabel explanationLabel;
  private JLabel statusLabel;
  private JPanel controlPanel; //needs JPanel to work
  private JTextField textField;
  private JButton enterButton;
  public String property;
  public String target;
  public boolean selectedProperty;
  public boolean selectedTarget;

  private JButton nameButton;
  private JButton scientificNameButton;
  private JButton folknameButton;
  private JButton planetButton;
  private JButton deitiesButton;
  private JButton elementButton;
  private JButton powersButton;

  public WDGUI()
  {
    property = "";
    target = "";
    selectedProperty = false;
    selectedTarget = false;
    mainFrame = new JFrame("Witchy Database test GUI");
    mainFrame.setSize(600,300);
    mainFrame.setLayout(new GridLayout(4, 1)); //parameters: rows, cols
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //don't forget this guy!

    headerLabel = new JLabel("", JLabel.CENTER);
    explanationLabel = new JLabel("", JLabel.CENTER);
    statusLabel = new JLabel("", JLabel.CENTER);
    statusLabel.setSize(350, 100);

    controlPanel = new JPanel(); //frame vs panel? How do they have different layouts?
    controlPanel.setLayout(new FlowLayout());

    mainFrame.add(headerLabel);
    mainFrame.add(explanationLabel);
    mainFrame.add(controlPanel);
    mainFrame.add(statusLabel);
    mainFrame.setVisible(true);
  }

  public void selectProperty()
  {
    headerLabel.setText("Welcome to the Witchy Database of Herbs. You can search by these prorperties:");
    explanationLabel.setText("Name will return all properties of the herb; all other search options will return name");

    nameButton = new JButton("Name");
    scientificNameButton = new JButton("Scientific Name");
    folknameButton = new JButton("Folk Name");
    planetButton = new JButton("Planet");
    elementButton = new JButton("Element");
    deitiesButton = new JButton("Deities");
    powersButton = new JButton("Powers");

    nameButton.setActionCommand("Name");
    scientificNameButton.setActionCommand("Scientific Name");
    folknameButton.setActionCommand("Folk Name");
    planetButton.setActionCommand("Planet");
    elementButton.setActionCommand("Element");
    deitiesButton.setActionCommand("Deities");
    powersButton.setActionCommand("Powers");

    nameButton.addActionListener(new ButtonClickListener());
    scientificNameButton.addActionListener(new ButtonClickListener());
    folknameButton.addActionListener(new ButtonClickListener());
    planetButton.addActionListener(new ButtonClickListener());
    elementButton.addActionListener(new ButtonClickListener());
    deitiesButton.addActionListener(new ButtonClickListener());
    powersButton.addActionListener(new ButtonClickListener());

    controlPanel.add(nameButton); //... wait... is this supposed to be added to the JFrame instead??
    controlPanel.add(scientificNameButton);
    controlPanel.add(folknameButton);
    controlPanel.add(planetButton);
    controlPanel.add(elementButton);
    controlPanel.add(deitiesButton);
    controlPanel.add(powersButton);

    controlPanel.setVisible(true);
  }

  public void enterParameters()
  {
    controlPanel.remove(nameButton);
    controlPanel.remove(scientificNameButton);
    controlPanel.remove(folknameButton);
    controlPanel.remove(planetButton);
    controlPanel.remove(elementButton);
    controlPanel.remove(deitiesButton);
    controlPanel.remove(powersButton);

    enterButton = new JButton("Enter");
    enterButton.setActionCommand("Enter");
    enterButton.addActionListener(new ButtonClickListener());
    controlPanel.add(enterButton);

    headerLabel.setText("Enter the " + property + " of the herb you would like to find");

    textField = new JTextField(10);
    controlPanel.add(textField);

    while(selectedTarget == false)
    {
      try{
        Thread.sleep(100); //not very elegant but wasn't working w timing - was doing stuff too quickly
      } catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
    }
      target = textField.getText();
    System.out.println("Exited target loop");
  }

  public void printResults(ArrayList<Herb> matches, String property)
  {
    controlPanel.remove(textField);
    controlPanel.remove(enterButton);

    JLabel nameLabel = new JLabel("", JLabel.CENTER);
    JLabel scientificLabel = new JLabel("", JLabel.CENTER);
    JLabel folkNameLabel = new JLabel("", JLabel.CENTER);
    JLabel planetLabel = new JLabel("", JLabel.CENTER);
    JLabel elementLabel = new JLabel("", JLabel.CENTER);
    JLabel deitiesLabel = new JLabel("", JLabel.CENTER);
    JLabel powersLabel = new JLabel("", JLabel.CENTER);

    mainFrame.setSize(600,600);
    JPanel resultsFrame = new JPanel();
    resultsFrame.setLayout(new GridLayout(4, 2));
    resultsFrame.setVisible(true);
    mainFrame.add(resultsFrame);
    //controlPanel.add(resultsFrame);

    /*controlPanel.add(nameLabel); //is there a way to do this more efficiently?
    controlPanel.add(scientificLabel);
    controlPanel.add(folkNameLabel);
    controlPanel.add(planetLabel);
    controlPanel.add(elementLabel);
    controlPanel.add(deitiesLabel);
    controlPanel.add(powersLabel);*/
    resultsFrame.add(nameLabel); //experiment to get the layout nicer - currently not working
    resultsFrame.add(scientificLabel);
    resultsFrame.add(folkNameLabel);
    resultsFrame.add(planetLabel);
    resultsFrame.add(elementLabel);
    resultsFrame.add(deitiesLabel);
    resultsFrame.add(powersLabel);

    if(property.equals("Name"))
    {
      //set up text for scientific names
      String scientificTxt = "Scientific name(s): ";
      for(int i = 0; i < matches.get(0).returnScientific().length - 1; i++)
      {
        scientificTxt += matches.get(0).returnScientific()[i] + ", ";
      }
      scientificTxt += matches.get(0).returnScientific()[matches.get(0).returnScientific().length - 1];

      //set up text for folk names
      String folkTxt = "Folk name(s): ";
      for(int j = 0; j < matches.get(0).returnfolkName().length - 1; j++)
      {
        folkTxt += matches.get(0).returnfolkName()[j] + ", ";
      }
      folkTxt += matches.get(0).returnfolkName()[matches.get(0).returnfolkName().length - 1];

      //set up text for deities
      String deitiesTxt = "Deities: ";
      for(int k = 0; k < matches.get(0).returnDeities().length - 1; k++)
      {
        deitiesTxt += matches.get(0).returnDeities()[k] + ", ";
      }
      deitiesTxt += matches.get(0).returnDeities()[matches.get(0).returnDeities().length - 1];

      //set up text for powers
      String powersTxt = "Powers: ";
      for(int m = 0; m < matches.get(0).returnPowers().length - 1; m++)
      {
        powersTxt += matches.get(0).returnPowers()[m] + ", ";
      }
      powersTxt += matches.get(0).returnPowers()[matches.get(0),returnPowers().length - 1];

      nameLabel.setText("Name: " + matches.get(0).returnName());
      scientificLabel.setText(scientificTxt);
      folkNameLabel.setText(folkTxt);
      planetLabel.setText("Planet: " + matches.get(0).returnPlanet());
      elementLabel.setText("Element: " + matches.get(0).returnElement());
      deitiesLabel.setText(folkTxt);
      powersLabel.setText(powersTxt);
      //currently showing up BUT now the placement is all screwed up
      //stick them in a miniframe w a gridlayout?
    } else {
      //will be a little different for the rest of them
      //... can you use a for loop inside the setText method?
      //or could use for loop to make a String and then have the setText display that
      //that's probly easier
    }
  }

  public static void main (String[] args)
  {
    WDGUI test = new WDGUI();
    //test.find();
  }

  private class ButtonClickListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String command = e.getActionCommand();

      property = command;
      System.out.println(property);
      selectedProperty = true;
      if(command.equals("Name"))
      {
        statusLabel.setText("Searching by Name");
      } else if (command.equals("Scientific Name"))
      {
        statusLabel.setText("Searching by Scientific Name");
      } else if(command.equals("Folk Name"))
      {
        statusLabel.setText("Searching by Folk Name");
      } else if(command.equals("Planet"))
      {
        statusLabel.setText("Searching by Planet");
      } else if(command.equals("Element"))
      {
        statusLabel.setText("Searching by Element");
      } else if(command.equals("Deities"))
      {
        statusLabel.setText("Searching by Deities");
      } else if(command.equals("Powers"))
      {
        statusLabel.setText("Searching by Powers");
      } else if(command.equals("Enter"))
      {
        selectedTarget = true;
        System.out.println("Selected target");
      }
    }
  }
}
