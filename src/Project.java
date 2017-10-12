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

   Project(String filename)
   /*
   * Creates a "project" (code file to check for style errors).
   * Reads in information about the code file to be checked as properly documented in the header block comment which
   * starts on the first line of the code file.
   */
   {
      this.filename = filename;

      // Reads into the class variable the entirety of the file contents so we don't have to do it again.
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
         System.out.println("File \"" + filename + "\" does not have a proper header.");
         System.exit(1);
      } catch (FileNotFoundException e)
      {
         System.out.println("File \"" + filename + "\" not found.");
         System.exit(1);
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
         }
      }
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
   }

   void CheckNameCase()
   /*
   * Checks for violations of the following conditions:
   * Class names should begin with an uppercase letter.
   * Method names should begin with a lowercase letter.
   * Constants (you know, constant variables) are all uppercase.
   */
   {
      DeclareCheckerMethod("CheckNameCase");

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         System.out.print(ln + " ");
      }
      System.out.println();
   }

   void CheckBlankLines()
   /*
   * Checks to find errors of the following condition:
   * There should be exactly one blank line between methods, between the class header and declarations, and between the
   * end of the declarations and the first method header.
   */
   {
      DeclareCheckerMethod("CheckBlankLines");

      // Create a regular expression pattern that will recognize method headers - matches line fragments
      Pattern methodDecPat = Pattern.compile("[\\w *]+[\\w\\<\\>\\[\\]]+\\s(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");

      // Pattern that recognizes class declarations - matches line fragments
      Pattern classDecPat = Pattern.compile("[\\w ]+class\\s+(\\w+)");

      // Pattern that recognizes variable declarations - matches whole line
      Pattern classVarPat = Pattern.compile("(\\w+ )+(\\w( += +[^;]+)?(, )*)+;");

      for (int ln = 0; ln < this.FileContents.length; ln++)
      {
         // Create the regular expression matchers for each line of the file.
         Matcher methodDecMatch = methodDecPat.matcher(this.FileContents[ln]);
         Matcher classDecMatch = classDecPat.matcher(this.FileContents[ln]);
         Matcher classVarMatch = classVarPat.matcher(this.FileContents[ln]);

         // Check to see if the line looks like a method header or a class declaration, does not have a blank line
         // before it, and is not a return statement.
         if ((ln == 0 || this.FileContents[ln - 1].trim().length() > 0) &&
               !this.FileContents[ln].trim().startsWith("return"))
         {
            if (methodDecMatch.find() && !(this.FileContents[ln].contains("new") ||
                                           this.FileContents[ln].contains("public static void main")) &&
                  !(this.FileContents[ln - 1].trim().startsWith("@") && this.FileContents[ln - 2].trim().length() == 0))
            {

               ReportError("No blank line before a method.", GetLnNum(ln));
            }
            else if (classDecMatch.find())
            {
               ReportError("No blank line before a class.", GetLnNum(ln));
            }
         }

         if ((ln != 0 || ln != this.FileContents.length - 2)
               && classVarMatch.matches()
               && !this.FileContents[ln].trim().startsWith("for"))
         // Ignore the first & last lines; the first line can't have a declaration, neither can the last, and in the odd
         // case that there is a declaration on the first or last line and there are multiple things up there, one of
         // the other checks will throw an error.
         // I'm assuming that it's an unrealistic case that a file has only one statement marked by a semicolon on the
         // first or last line, that statement is a declaration, and the line is less than 120 characters.
         {
            Matcher prevClassVarMatch = classVarPat.matcher(this.FileContents[ln - 1]);
            Matcher nextClassVarMatch = classVarPat.matcher(this.FileContents[ln + 1]);

            // If the previous line is not a blank or a match and the next line is not a blank or a match either, then
            // we have found a violation of the style rule for blank lines - handle it accordingly and report.
            if (!(this.FileContents[ln - 1].trim().equals("") || this.FileContents[ln - 1].startsWith("{") || prevClassVarMatch.matches())
                  || !(this.FileContents[ln + 1].trim().equals("") || nextClassVarMatch.matches()))
            {
               ReportError("Variable declarations not properly surrounded by blank lines.", GetLnNum(ln));
            }
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
      System.out.println("Checking for " + MethodName + " errors...");
   }

   private int GetLnNum(int i)
   {
      return this.FileHeaderLen - 1 + i;
   }
}
