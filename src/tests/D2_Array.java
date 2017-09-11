/*
* Test for optional curly braces
* Errors on lines:
*    15, 16 - Checking with a nested for loop which has only one statement in it.
*/

package tests;
import java.util.*;
import java.io.*;

public class D2_Array
{
	public static void main(String[] args) throws Exception
    {
       for (int y = 0; y < 8; y++)
          for (int x = 0; x < 8; x++)
             System.out.println(x + " * " + y + " = " + x * y);
	}
}
