package tests;

/*
 * Programmer: Zachary Champion
 * Lines with errors in the file:
 * 23 - Missing blank line between methods.
 * 31, 32, 33, 34, 35 - nested loop statements with no curly braces.
 * 59 - No new lines between the methods.
 * 63 - Missing blank line at the end of the file.
 */

public class zachary_multiple2
{
   public static void main(String[] args)
   {
      declare_method("Main");

      dec_i();

      inc_i();

      inc_2d();
   }
   private static void inc_2d()
   {
      declare_method("inc_2d");

      int z = 42;

      for (int i = 0; i <= 8; i++)
         for (int j = 0; j <= 8; j++)
            if (i * j == z)
               System.out.println("42 is the answer to life, the universe, and everything!");
            else
               System.out.println(i * j);

   }

   private static void dec_i()
   {
      declare_method("dec_i");

      int i = 10;
      while (i >= 0)
      {
         i--;
         System.out.println("i: "+ i);
      }
   }

   private static void inc_i()
   {
      declare_method("inc_i");

      for (int i = 0; i < 10; ++i)
      {
         System.out.println("i: " + i);
      }
   }
   private static void declare_method(String method_name)
   {
      System.out.println("...in method " + method_name);
   }
}