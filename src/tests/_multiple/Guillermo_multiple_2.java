/*
 * Guillermo Martinez
 * Operator spaces error
 * optional brace error on line 19
 * bracket alignment error on line 25
 */
package tests._multiple;
import java.util.Scanner;

public class Guillermo_multiple_2
{
    public static void main(String[] args) 
    {
        Scanner UserInput = new Scanner (System.in);
        System.out.println("This program prints the squares of integers up to a desired number");
        System.out.print("print up to which number?: ");
        int finalNumber = UserInput.nextInt();
        for (int i=0 ; i<= finalNumber; i++)
            System.out.println(i*i);
        if (finalNumber < 10) 
        {	
            System.out.println ("Try running again with a larger number");
        }
        else{
            System.out.println("Displaying results for 0 - " + finalNumber );	
        }
		
    }
}
