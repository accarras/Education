#include "stdafx.h"
#include<iostream>
using namespace std;

int fib(int);

int fib(int x)
{
	//if x < 0, return what x is
	if (x < 2)
		return x;
	//run fib() again, with arguments decreased
	//returns adjacent numbers added together

		return (fib(x - 1) + fib(x - 2));
}

int main() {
	while(1) {
		char userInput;
		int length;
		cout << "I will display the first N Fibonacci numbers." << endl;
		cout << "Enter a value for n (must be >= 0): ";
		cin >> length;
		for (int i = 0; i < length; i++) {
			cout << fib(i+1) << endl;
		}
		cout << "Y/y to continue, anything else quits" << endl;
		cin >> userInput;
		if ((userInput != 'y') && (userInput != 'Y'))
			break;
	}
		
	return 0;
}