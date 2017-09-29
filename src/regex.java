import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class regex
{
   public static void main(String[] args) {
      try
      {
         String whole_string = new Scanner(new File(args[0])).useDelimiter("\\Z").next();

         Pattern line_matcher = Pattern.compile(".*\\n");

         int longCount = 0;

         // Supposed to be "any character except +-*/
         // [^ \+\-\*\/]

         Pattern bad_op_pattern = Pattern.compile("\\p{Alnum}?+[\\Q+-*/%\\E]\\p{Alnum}|\\p{Alnum}[\\Q+-*/%\\E](\\p{Alnum})?+");

         Matcher bad_op_matcher = bad_op_pattern.matcher(whole_string);

         if (bad_op_matcher.find())
         {
           while (bad_op_matcher.find())
            {
               System.out.println("Found \"" + bad_op_matcher.group() + "\" at pos "
                    + bad_op_matcher.start());
               longCount++;
            }
            System.out.println(longCount + " instances found in the string.");
         }
         else
         {
            System.out.println("Pattern match \"" + bad_op_pattern.toString() + "\" not found.");
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
