/*
 * Tests for optional curly-brace.
 * Errors on lines:
 *  15-17 following the initial if.
 *  18-19 following the tailing else statement.
 */

package tests._opt_braces;

public class andrew_opt_braces {
  public static void main(String[] args) {
    boolean isTheWorldOver = true;

    if (isTheWorldOver)
      System.out.println("The world is over! Please send help!");
    else
      System.out.println("No it's not, silly boy!");
  }
}
