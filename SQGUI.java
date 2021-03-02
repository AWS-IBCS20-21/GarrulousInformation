import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class SQGUI
{
  public JPanel controlPanel;
  public JFrame mainFrame;
  public JTextField myTextField;
  public JButton enterButton;
  public JLabel headerLabel;
  public JLabel explanationLabel;

  public SQGUI()
  {
    mainFrame = new JFrame("Subjunctive Quiz");
    mainFrame.setSize(600,300);
    mainFrame.setLayout(new GridLayout(4, 1));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    headerLabel = new JLabel("", JLabel.CENTER);
    explanationLabel = new JLabel("", JLabel.CENTER);

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    mainFrame.add(headerLabel);
    mainFrame.add(explanationLabel);
    mainFrame.add(controlPanel);
    mainFrame.setVisible(true);
  }

  public static void main(String[] args)
  {
    SQGUI test = new SQGUI();
  }

  public int getNumQs()
  {
    enterButton = new JButton("Enter");
    //enterButton.addActionListener(new ButtonListener());
    controlPanel.add(enterButton); //why am I adding it here and not to mainFrame?

    myTextField = new JTextField(10);
    controlPanel.add(myTextField);

    headerLabel.setText("Welcome to the Subjunctive Quiz");
    explanationLabel.setText("Enter the number of questions for this session");

    int numQs = 0;
    return numQs;
  }

  public void runQuiz()
  {

  }

}

//SUPER not working, we'll get back to this
/*private class ButtonListener implements ActionListener
{
  public void actionPerformed(ActionEvent e)
  {
    //do something
  }
}*/
