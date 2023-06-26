#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

void reverse(char *userInput);
void output(char *userInput);

int main()
{
	
	char userInput[80];

	//gets user input, and stores it in char array
	cout << "Please enter a string less than 80 characters long." << endl;
	cin.get(userInput, 80);

	//calls reverse function
	reverse(userInput);
	//calls output function
	output(userInput);

    return 0;
}

void reverse(char *userInput) {
	
	char *head;
	char *tail;
	char temp;
	int length = strlen(userInput);
	//initializes head and tail pointers to be the user input
	head = userInput;
	tail = userInput;

	//makes tail the last character in the array
	for (int i = 0; i < (length - 1); i++) {
		tail++;
	}

	//switches the value of head and tail until the two meet in the middle of the array
	for (int i = 0; i < (length / 2); i++)
	{
		temp = *tail;
		*tail = *head;
		*head = temp;
		head++;
		tail--;		
	}
}

void output(char *userInput){
	//output the character array
	cout << "Your string reversed is: ";
	for (int i = 0; i < (strlen(userInput)); i++) {
		cout << userInput[i];
	}
	cout << endl;
}
