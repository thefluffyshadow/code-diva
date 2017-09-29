import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  22 September 2017
*/

public class Diva
{
   private String Filename;
   private String FileContents;
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
         this.FileContents = new Scanner(new File(this.Filename)).useDelimiter("\\Z").next();

         if (this.Diva_Tracer)
         {
            System.out.println("...file read success.\n" +
                               this.FileContents);
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

//      if ((proc_line.startsWith("if") || proc_line.startsWith("else") ||
//            proc_line.startsWith("for") || proc_line.startsWith("while"))
//            &&
//            (!proc_next_line.startsWith("{")))
//      {
//         this.NumErrors++;
//         int error_line = ln + 1;
//         this.AppendToReport("Optional brace missing from line " + error_line);
//      }

      this.AppendToReport();
   }

   void CheckBlockIndentation()
   {
      DeclareCheckerMethod("CheckBlockIndentation");
   }

   void CheckBinaryOpSpaces()
   {
      DeclareCheckerMethod("CheckBinaryOpSpaces");

      // Put together the regular expression that will recognize the binary operations.
//      Pattern BinaryOpPattern = Pattern.compile("[.[^ +-*/]][+-*/][.[^ +-*/]");

//      String proc_line = FileContents.get(ln).trim().toLowerCase();
//
//      if ((proc_line.contains("+") || proc_line.contains("-") || proc_line.contains("*") || proc_line.contains("/"))
//            && !(proc_line.contains("/*") || proc_line.contains("*/") || proc_line.startsWith("*")
//            || proc_line.contains("++") || proc_line.contains("--"))
//            && !(proc_line.contains(" + ") || proc_line.contains(" - ") || proc_line.contains(" * ")
//            || proc_line.contains(" / "))) // TODO: TURN THIS INTO REGEX
//      {
//         this.AppendToReport("Binary op missing spaces around it on line " + ln);
//         this.NumErrors++;
//      }
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

