/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  13 September 2017
*/

public class Diva
{
   public String Filename;
   public String FileContents;
   public int NumErrors;
   public String Report;
   private boolean Diva_Tracer;

   Diva (String filename, boolean Tracer)
   {
      // Read the file and put it in file_contents.bbg
      this.Diva_Tracer = Tracer;
   }

   static void ReadJava()
   {}

   static void CheckOptCurlyBraces()
   {}

   static void CheckBlockIndentation()
   {}

   static void CheckBinaryOpSpaces()
   {}

   static void CheckBraceAlignment()
   {}

   static void CheckVariableCase()
   {}

   static void CheckMultipleStatementLines()
   {}

   static void CheckMaxLineLength()
   {}

   static void PrintReport()
   {}

   private static void DeclareCheckerMethod(String MethodName)
   {
      if (this.Diva_Tracer)
      {
         System.out.println("...in checker method " + MethodName);
      }
   }
}
