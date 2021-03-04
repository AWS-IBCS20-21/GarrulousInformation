import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//NEXT: everything fits on screen, but add some space so it doesn't look squished

public class SQGUI
{
  public JPanel controlPanel;
  public JPanel questionPanel; //possible solution so question shows
  public JFrame mainFrame;
  public JTextField myTextField;
  public JButton enterButton;
  public JLabel headerLabel;
  public JLabel explanationLabel;
  public JLabel correctLabel;
  public boolean chosenQs;
  public boolean enteredAnswer;
  public int numQs;

  public SQGUI()
  {
    chosenQs = false;
    enteredAnswer = false;
    numQs = 0;

    mainFrame = new JFrame("Subjunctive Quiz");
    mainFrame.setSize(600,300);
    mainFrame.setLayout(new GridLayout(4, 1));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    headerLabel = new JLabel("", JLabel.CENTER);
    explanationLabel = new JLabel("", JLabel.CENTER);
    correctLabel = new JLabel("", JLabel.CENTER);

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    questionPanel = new JPanel();
    questionPanel.setLayout(new BorderLayout(100, 100));

    mainFrame.add(headerLabel);
    mainFrame.add(explanationLabel);
    mainFrame.add(questionPanel);
    mainFrame.add(controlPanel);
    mainFrame.add(questionPanel);
    mainFrame.setVisible(true);
  }

  public static void main(String[] args)
  {
    SQGUI test = new SQGUI();
  }

  public int getNumQs()
  {
    enterButton = new JButton("Enter");
    enterButton.addActionListener(new ButtonListener());
    enterButton.setActionCommand("Question number");
    controlPanel.add(enterButton); //why am I adding it here and not to mainFrame?
    //questionPanel.add(enterButton, BorderLayout.CENTER);

    myTextField = new JTextField(10);
    controlPanel.add(myTextField);
    //questionPanel.add(myTextField, BorderLayout.CENTER);

    headerLabel.setText("Welcome to the Subjunctive Quiz");
    explanationLabel.setText("Enter the number of questions for this session");

    while(chosenQs == false)
    {
      try{
        Thread.sleep(100); //not very elegant but wasn't working w timing - was doing stuff too quickly
      } catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
    }
    try{
      numQs = Integer.parseInt(myTextField.getText()); //since had to conver from String to int
    } catch (NumberFormatException e)
    {
      System.out.println("You don't seem to have entered a valid number"); //error handling so it doesn't crash if user inputs a letter
      System.exit(0); //so doesn't print irrelevant information
    }

    System.out.println("Exited target loop");

    //explanationLabel.setText("You have chosen " + numQs + " questions"); //for debugging
    return numQs;
  }

  public String runQuiz(String question)
  {
    String answer = "";
    enteredAnswer = false;
    headerLabel.setText("Enter the correct form of the verb");
    mainFrame.remove(explanationLabel);
    mainFrame.remove(controlPanel);
    questionPanel.add(explanationLabel, BorderLayout.NORTH);
    mainFrame.add(controlPanel);
    //kinda works... displays all text but button and textfield are weridly low now
    explanationLabel.setText("<html><p>" + question + "</p></html>");
    enterButton.setActionCommand("Answered");
    myTextField.setText("");

    while(enteredAnswer == false)
    {
      try{
        Thread.sleep(100); //not very elegant but wasn't working w timing - was doing stuff too quickly
      } catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
      answer = myTextField.getText(); //since had to conver from String to int
    }
    return answer;
  }

  public void showImmediateResult(boolean answer, String correctAnswer)
  {
    mainFrame.add(correctLabel);
    System.out.println("Added correctLabel w no text");
    if(answer)
    {
      correctLabel.setText("Correct");
    } else {
      correctLabel.setText("False: " + correctAnswer);
    }
    System.out.print("set text");

    try{
      Thread.sleep(600); //so displays for a while and then moves on
    } catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
    correctLabel.setText("");
    mainFrame.remove(correctLabel);
    System.out.println("removed label");
  }

  public void showFinalResult(int correct)
  {
    controlPanel.remove(enterButton);
    controlPanel.remove(myTextField);
    //questionPanel.remove(enterButton);
    //questionPanel.remove(myTextField);

    headerLabel.setText("");

    mainFrame.remove(controlPanel);
    explanationLabel.setText("Total correct: " + correct + "/" + numQs); //currently in right place... probably messed up when I fix earlier positioning tho
  }

  private class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String command = e.getActionCommand();
      if(command.equals("Question number"))
      {
        chosenQs = true;
      } else if(command.equals("Answered"))
      {
        enteredAnswer = true;
      }
    }
  }
}
