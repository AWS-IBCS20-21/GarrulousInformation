import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WDGUI
{
  private JFrame mainFrame;
  private JLabel headerLabel;
  private JLabel explanationLabel;
  private JLabel statusLabel;
  private JPanel controlPanel;
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

    controlPanel.add(nameButton);
    controlPanel.add(scientificNameButton);
    controlPanel.add(folknameButton);
    controlPanel.add(planetButton);
    controlPanel.add(elementButton);
    controlPanel.add(deitiesButton);
    controlPanel.add(powersButton);

    mainFrame.setVisible(true);
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

    mainFrame.add(nameLabel); //is there a way to do this more efficiently?
    mainFrame.add(scientificLabel);
    mainFrame.add(folkNameLabel);
    mainFrame.add(planetLabel);
    mainFrame.add(elementLabel);
    mainFrame.add(deitiesLabel);
    mainFrame.add(powersLabel);

    if(property.equals("Name"))
    {
      nameLabel.setText("Name: " + matches.get(0).returnName());
      //figure out how to print multiple results ... altho for name it should only be one actually
      //and then add in rest of labels
      //currently showing up BUT now the placement is all screwed up
    } else {
      //will be a little different for the rest of them
      //... can you use a for loop inside the setText method?
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
