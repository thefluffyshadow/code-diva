import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  2 October 2017
*/

public class Diva
{
   private String Filename;
   private String[] FileHeader;
   private String[] FileContents;
   private int NumErrors;
   private String Report;
   private boolean Diva_Tracer;

   Diva (String filename, boolean Tracer)
   {
      this.Filename = filename;
      this.Diva_Tracer = Tracer;
   }

   private void StartHeader()
   {
      this.Report = "";

      AppendToReport("Style Report by Zachary Champion\n" +
                     "Test Program Author:  " + FileHeader[1] + "\n" +
                     "Error(s) Checked:     " + FileHeader[2] + "\n");
   }

   void ReadJava()
   {  // Reads into the class variable the entirety of the file contents so we don't have to do it again.
      DeclareCheckerMethod("ReadJava");

      try
      {
         // Just read the entire file into a temporary string and split it into parts.
         // Split it by a regex that looks for /* or */ markers.
         String RawFile = new Scanner(new File(this.Filename)).useDelimiter("\\Z").next();
         String[] RawFileArray = RawFile.split("\\/\\*|\\*\\/");

         try
         {
            // Assign each part to the header and the contents of the file according to the assignment specs.
            this.FileHeader = RawFileArray[1].split("\\n");  // Take the second part - that is, after the header comment starts.
            this.FileContents = RawFileArray[2].split("\\n");
         }
         catch (ArrayIndexOutOfBoundsException e)
         {
            System.out.println(RawFile);
            System.exit(8);
         }

         StartHeader();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.exit(0);
      }

      if (this.Diva_Tracer)
      {
         System.out.println("...file read success.");

         for (String line : this.FileContents)
         {
            System.out.println(line);
         }
      }
   }

   void CheckOptCurlyBraces()
   {
      DeclareCheckerMethod("CheckOptCurlyBraces");

      for (int ln = 0; ln < this.FileContents.length - 1; ln++)
      {
         String proc_line = FileContents[ln].trim().toLowerCase();
         String proc_next_line = FileContents[ln + 1].trim().toLowerCase();

         if ((proc_line.startsWith("if") || proc_line.startsWith("else") || proc_line.startsWith("for") ||
               proc_line.startsWith("while"))
               &&
               (!proc_next_line.startsWith("{"))
               &&
               (!proc_line.endsWith("{")))
         {
            this.NumErrors++;
            this.AppendToReport("Optional brace missing from line " + (ln + 1));
         }
      }

      this.AppendToReport();
   }

   void CheckBlockIndentation()
   {
      DeclareCheckerMethod("CheckBlockIndentation");

      int properIndent = 0;

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         if (countLeadingSpaces(this.FileContents[ln]) != properIndent
               && this.FileContents[ln].length() > 0)
         {
            this.AppendToReport("Improper indentation at line " + (ln + 1) + ".");
            this.NumErrors++;
         }

         if (this.FileContents[ln].contains("{"))
         {
            properIndent += 3;
         }
         else if (this.FileContents[ln].contains("{"))
         {
            properIndent -= 3;
         }
      }
   }

   private int countLeadingSpaces(String line)
   {
      int space_count = 0;

      if (line.length() > 0) {
         for (int c = 0; (line.charAt(c) == ' ') && (c < line.length() - 1); c++)
         {
            space_count++;
         }
      }
      else
      {
         return 0;
      }

      return space_count;
   }

   void CheckBinaryOpSpaces() {
//      // TODO: BROKEN
//      DeclareCheckerMethod("CheckBinaryOpSpaces");
//
//      for (int ln = 0; ln < this.FileContents.length; ln++) {
//         String proc_line = FileContents[ln].trim().toLowerCase();
//         String BinaryErrorString = "Binary infix operator missing space(s) on line " + (ln + 1) + ".";
//
//
//         if (((proc_line.indexOf("+") > 0) && proc_line.indexOf("+") < proc_line.length())
//               && (!(proc_line.charAt(proc_line.indexOf("+") - 1) == ' ')
//               || !(proc_line.charAt(proc_line.indexOf("+") + 1) == ' '))
//               && !(proc_line.charAt(proc_line.indexOf("/") - 1) == '+')   // Check to make sure it's not a unary op,
//               && !(proc_line.charAt(proc_line.indexOf("/") + 1) == '+'))  // such as num++.
//         {
//            HandleBinOpErr(BinaryErrorString);  // Append stuff to the report and increase the error count.
//         }
//         else if (((proc_line.indexOf("-") > 0) && proc_line.indexOf("-") < proc_line.length())
//               && (!(proc_line.charAt(proc_line.indexOf("-") - 1) == ' ')
//               || !(proc_line.charAt(proc_line.indexOf("-") + 1) == ' '))
//               && !(proc_line.charAt(proc_line.indexOf("/") - 1) == '-')   // Check to make sure it's not a unary op,
//               && !(proc_line.charAt(proc_line.indexOf("/") + 1) == '-'))  // such as num--.
//         {
//            HandleBinOpErr(BinaryErrorString);  // Append stuff to the report and increase the error count.
//         }
//         else if (((proc_line.indexOf("*") > 0) && proc_line.indexOf("*") < proc_line.length())
//               && (!(proc_line.charAt(proc_line.indexOf("*") - 1) == ' ')
//               || !(proc_line.charAt(proc_line.indexOf("*") + 1) == ' '))
//               && !(proc_line.charAt(proc_line.indexOf("/") - 1) == '/')   // Check to make sure it's not a multi-line
//               && !(proc_line.charAt(proc_line.indexOf("/") + 1) == '/'))  // comment marker.
//         {
//            HandleBinOpErr(BinaryErrorString);  // Append stuff to the report and increase the error count.
//         }
//         else if (((proc_line.indexOf("/") > 0) && proc_line.indexOf("/") < proc_line.length())
//               && (!(proc_line.charAt(proc_line.indexOf("/") - 1) == ' ')
//               || !(proc_line.charAt(proc_line.indexOf("/") + 1) == ' '))
//               && !(proc_line.charAt(proc_line.indexOf("/") - 1) == '/')   // Check to make sure it's not a single
//               && !(proc_line.charAt(proc_line.indexOf("/") + 1) == '/'))  // line comment marker.
//         {
//            HandleBinOpErr(BinaryErrorString);  // Append stuff to the report and increase the error count.
//         }
//         else if (((proc_line.indexOf("%") > 0) && proc_line.indexOf("%") < proc_line.length())
//               && (!(proc_line.charAt(proc_line.indexOf("%") - 1) == ' ')
//               || !(proc_line.charAt(proc_line.indexOf("%") + 1) == ' ')))
//         {
//            HandleBinOpErr(BinaryErrorString);
//         }
//      }
   }

   private void HandleBinOpErr(String binaryErrorString)
   {
      this.AppendToReport(binaryErrorString);
      this.NumErrors++;
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