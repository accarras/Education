#include <stdio.h>
//#include "stdafx.h"
#include <iostream>
#include <cstring>
using namespace std;

int main()
{

	char ch1[256];
	char ch2[256];
	int count = 0;

	cout << "enter string 1:" << endl;
	cin >> ch1;

	cout << "enter string 2:" << endl;
	cin >> ch2;

	int length1 = strlen(ch1);

	int length2 = strlen(ch2);

	if (length1 == length2)
//checks to see if length of strings is the same
//if they are not, then strings are not anagrams
	{
		for (int i = 0; i < length1; i++)
//checks the next character of ch1
		{
			for (int j = 0; j < length1; j++)
//cheks the next character of ch2
			{
				if (ch1[i] == ch2[j])
//checks if the character in each position is the same for both //character
				{
					count++;
//if ch1[i] is equal to ch2[j] then counts up

					break;
				}
			}
		}

		if (count == length1)
			cout << "Anagrams" << endl;
		else
			cout << "Not Anagrams" << endl;
	}
	else
		cout << "Not Anagrams" << endl;

}
