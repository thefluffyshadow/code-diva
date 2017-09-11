/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  11 September 2017
*/

public class Hana
{
   private static boolean Tracer = true;

   public static void main(String[] args)
   {
      list_args(args);
   }

   private static void list_args(String[] args)
   {
      // List the arguments passed in if there are any to confirm test receipt.
      if ((Tracer) && (args.length > 0))
      {
         System.out.println("..." + args.length + " args passed...");

         for (int a = 0; a < args.length; a++)
         {
            System.out.println("...arg[" + a + "] passed:  \"" + args[a] + "\"");
         }

      }
      else if (Tracer)
      {
         System.out.println("...no args passed.");
      }
   }
}
