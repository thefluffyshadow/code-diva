/** 
   Sang Nguyen
   Blank Lines
   2 Errors on line 15 and line 23
*/


/** This program is used to calculate the perimeter, semi-perimeter,
    and the area of a triangle using method.
*/
package tests._blank_lines;
import java.util.Scanner;
class Sang_blank_lines {

   static Scanner console = new Scanner (System.in);

   public static void main (String [] args){
      double num1 = 3;
      double num2 = 4;
      double num3 = 5;
      double area = 0.0;
      double perimeter = 0.0;
      perimeter = calcPerimeter(num1, num2, num3);
      area = calcArea(num1, num2, num3);
      }
     public static double calcPerimeter(double a,double b,double c){
     return (a + b + c);
     }//End calcPerimeter

     //********************************************************************
     //Calculate area

     public static double calcArea(double a, double b, double c){
     double s = 0;
     s = calcPerimeter(a, b, c) / 2.0;
      return ( Math.sqrt (s * (s - a) * (s - b) * (s - c)));
     }//End calcArea
}//End class