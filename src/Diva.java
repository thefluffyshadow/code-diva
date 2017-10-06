/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  5 October 2017
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Diva
{
   private String[] FileHeader;
   private int FileHeaderLen;
   private String[] FileContents;
   private int NumErrors;
   private String Report;
   private boolean Diva_Tracer;

   Diva (String filename, boolean Tracer)
   {
      this.Diva_Tracer = Tracer;

      // Reads into the class variable the entirety of the file contents so we don't have to do it again.
      DeclareCheckerMethod("ReadJava");

      try
      {
         // Just read the entire file into a temporary string and split it into parts.
         // Split it by a regex that looks for /* or */ markers.
         String RawFile = new Scanner(new File(filename)).useDelimiter("\\Z").next();
         String[] RawFileArray = RawFile.split("\\Q/*\\E|\\Q*/\\E");

         // Assign each part to the header and the contents of the file according to the assignment specs.
         this.FileHeader = RawFileArray[1].split("\\n");    // Take the second part - that is, after the header
         // comment starts.
         this.FileContents = RawFileArray[2].split("\\n");
         this.FileHeaderLen = this.FileHeader.length + 2;   // Keeps track of how long the header in the original
         // file is so that Diva can give accurate line numbers

         StartHeader();
      } catch (ArrayIndexOutOfBoundsException e)
      {
         e.printStackTrace();
         System.exit(8);
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

   private void StartHeader()
   {
      this.Report = "";

      AppendToReport("Style Report by Zachary Champion\n" +
            "Test Program Author:  " + FileHeader[1] + "\n" +
            "Error(s) Checked:     " + FileHeader[2] + "\n");
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
            AppendToReport("Optional curly brace missing from line " + GetLnNum(ln + 1));
         }
      }
   }

   void CheckBlockIndentation()
   {
      DeclareCheckerMethod("CheckBlockIndentation");

      int properIndent = 0;

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         if (this.FileContents[ln].contains("}")
               && !FileContents[ln].contains("{"))
         {
            properIndent -= 3;
         }

         if (countLeadingSpaces(this.FileContents[ln]) != properIndent
               && this.FileContents[ln].trim().length() > 0
               && this.FileContents[ln].trim().startsWith(""))
         {
            AppendToReport("Improper indentation at line " + GetLnNum(ln) + ".");
            this.NumErrors++;
         }

         if (this.FileContents[ln].contains("{")
               && !FileContents[ln].contains("}"))
         {
            properIndent += 3;
         }
      }
   }

   private int countLeadingSpaces(String line)
   {
      int space_count = 0;

      if (line.length() > 0)
      {
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

   void CheckBinaryOpSpaces()
   {
      /*
      *
      */
      DeclareCheckerMethod("CheckBinaryOpSpaces");

      Pattern BinOpPat = Pattern.compile(
            "[^ \\Q/*+-.\\E][\\Q+-*/%\\E][^\\Q*/+-\\E\\n]|[^\\Q*/+-.\\E][\\Q+-*/%\\E][^ \\Q+-*/=\\E\\n]"
      ); // Heck of a regex, but she works.

      for (int ln = 0; ln < this.FileContents.length; ln++) {
         String BinaryErrorString = "Binary infix operator missing space(s) on line " + GetLnNum(ln) + ".";

         Matcher Clouseau = BinOpPat.matcher(this.FileContents[ln]);

         while (Clouseau.find())
         {
            HandleBinOpErr(BinaryErrorString);
         }
      }
   }

   private void HandleBinOpErr(String binaryErrorString)
   {
      // Helper function to CheckBinaryOpSpaces that handles appending to the style error report and tallying the error.
      AppendToReport(binaryErrorString);
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

      int MaxLineLength = 120;

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         if (FileContents[ln].length() > MaxLineLength)
         {
            AppendToReport("Line " + ln + " is " + (this.FileContents[ln].length() - MaxLineLength) + " characters" +
                  " too long.");
            this.NumErrors++;
         }
      }
   }

   private void FinReport()
   {
      if (this.NumErrors > 0)
      {
         AppendToReport("Total Error Count:  " + this.NumErrors);
      }
      else
      {
         AppendToReport("                                 .''.\n" +
               "       .''.             *''*    :_\\/_:     . \n" +
               "      :_\\/_:   .    .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
               "  .''.: /\\ : _\\(/_  ':'* /\\ *  : '..'.  -=:o:=-\n" +
               " :_\\/_:'.:::. /)\\*''*  .|.* '.\\'/.'_\\(/_'.':'.'\n" +
               " : /\\ : :::::  '*_\\/_* | |  -= o =- /)\\    '  *\n" +
               "  '..'  ':::'   * /\\ * |'|  .'/.\\'.  '._____\n" +
               "      *        __*..* |  |     :      |.   |' .---\"|\n" +
               "       _*   .-'   '-. |  |     .--'|  ||   | _|    |\n" +
               "    .-'|  _.|  |    ||   '-__  |   |  |    ||      |\n" +
               "    |' | |.    |    ||       | |   |  |    ||      |\n" +
               " ___|  '-'     '    \"\"       '-'   '-.'    '`      |____\n" +
               "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
               "                       NO ERRORS\n" +
               "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
         );
      }
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
   }

   private void DeclareCheckerMethod(String MethodName)
   {
      if (this.Diva_Tracer)
      {
         System.out.println("...in checker method " + MethodName);
      }
   }

   private int GetLnNum(int i)
   {
      return this.FileHeaderLen - 1 + i;
   }
}
