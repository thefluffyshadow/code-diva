/*
 * Programmer: Zachary Champion
 * Project:    Code Diva
 * File Description:
 * Acts as the managing code for Project.java, which has the meat of the checks in it.
 * Checks any number of code files and prints out the report to summarize any errors found in the code style according
 * to the style as defined in the project specifications.
 */

public class Diva
{
   public static void main(String[] args)
   {
      // list all of the passed-in args.
      ListArgs(args);

      if (args.length > 0)
      {  // Run the main checker on each file passed in as an argument.
         AddProjects(args);
      }
      else
      {  // If the user has passed in no arguments, tell them how to.
         System.out.println("No files to inspect. The Code Diva is bored.");
         System.out.println("Please pass in files as arguments for the Diva to inspect.");
         System.out.println("> \"java Diva example_java.txt\"");
      }


   }

   private static void AddProjects(String[] args)
   {
      for (String project : args)
      {
         Project newProject = new Project(project);

         if (newProject.valid_file)
         {
            // Do all the checks on the code file.
            newProject.CheckOptCurlyBraces();
            newProject.CheckBlockIndentation();
            newProject.CheckBinaryOpSpaces();
            newProject.CheckBraceAlignment();
            newProject.CheckMultipleStatementLines();
            newProject.CheckMaxLineLength();
            newProject.CheckBlankLines();
         }

         // Print out the Project's findings.
         newProject.PrintReport();
      }
   }

   private static void ListArgs(String[] args)
   {
      // List the arguments passed in if there are any to confirm test receipt.
      if (args.length > 0)
      {
         System.out.println("Code files specified to check:");

         for (int arg = 0; arg < args.length; arg++)
         {
            System.out.println((arg + 1) + " > " + args[arg]);
         }
      }
      else
      {
         System.out.println("...no args passed.");
      }
   }
}
