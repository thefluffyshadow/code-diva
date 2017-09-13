/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  13 September 2017
*/

public class Diva
{
   String Filename;
   String FileContents;
   int NumErrors;
   String Report;
   private boolean Diva_Tracer;

   Diva (String filename, boolean Tracer)
   {
      this.Filename = filename;
      this.Diva_Tracer = Tracer;
      this.Report = "Report for " + this.Filename + ":";
   }

   void ReadJava()
   {
      DeclareCheckerMethod("ReadJava");
   }

   void CheckOptCurlyBraces()
   {
      DeclareCheckerMethod("CheckOptCurlyBraces");
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
