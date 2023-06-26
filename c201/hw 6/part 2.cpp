#include "stdafx.h"
#include <iostream>
using namespace std;

int fib(int);

int fib(int n){

	int num1 = 1;
	int num2 = 1;
	int finalNum = 0;

	for (int i = 0; i < n; i++)
		//runs until it has looped n times
	{
		//if number is < 2, return number
		if (i == 0) {
			finalNum = 1;
		}
		else if (i == 1) {
			finalNum = 1;
		}
		//else finalnum is num1 + num2, with num1 and num 2 increased each time
		else 
		{
			finalNum = num1 + num2;
			num1 = num2;
			num2 = finalNum;
		}
	}

	return finalNum;

}


int main(){
	while (1) {
		char userInput;
		int length;
		cout << "I will display the first N Fibonacci numbers." << endl;
		cout << "Enter a value for n (must be >= 0): ";
		cin >> length;
		//returns all of the fibonacci numbers
		for (int i = 0; i < length; i++) {
			cout << fib(i+1) << endl;
		}
		cout << "Y/y to continue, anything else quits" << endl;
		cin >> userInput;
		if ((userInput != 'y') && (userInput != 'Y')) {
			break;
		}
	}

	return 0;

}