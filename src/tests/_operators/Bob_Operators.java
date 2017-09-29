/*
	Dong(Bob) Lee
	09/10/17
*/
import java.util.*;

class SortingPractice{

   public static void main(String args[]){
   
      int[] list = {1,3,4,5,2,6,7,0};
      int temp = 0;
      
      for(int pass = 0; pass < (list.length-1); pass++){
         int smallestValueIndex = pass;
         for(int index = pass+1; index < list.length; index++){
            if(list[smallestValueIndex] > list[index]){
               smallestValueIndex = index;
            }
         }
         temp = list[pass];
         list[pass] = list[smallestValueIndex];
         list[smallestValueIndex] = temp;
      }
      for(int c = 0; c < list.length; c++){
         System.out.println(list[c]);
      }
   }
}