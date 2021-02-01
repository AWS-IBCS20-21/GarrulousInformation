import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WDGUI
{
  private JFrame mainFrame;
  private JLabel headerLabel;
  private JLabel explanationLabel;
  private JLabel statusLabel;
  private JPanel controlPanel;
  private JTextField textField;
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

    headerLabel = new JLabel("", JLabel.CENTER);
    explanationLabel = new JLabel("", JLabel.CENTER);
    statusLabel = new JLabel("", JLabel.CENTER);
    statusLabel.setSize(350, 100);

    //mainFrame.addWindowListener(new WindowAdapter())
    /*{
      public void windowClosing(WindowEvent windowEvent)
      {
        System.exit(0); //ig sets up exit on exit?
      }
    }*/
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
    headerLabel.setText("Enter the " + property + " of the herb you would like to find");

    controlPanel.remove(nameButton);
    controlPanel.remove(scientificNameButton);
    controlPanel.remove(folknameButton);
    controlPanel.remove(planetButton);
    controlPanel.remove(elementButton);
    controlPanel.remove(deitiesButton);
    controlPanel.remove(powersButton);

    textField = new JTextField();
    controlPanel.add(textField);
    target = textField.getText();
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
      } else //if(command.equals("Powers"))
      {
        statusLabel.setText("Searching by Powers");
      }
      property = command;
      selectedProperty = true;
    }
  }
}
