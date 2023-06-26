#include "stdafx.h"
#include<iostream>
using namespace std;

void toHan(int disks, int startPeg, int tmp, int endPeg);

int main() {

	int disks;

	cout << "How many disks?: ";
	cin >> disks;

	//disks and pegs
	toHan(disks, 1, 2, 3);
	return 0;
}

void toHan(int disks, int startPeg, int temp, int endPeg) {
	//checks to see if there are disks
	if (disks != 0)
	{
		//moves disk from start peg to temp peg
		toHan(disks - 1, startPeg, endPeg, temp);
		cout << "Move disk from " << startPeg << " to " << temp << endl;
		//arguments change order to reflect where the disks should go
		toHan(disks - 1, endPeg, temp, startPeg);
	}
}