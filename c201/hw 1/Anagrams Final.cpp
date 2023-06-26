// ConsoleApplication4.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <cstring>
#include <algorithm>
using namespace std;


bool IsAnagram2(string str1, string str2)
{

	//These next two lines will sort the strings to make them easier to compare
	sort(str1.begin(), str1.end());
	sort(str2.begin(), str2.end());
	//This line tells us whether the first and second string have the same characters once they are sorted.
	return str1 == str2;
}

int main()
{
	//declaring my variables
	//I should come up with more colorful names
	string string1;
	string string2;

	//takes the input from the user
	//takes the first string
	cout << "Enter First String \n";
	getline(cin, string1);
	//takes the second string
	cout << "Enter Second String \n";
	getline(cin, string2);

	//if the strings are anagrams, prints "Anagrams"
	//else prints "Not anagrams.
	//Calls the previously define function IsAnagram
	//gives the strings that the user inputted to IsAnagram
	if (IsAnagram2(string1, string2))
	{
		cout << "Anagrams \n";
	}
	else {
		cout << "Not Anagrams \n";
	}
	return 0;

}