/*Lu Henry-Mitchell
9-29-20
6th period */

import java.util.*;

public class Calculator
{

public Calculator()
{

}

  public static void main (String[] args)
  {
    Calculator test = new Calculator();
    Scanner scanny = new Scanner(System.in);
    int operation = 0;
    double num1 = 0;
    double num2 = 0;

    System.out.println("This calculator can perform several operations. " +
    "Enter a number to choose an operation:");
    System.out.println("1.) Add \n2.) Muliply \n3.) Exponent");
    operation = scanny.nextInt();
    if(operation != 1 && operation != 2 && operation != 3){
      System.out.println("You don't seem to have entered one of the available options");
    } else {
      System.out.println("Enter a number:");
      num1 = scanny.nextDouble();
      System.out.println("Enter a second number:");
      num2 = scanny.nextDouble();
    }


    if(operation == 1)
    {
      System.out.println("The sum of " + num1 + " and " + num2 + " is " + test.add(num1, num2));
    } else if(operation == 2)
    {
      System.out.println("The product of " + num1 + " and " + num2 + " is " + test.multiply(num1, num2));
    } else if(operation == 3)
    {
      System.out.println(num1 + " to the power of " + num2 + " is " + test.toThePowerOf(num1, num2));
    }
  }

  public double add(double num1, double num2)
  {
    return num1+num2;
  }

  public double multiply(double num1, double num2)
  {
    return num1*num2;
  }

  public double toThePowerOf(double num1, double num2)
  {
    return Math.pow(num1, num2);
  }
}
