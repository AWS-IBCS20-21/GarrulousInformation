/*Lu Henry-Mitchell
10-21-20
6th period*/

import java.util.*;

public class Halloween
{
  public static void main(String[] args)
  {

    double[][] neighborhood = new double[5][5];

    int bestCol = 0;
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

    for(int i = 0; i < 5; i++)
    {
      for(int j = 0; j < 5; j++)
      {
        neighborhood[i][j] = (Math.random())*10;
      }
    }

    for(int k = 0; k < 5; k++)
    {
      for(int l = 0; l < 5; l++)
      {
        if(neighborhood[k][l] > bestVal)
        {
          bestVal = neighborhood[k][l];
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
      System.out.println("Top val: " + bestVal);
      System.out.println("Second val: " + val2);
      System.out.println("Third val: " + val3);
      System.out.println("Fourth val: " + val4);
      System.out.println("Fifth val: " + val5);

      //rows = streets
      //columns = xth house on ...
      //maybe use if statements?

      System.out.println("The first house to visit is house " + bestCol + "on Street " + bestRow);
    }
  }
