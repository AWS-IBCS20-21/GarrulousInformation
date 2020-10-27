/*Lu Henry-Mitchell
10-21-20
6th period*/

import java.util.*;

public class Halloween
{
  public static void main(String[] args)
  {

    double[][] neighborhood = new double[5][5]; //creates 5x5 matrix to represent houses of neighborhood

    int bestCol = 0; //sets up variables to keep track of the highest generosity rating
    int bestRow = 0;
    double bestVal = 0;

    int col2 = 0;
    int row2 = 0;
    double val2 = 0;

    int col3 = 0;
    int row3 = 0;
    double val3 = 0;

    int col4 = 0;
    int row4 = 0;
    double val4 = 0;

    int col5 = 0;
    int row5 = 0;
    double val5 = 0;

    for(int i = 0; i < 5; i++) //loops through matrix and
    {                          //gives each house a random generosity rating
      for(int j = 0; j < 5; j++)
      {
        neighborhood[i][j] = (Math.random())*10;
      }
    }

    for(int k = 0; k < 5; k++) //goes through each spot of matrix
    {
      for(int l = 0; l < 5; l++) //and checks if generosity rating is higher than current max
      {
        if(neighborhood[k][l] > bestVal) //if not, checks if higher than current second-highest rating, etc
        {
          bestVal = neighborhood[k][l]; //to establish top 5 highest ratings
          bestRow = k;
          bestCol = l;
        } else if(neighborhood[k][l] > val2)
        {
          val2 = neighborhood[k][l];
          row2 = k;
          col2 = l;
        } else if(neighborhood[k][l] > val3)
        {
          val3 = neighborhood[k][l];
          row3 = k;
          col3 = l;
        } else if(neighborhood[k][l] > val4)
        {
          val4 = neighborhood[k][l];
          row4 = k;
          col4 = l;
        } else if(neighborhood[k][l] > val5)
        {
          val5 = neighborhood[k][l];
          row5 = k;
          col5 = l;
        }

        }
      }

      //rows = streets
      //columns = xth house on ...

      bestCol++;
      bestRow++;
      col2++;
      row2++;
      col3++;
      row3++;
      col4++;
      row4++;
      col5++;
      row5++; //so house and street numbers aren't 1 off (otherwise would start at 0 - ex "House 0")

      //add street names... somehow
      String[] streetNames = new String[5]; //creates empty array of street names to correspond to numbers of rows
      int[] rows = {bestRow, row2, row3, row4, row5}; //puts rows (representing streets) in array so I can go through them more easily
      for(int m = 0; m < 5; m++)
      {
        if(rows[m] == 1) //assigns street name to each street based on number of row
        {
          streetNames[m] = "Oak";
        } else if(rows[m] == 2)
        {
          streetNames[m] = "Pine";
        } else if(rows[m] == 3)
        {
          streetNames[m] = "Maple";
        } else if(rows[m] == 4)
        {
          streetNames[m] = "Cedar";
        } else
        {
          streetNames[m] = "Hill";
        }
      }

      System.out.println("Welcome to the Halloween Strategizer 2020 Edition"); //prints welcome message
      System.out.println("Your first destination is house " + bestCol + " on " + streetNames[0] + " Street with a " + Math.round(bestVal*10) + "% chance of candy");
      System.out.println("Your second destination is house " + col2 + " on " + streetNames[1] + " Street with a " + Math.round(val2*10) + "% chance of candy");
      System.out.println("Your third destination is house " + col3 + " on " + streetNames[2] + " Street with a " + Math.round(val3*10) + "% chance of candy");
      System.out.println("Your fourth destination is house " + col4 + " on " + streetNames[3] + " Street with a " + Math.round(val4*10) + "% chance of candy");
      System.out.println("Your fifth destination is house " + col5 + " on " + streetNames[4] + " Street with a " + Math.round(val5*10) + "% chance of candy");
      //prints list of ranked destinations
    }
  }
