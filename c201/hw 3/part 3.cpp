#include "stdafx.h"
#include <iostream>
#include <string>
#include <sstream>
using namespace std;

int main()
{
	while (1)
	{
		int userInt;
		string userStr;

		cout << "Enter an integer: \n";
		cin >> userStr;

		// Check to make sure each character is a digit
		bool bValid = true;
		for ( int i = 0; i < userStr.length(); i++)
			if (!isdigit(userStr[i]))
			{
				bValid = false;
				cout << "Enter a valid integer. \n";
			}
		if (!bValid)
			continue;
		if (bValid) {
			break;
		}
		// converted to a number with stringstream
		stringstream ss;
		ss << userStr;
		ss >> userInt;

		cout << "You entered: " << userInt << endl;
	}
}