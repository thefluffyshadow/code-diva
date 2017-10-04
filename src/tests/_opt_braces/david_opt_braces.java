/*
David Aguiar
Optional Braces
Errors should be on lines 12-13 and 14-15
*/
package tests._opt_braces;

public class david_opt_braces
{
   public static void main(String[] args) 
   {
	   int x = 4;
	   if(x == x) {
			System.out.println("of course its the same");
		} else if (x == x + 1)
	   	System.out.println("...you broke it.");
	   else
	   for(int z = 0; z < 10; z++)
	      System.out.println(z);
   }
}
