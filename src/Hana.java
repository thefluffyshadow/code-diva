/*
* Programmer:         Zachary Champion
* Project:            Project Code Diva
* Date Last Updated:  11 September 2017
*/

public class Hana {

   boolean Tracer = true;

   public static void main(String[] args) {

      // List the arguments passed in if there are any to confirm test receipt.
      if (args.length > 0) {
         System.out.println("..." + args.length + " args passed...");
         for (String arg : args) {
            System.out.println("...arg passed:  \"" + arg + "\"");
         }

      } else {
         System.out.println("...no args passed.");
      }

   }
}
