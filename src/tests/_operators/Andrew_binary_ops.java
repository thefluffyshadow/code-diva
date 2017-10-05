/*
Andrew Hodder
Space around binary operators

On line 22, I put the unary postfix incrementer after of i to attempt to fool the parser. The line should read:

  val = (val * i / 2 + i++) >> 8;

There is also a simpler second error on line 23 where the string is added to val.
*/

package samples;


public class Andrew_binary_ops
{

   public static void main(String[] args)
   {
      int val = 10;

      for (int i = 0; i < 10;)
      {
         val = (val*i/2+i++)>>8;  // TODO: Check if the ">>" is actually one of the errors that need to be caught.
         System.out.println("Here is a weird number that has gone through a grinder "+val);
      }
   }
}
