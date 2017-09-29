/**
 * Created by aloom on 9/8/2017.
 */

/*
No curlys contained after a single statement if statement.
 */
package tests._multiple;

class aaronloomis_multiple2
{

    public static void main(String[]args)
    {
       aaronloomis_multiple2 test1 = new aaronloomis_multiple2();
        test1.CircleOfNumbers(10,1);
    }
    int CircleOfNumbers(int n, int firstNumber)
    {
        int oppositeNum;
        if(firstNumber < n / 2)
		{
			oppositeNum = firstNumber + (n / 2);
		}
        else
        {
		oppositeNum = firstNumber - (n / 2);
        }
        return oppositeNum;
    }
}