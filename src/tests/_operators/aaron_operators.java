/**
 * Created by aloom on 9/8/2017.
 */

/*
No curlys contained after a single statement if statement.
 */
package tests._operators;

public class aaron_operators
{

    public static void main(String[]args)
    {
        aaron_operators test1 = new aaron_operators();
        test1.CircleOfNumbers(10,1);
    }
	
    int CircleOfNumbers(int n, int firstNumber)
    {
        int oppositeNum;
        if(firstNumber < n /2){
			oppositeNum = firstNumber + (n / 2);
		}
        else
        {
			oppositeNum = firstNumber - (n / 2);
        }
        return oppositeNum;
    }
}