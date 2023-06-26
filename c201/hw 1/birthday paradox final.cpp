#include <iostream>
#include <stdio.h>
#include <stdlib.h>   
#include <time.h>
using namespace std;

bool SameBirthday(int birthdays[], int numpeople)
//takes an array of birthdays and the number of people and //returns an array of random birthdays from 1 to 365
{
	for (int i = 0; i < numpeople; i++)
	{
		birthdays[i] = rand() % 365 + 1;
		//assigns random birthdays
	}
}

int main(void) {
	int birthdays[50];
	int flag;
	double total;
	int hits[50];
	// number of people "in the room"
	for (int people = 2; people < 50; people++)
	{
		hits[people] = 0;
		// do 5000 trials:
		for (int trial = 0; trial < 5000; trial++)
		//sets the number of trials to do
		{
			SameBirthday(birthdays, people);
			flag = 0;
			// checks to see if any of the dates are matches:
			for (int j = 0; j < people - 1 && flag == 0; j++)
			{
				for (int k = j + 1; k < people && flag == 0; k++)
				//compares every birthday with the one that is next in the array
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
		cout << "For " << people << " people, the probability is " << total << endl;
	}
	return 0;
}