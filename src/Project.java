/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  9 October 2017
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project
{
   private String filename;
   private String[] FileHeader;
   private int FileHeaderLen;
   private String[] FileContents;
   private int NumErrors;
   private String Report;
   private boolean Diva_Tracer;

   Project(String filename, boolean Tracer)
   /*
   * Creates a "project" (code file to check for style errors).
   * Reads in information about the code file to be checked as properly documented in the header block comment which
   * starts on the first line of the code file.
   */
   {
      this.Diva_Tracer = Tracer;
      this.filename = filename;

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
         // file is so that Project can give accurate line numbers

         StartReport();
      } catch (ArrayIndexOutOfBoundsException e)
      {
         e.printStackTrace();
         System.exit(1);
      } catch (FileNotFoundException e)
      {
         System.out.println("File \"" + filename + "\" not found");
         System.exit(1);
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

   private void StartReport()
   /*
   * Instantiates the report header with the relevant information to the project report.
   */
   {
      this.Report = "Style Report by Zachary Champion\n" +
            "Filename:             " + this.filename + "\n" +
            "Test Program Author:  " + FileHeader[1] + "\n" +
            "Error(s) Checked:     " + FileHeader[2] + "\n";
   }

   void CheckOptCurlyBraces()
   /*
   * Checks to find errors of the following conditions:
   * Optional curly braces are required. For if, else, and all loops (for, enhanced for, while, do-while), a single-
   * statement body should be surrounded by curly braces.
   */
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
            ReportError("Optional brace missing.", GetLnNum(ln + 1));
         }
      }
   }

   void CheckBlockIndentation()
   /*
   * Checks to find errors of the following conditions:
   * The following blocks should be indented three spaces more than their headers: class bodies, method bodies,
   * loop bodies, if-else bodies, instance declarations.
   *
   * Uses the { and } characters to mark when the indentation should be increased and decreased, respectively.
   */
   {
      DeclareCheckerMethod("CheckBlockIndentation");

      int properIndent = 0;

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         int foundIndent = countLeadingSpaces(this.FileContents[ln]);

         // Before checking the current line for indentation, check to see if it should be indented less.
         // If it closes a body of some kind, it should be indented less.
         if (this.FileContents[ln].contains("}")
               && !FileContents[ln].contains("{"))
         {
            properIndent -= 3;
         }

         // Check if the line uses tabs as it shouldn't.
         if (this.FileContents[ln].contains("\t"))
         {
            ReportError("Line contains the evil TAB for indent.", GetLnNum(ln));
         }

         // Otherwise, check if the indentation is the proper number of spaces.
         else if (foundIndent != properIndent
               && this.FileContents[ln].trim().length() > 0
               && this.FileContents[ln].trim().startsWith(""))
         {
            ReportError("Improper indentation - " + foundIndent + " spaces found, should be "
                  + properIndent + ".", GetLnNum(ln));
         }

         // After checking the current line, check to see if the next line should be indented more.
         // If it opens a body of some kind, it should be indented more.
         if (this.FileContents[ln].contains("{")
               && !FileContents[ln].contains("}"))
         {
            properIndent += 3;
         }
      }

      AppendToReport();  // Separate the sections in the error report
   }

   private int countLeadingSpaces(String line)
   /*
   * Counts the leading spaces in a line as a helper function for CheckBlockIndentation.
   */
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
   /*
   * Checks to find errors of the following condition:
   * All binary math operators should have exactly one space surrounding them.
   */
   {
      DeclareCheckerMethod("CheckBinaryOpSpaces");

      Pattern BinOpPat = Pattern.compile(
            "[^ \\Q/*+-.\\E][\\Q+-*/%\\E][^\\Q*/+-\\E\\n]|[^\\Q*/+-.\\E][\\Q+-*/%\\E][^ \\Q+-*/=\\E\\n]"
      ); // Heck of a regex, but she works.

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         Matcher Clouseau = BinOpPat.matcher(this.FileContents[ln]);

         while (Clouseau.find())
         {
            ReportError("Improper spacing around binary math operator (+, -, *, /, or %).", GetLnNum(ln));
         }
      }

      AppendToReport();  // Separate the sections in the error report
   }

   void CheckBraceAlignment()
   /*
   * Checks to find errors of the following condition:
   * Opening and closing curly braces should be on a line by themselves.
   *
   * Their indentation is already checked by CheckBlockIndentation(), so this function does not look for that.
   */
   {
      DeclareCheckerMethod("CheckBraceAlignment");

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         if (this.FileContents[ln].contains("{") && !this.FileContents[ln].trim().equals("{"))
         {
            ReportError("Curly brace should be on its own line.", GetLnNum(ln));
         }

         if (this.FileContents[ln].contains("}") && !this.FileContents[ln].trim().equals("}"))
         {
            ReportError("Curly brace should be on its own line.", GetLnNum(ln));
         }
      }

      AppendToReport();  // Separate the sections in the error report
   }

   void CheckVariableCase()
   {
      DeclareCheckerMethod("CheckVariableCase");
   }

   void CheckMultipleStatementLines()
   /*
   * Individual error chosen by me.
   * Checks to find errors of the following condition:
   * All lines should contain at most one Java code statement.
   */
   {
      DeclareCheckerMethod("CheckMultipleStatementLines");

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         String[] split = this.FileContents[ln].split(";", -1);

         if (split.length - 1 > 1 && !this.FileContents[ln].trim().startsWith("for"))
         {
            ReportError("Multiple statements.", GetLnNum(ln));
            this.NumErrors++;

            if (this.Diva_Tracer)
            {
               for (String sec : split)
               {
                  System.out.print("[" + sec + "]");
               }
            }
         }
      }

      AppendToReport();  // Separate the sections in the error report
   }

   void CheckMaxLineLength()
   /*
   * Individual error chosen by me.
   * Checks to find errors of the following condition:
   * All lines should be at most 120 characters in length.
   */
   {
      DeclareCheckerMethod("CheckMaxLineLength");

      int MaxLineLength = 120;

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         if (FileContents[ln].length() > MaxLineLength)
         {
            ReportError("Line is too long (" +
                  this.FileContents[ln].length() + " chars > " + MaxLineLength + ").", GetLnNum(ln));
         }
      }

      AppendToReport();  // Separate the sections in the error report
   }

   void CheckBlankLines()
   {
      DeclareCheckerMethod("CheckBlankLines");
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

   private void ReportError(String errortype, int line)
   {
      AppendToReport("Line " + line + " | Error:  " + errortype);
      this.NumErrors++;
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
