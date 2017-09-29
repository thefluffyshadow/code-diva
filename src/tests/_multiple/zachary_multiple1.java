package tests._multiple;

/*
 * Programmer: Zachary Champion
 * Lines with errors in the file:
 * 19 - Missing blank line between methods.
 * 36 - No new lines between the methods.
 * 41 - Missing blank line at the end of the file.
 */

public class zachary_multiple1
{
   public static void main(String[] args)
   {
      declare_method("main");

      inc_i();
      dec_i();
   }
   private static void dec_i()
   {
      declare_method("dec_i");

      int i = 10;
      while (i >= 0)
         System.out.println("i: " + --i);
   }

   private static void inc_i()
   {
      declare_method("inc_i");

      for (int i = 0; i < 10; ++i)
      {
         System.out.println("i: " + i);
      }
   }  private static void declare_method(String method_name)
   {
      System.out.println("...in method " + method_name);
   }
}