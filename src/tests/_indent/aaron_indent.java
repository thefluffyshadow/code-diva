/*
 * Aaron Loomis
 * Block Indentation
No curlys contained after a single statement if statement.
 */

public class aaron_indent
{
    public static void main(String[]args)
    {
        aaron_indent test1 = new aaron_indent();
        test1.CircleOfNumbers(10,1);
    }
	
    int CircleOfNumbers(int n, int firstNumber)
    {
        int oppositeNum;
        if(firstNumber < n / 2){
			oppositeNum = firstNumber + (n / 2);
		}
        else
        {
			oppositeNum = firstNumber - (n / 2);
        }
        return oppositeNum;
    }
}
