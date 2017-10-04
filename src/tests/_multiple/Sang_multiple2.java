/*
	Sang Nguyen
	Brace Alignment and Case Check
	Brace Alignment Error at line 15,40,41
   Case Check Error at line 14
*/


import java.util.*;
import java.text.DecimalFormat;

public class Sang_multiple2
{
   public static void main(String [] args)
         {
      double a = 3;
      double b = 4;
      double c = 5;
      // The sides of the triangle

      double perimeter; // Sum of the sides of the triangle
      double s;         // Half of the perimeter
      double area;      // Area of the sides of the triangle
      String input;
      StringTokenizer x;
      
      //perform calculations
            
      perimeter = a + b + c;   // Equation to calculate the perimeter of a triangle
      s= 0.5 * (perimeter);    // Equation to calculate half perimeter
      area = Math.sqrt(s*(s - a)*(s - b) *(s - c));         
      DecimalFormat format1 = new DecimalFormat("#0.00");   

      // Output results
      System.out.println("The sides of the triangles are " +                     
               format1.format (a) + ", " +format1.format (b) +" and " 
		+ format1.format(c) + "\n" + 
               "The perimeter of the triangle is " +format1.format (perimeter)+ "\n" +
               "The area of the triangles is " +format1.format (area));
                   
}
      }