/*
ThienNgo Nguyen Le
Error type(s): optional braces
The optional braces error is on line 18 and line 30
*/
package tests._opt_braces;

import java.util.Scanner;

public class thien_opt_braces {
   
   public static void main (String[]args) {
      
      Scanner console = new Scanner(System.in);
      double num1, num2; 
      double average;    
      
      System.out.println("This program averages two real numbers.");
      
      System.out.print("Input your first number: ");
      num1 = console.nextDouble();
      System.out.print("Input your second number: ");
      num2 = console.nextDouble();
      
      average = (num1 + num2) / 2.0;
      
      System.out.print(" The average of " + num1);
      System.out.println(" and " + num2 + " is " + average);
      System.out.println(" ThienNgo N. Le");
      
      console.close();
      System.exit(0);
   } 
}
