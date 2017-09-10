package tests;// Import the required API classes
import java.util.Scanner;

public class NestingLoops
{
   public static void main(String[] args)
   {
      // Create the Scanner
      Scanner GetChoice = new Scanner(System.in);
      
      // Ask the user how many rows to provide
      System.out.print("How many rows do you want? ");
      int Rows = GetChoice.nextInt();
      
      // Ask the user how many columns to provide
      System.out.print("How many columns do you want? ");
      int Columns = GetChoice.nextInt();
      System.out.println();
      
      // Print out a column heading across the top
      for (int ColNum = 1; ColNum <= Columns; ColNum++)
         System.out.print("\t" + ColNum);
         
      // End the column heading
      System.out.println();
      
      //Create a for loop for the rows
      for (int Row = 1; Row <= Rows; Row++)
      {
         // Print out the current row number
         System.out.print(Row + "\t");
         
         // Create a for loop for the columns
         for (int Column = 1; Column <= Columns; Column++)
         {
            // Display a value for each column
            System.out.print(Row * Column + "\t");
         }
         
         // Move on to the next row.
         System.out.println();
      }
   }
}