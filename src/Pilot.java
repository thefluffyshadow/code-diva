public class Pilot
{
   private static boolean Tracer = false;

   public static void main(String[] args)

   {
      // Checks all of the args to see if any of them are turn-ons for Tracer.
      for (String arg : args)
      {
         if (arg.toLowerCase().equals("tracer"))
         {
            Tracer = true;
         }
      }

      // Tracer statement to list all of the passed-in args.
      if (Tracer)
      {
         list_args(args);
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
