public class Pilot
{
   private static boolean Tracer = false;

   public static void main(String[] args)

   {
      // Checks the first arg to see if it turns Tracer on.
      if (args[0].toLowerCase().equals("tracer"))
      {
         Tracer = true;
      }

      // Tracer statement to list all of the passed-in args.
      if (Tracer)
      {
         list_args(args);
      }


      for (int a = 1; a < args.length; a++)
      {

      }


   }

   private static void list_args(String[] args)
   {
      // List the arguments passed in if there are any to confirm test receipt.
      if (args.length > 0)
      {
         System.out.println("..." + args.length + " args passed...");

         for (int a = 0; a < args.length; a++)
         {
            System.out.println("...arg[" + a + "] passed:  \"" + args[a] + "\"");
         }

      }
      else
      {
         System.out.println("...no args passed.");
      }
   }
}
