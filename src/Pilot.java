/*
 * Programmer:         Zachary Champion
 * Project:            Code Diva
 * Date Last Updated:  14 September 2017
 */

public class Pilot
{
   private static boolean Tracer = false;

   public static void main(String[] args)

   {
      int arg_start; // Tells the pilot where the first of the program arguments is in the array.

      // Checks the first arg to see if it turns Tracer on.
      if ((args.length > 0) && (args[0].toLowerCase().equals("tracer")))
      {
         Tracer = true;
         arg_start = 1;
      }
      else
      {
         arg_start = 0;
      }

      // list all of the passed-in args.
      ListArgs(args);

      if (args.length > 0)
      {  // Run the main checker on each file passed in as an argument.
         RecruitCodeDivas(args, arg_start);
      }

      else
      {  // If the user has passed in no arguments, tell them how to.
         System.out.println("No files to inspect. The Code Diva is bored.");
         System.out.println("Please pass in files as arguments for the Diva to inspect.");
         System.out.println("> \"java Pilot example_java.txt\"");
      }


   }

   private static void RecruitCodeDivas(String[] args, int arg_start)
   {
      for (int a = arg_start; a < args.length; a++)
      {
         Diva NewDiva = new Diva(args[a], Tracer);

         // Do all the checks on the code file.
         NewDiva.CheckOptCurlyBraces();
         NewDiva.CheckBlockIndentation();
         NewDiva.CheckBinaryOpSpaces();
         NewDiva.CheckBraceAlignment();
         NewDiva.CheckVariableCase();
         NewDiva.CheckMultipleStatementLines();
         NewDiva.CheckMaxLineLength();

         // Print out the Diva's findings.
         NewDiva.PrintReport();
      }
   }

   private static void ListArgs(String[] args)
   {
      // List the arguments passed in if there are any to confirm test receipt.
      if (args.length > 0)
      {
         System.out.println("Code files specified to check:");

         for (String arg : args)
         {
            System.out.println(" > " + arg);
         }

      }
      else
      {
         System.out.println("...no args passed.");
      }
   }
}
