import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SQGUI
{
  public JPanel controlPanel;
  public JPanel questionPanel;
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
    controlPanel.add(enterButton);

    myTextField = new JTextField(10);
    controlPanel.add(myTextField);

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
      numQs = Integer.parseInt(myTextField.getText()); //since had to convert from String to int
    } catch (NumberFormatException e)
    {
      //error handling so it doesn't crash if user inputs a letter
      errorMessage();
      try{
        Thread.sleep(3000); //display message for a minute
      } catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
      System.exit(0); //so doesn't print irrelevant information
    }
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
      answer = myTextField.getText(); //since had to convert from String to int
    }
    return answer;
  }

  public void showImmediateResult(boolean answer, String correctAnswer)
  {
    mainFrame.add(correctLabel);
    if(answer)
    {
      correctLabel.setText("Correct");
    } else {
      correctLabel.setText("False: " + correctAnswer);
    }

    try{
      Thread.sleep(600); //so displays for a while and then moves on
    } catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
    correctLabel.setText("");
    mainFrame.remove(correctLabel);
  }

  public void showFinalResult(int correct)
  {
    controlPanel.remove(enterButton);
    controlPanel.remove(myTextField);

    headerLabel.setText("");

    mainFrame.remove(controlPanel);
    explanationLabel.setText("Total correct: " + correct + "/" + numQs);
  }

  public void errorMessage()
  {
    headerLabel.setText("");
    explanationLabel.setText("You don't seem to have entered a valid number");
    controlPanel.remove(enterButton);
    controlPanel.remove(myTextField);
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
