/*
Mark Huntington
Brace alignment and block indentation
Lines 25, 27, 32, and 34 do not follow brace alignment standard.
Lines 27 and 33 have if statements that aren't indented properly.
*/
package tests._multiple;
import java.util.*;

public class Mark_multiple
{
   static Scanner console = new Scanner(System.in);
   
   
   public static void main (String [] args) throws Exception
   {
   int side1;
   int side2;
   int side3;   
   double perimeter;        
   double halfPerimter;       
   double area;     
 
    
    System.out.println("Input side 1.");
    side1 = console.nextInt();
	if (side1 <= 0){
	System.out.println("Please input a number greater than zero.");
	side1 = console.nextInt();}
     
    System.out.println("Input side 2.");
    side2 = console.nextInt();
	if (side2 <= 0){
	              System.out.println("Please input a number greater than zero.");
	              side2 = console.nextInt();}
     
    System.out.println("Input side 3.");
    side3 = console.nextInt();
	if (side3 <= 0)
	{
	   System.out.println("Please input a number greater than zero.");
	   side3 = console.nextInt();
	}
     
    perimeter = side1 + side2 + side3;
     
    halfPerimter = perimeter/2;
     
    area = Math.sqrt(halfPerimter * (halfPerimter - side1) * (halfPerimter - side2) * (halfPerimter - side3));
     
    System.out.println(" The area of your triangle is " + area);
    
	}
}