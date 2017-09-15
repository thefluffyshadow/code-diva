import java.io.*;
import java.nio.file.*;
import java.util.List;

/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  14 September 2017
*/

public class Diva
{
   private String Filename;
   private List<String> FileContents;
   private int NumErrors;
   private String Report;
   private boolean Diva_Tracer;

   Diva (String filename, boolean Tracer)
   {
      this.Filename = filename;
      this.Diva_Tracer = Tracer;
      this.Report = "Report for " + this.Filename + ":";
   }

   void ReadJava()
   {  // Reads into the class variable the entirety of the file contents so we don't have to do it again.
      DeclareCheckerMethod("ReadJava");

      try
      {
         this.FileContents = Files.readAllLines(Paths.get(this.Filename));


         if (this.Diva_Tracer)
         {
            System.out.println("...file read success.");
            for (String line : this.FileContents)
            {
               System.out.println(line);
            }
         }
      }
      catch (IOException e)
      {
         System.out.println("...File read failed.");
         e.printStackTrace();
      }
   }

   void CheckOptCurlyBraces()
   {
      DeclareCheckerMethod("CheckOptCurlyBraces");

      for (int l = 0; l < this.FileContents.size(); l++)
      {
         if (this.Diva_Tracer)
         {
            System.out.println("......checking line " + l + " || " + this.FileContents.get(l));
         }
      }
   }

   void CheckBlockIndentation()
   {
      DeclareCheckerMethod("CheckBlockIndentation");
   }

   void CheckBinaryOpSpaces()
   {
      DeclareCheckerMethod("CheckBinaryOpSpaces");
   }

   void CheckBraceAlignment()
   {
      DeclareCheckerMethod("CheckBraceAlignment");
   }

   void CheckVariableCase()
   {
      DeclareCheckerMethod("CheckVariableCase");
   }

   void CheckMultipleStatementLines()
   {
      DeclareCheckerMethod("CheckMultipleStatementLines");
   }

   void CheckMaxLineLength()
   {
      DeclareCheckerMethod("CheckMaxLineLength");
   }

   void PrintReport()
   {
      System.out.println("\n================================================================\n");
      System.out.println(this.Report);
      System.out.println();
   }

   private void DeclareCheckerMethod(String MethodName)
   {
      if (this.Diva_Tracer)
      {
         System.out.println("...in checker method " + MethodName);
      }
   }
}
