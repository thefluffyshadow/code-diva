import java.io.*;
import java.nio.file.*;
import java.util.List;

/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  18 September 2017
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
      this.Report = "Report for " + this.Filename + ":" + "\n\n";
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

      for (int ln = 0; ln < this.FileContents.size(); ln++)
      {
         String proc_line = FileContents.get(ln).trim().toLowerCase();

         if (proc_line.startsWith("if") || proc_line.startsWith("else") || proc_line.startsWith("for") ||
               proc_line.startsWith("while") || proc_line.startsWith("do"))
         {
            this.AppendToReport(FileContents.get(ln - 1));
            this.AppendToReport(FileContents.get(ln));
            this.AppendToReport(FileContents.get(ln + 1));
            this.AppendToReport();
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

   private void AppendToReport()
   {
      this.Report += '\n';
   }

   private void AppendToReport(String line)
   {
      this.Report += line + '\n';
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
