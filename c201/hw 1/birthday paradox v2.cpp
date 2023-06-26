// ConsoleApplication2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

bool SameBirthday(int birthdays[], int numpeople)
{
	for (int i = 0; i < numpeople; i++)
	{
		birthdays[i] = rand() % 365 + 1;
	}
}

int main(void) {
	int birthdays[50];
	//int NUM_TRIALS = 5000;
	int flag;
	double total;
	int hits[50];
	// number of people "in the room"
	for (int people = 2; people < 50; people++)
	{
		hits[people] = 0;
		// do 5000 trials:
		for (int trial = 0; trial < 5000; trial++)
		{
			SameBirthday(birthdays, people);
			flag = 0;
			// compare all pairs (j,k):
			for (int j = 0; j < people - 1 && flag == 0; j++)
			{
				for (int k = j + 1; k < people && flag == 0; k++)
				{
					if (birthdays[k] == birthdays[j])
					{
						hits[people]++;
						flag = 1;
					}
				}
			}
		}
		total = hits[people] / 5000.0;
		std::cout << "For " << people << " people in the room the probability is " << total << std::endl;
	}
	return 0;
}