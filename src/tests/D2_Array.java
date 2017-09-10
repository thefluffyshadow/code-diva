package tests;
/*
*  Programmer:	Zachary Champion
*  Project:		This program is just a temporary one which I'm using in class in order to keep up with the questions the teacher is going over.
*  Class:		CS 1050 008
*/

import java.util.*;
import java.io.*;

public class D2_Array
{
	public static void main(String[] args) throws Exception
	{
		double [][] C = new double [12][12];
		
		for (int y = 0; y < C.length; y++)
		{
			for (int x = 0; x < C[y].length; x++)
			{
				C[y][x] = x * y;
				System.out.println("C(" + x + "," + y + ") = " + C[y][x]);
			}
		}
		
	}
}
