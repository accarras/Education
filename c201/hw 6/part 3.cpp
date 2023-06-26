#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

void reverse(char *userInput, int inpStrt, int inpEnd);
void output(char *userInput);

int main()
{

	char userInput[80];
	int inpStrt;
	int inpEnd;
	//gets user input, and stores it in char array
	cout << "Please enter a string less than 80 characters long." << endl;
	cin.get(userInput, 80);
	//user inputs start of reversal
	cout << "Please input start position: ";
	cin >> inpStrt;
	//user inputs end of reversal
	cout << "Please input end position: ";
	cin >> inpEnd;

	//calls reverse function
	reverse(userInput, inpStrt, inpEnd);
	//calls output function
	output(userInput);

	return 0;
}

void reverse(char *userInput, int inpStrt, int inpEnd) {

	char *head;
	char *tail;
	char temp;
	int length = strlen(userInput);
	//initializes head and tail pointers to be the user input
	head = userInput;
	tail = userInput;

	//makes tail the end position
	for (int i = 0; i < (inpEnd); i++) {
		tail++;
	}

	//makes head the start position
	for (int i = 0; i < (inpStrt); i++) {
		head++;
	}

	//switches the value of head and tail until the two meet in the middle of the size of the part of the string to be reversed
	for (int i = 0; i < ((inpEnd-inpStrt) / 2); i++)
	{
		temp = *tail;
		*tail = *head;
		*head = temp;
		head++;
		tail--;
	}
}

void output(char *userInput) {
	//output the character array
	cout << "Your string reversed is: ";
	for (int i = 0; i < (strlen(userInput)); i++) {
		cout << userInput[i];
	}
	cout << endl;
}
