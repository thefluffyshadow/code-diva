import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  2 October 2017
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

      for (int ln = 0; ln < this.FileContents.size() - 1; ln++)
      {
         String proc_line = FileContents.get(ln).trim().toLowerCase();
         String proc_next_line = FileContents.get(ln + 1).trim().toLowerCase();

         if ((proc_line.startsWith("if") || proc_line.startsWith("else") || proc_line.startsWith("for") ||
               proc_line.startsWith("while"))
               &&
               (!proc_next_line.startsWith("{")))
         {
            this.NumErrors++;
            int error_line = ln + 1;
            this.AppendToReport("Optional brace missing from line " + error_line);
         }
      }

      this.AppendToReport();
   }

   void CheckBlockIndentation()
   {
      DeclareCheckerMethod("CheckBlockIndentation");
   }

   void CheckBinaryOpSpaces()
   {
      DeclareCheckerMethod("CheckBinaryOpSpaces");

      for (int ln = 0; ln < this.FileContents.size(); ln++)
      {
         String proc_line = FileContents.get(ln).trim().toLowerCase();

         if ((proc_line.contains("+") || proc_line.contains("-") || proc_line.contains("*") || proc_line.contains("/"))
               && !(proc_line.contains("/*") || proc_line.contains("*/") || proc_line.startsWith("*")
               || proc_line.contains("++") || proc_line.contains("--"))
               && !(proc_line.contains(" + ") || proc_line.contains(" - ") || proc_line.contains(" * ")
               || proc_line.contains(" / "))) // TODO: TURN THIS INTO REGEX
         {
            this.AppendToReport("Binary op missing spaces around it on line " + ln);
            this.NumErrors++;
         }

      }
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

   private void FinReport()
   {
      this.AppendToReport("Total Error Count:  " + this.NumErrors);
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
      this.FinReport();
      System.out.println("================================================================\n");
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