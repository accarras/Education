#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <algorithm>
#include <string>
using namespace std;

int main()
{
	//takes file input from user
	string fileName;
	cout << "Enter file name" << endl;
	cin >> fileName;


	ifstream inStream;
	inStream.open(fileName);
	if (inStream.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}

	const int n = 50;
	int numArray[n];

	//takes in all of the numbers from the file and puts them into an array
		for (int i = 0; i < n; ++i)
		{
			inStream >> numArray[i];
		}

		//sorts array in ascending order
	sort(numArray, numArray + n);

//opens output file
	ofstream outStream;
	outStream.open("outfile.txt");
	if (outStream.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}

	//writes sorted array to file
	for (int i = 0; i < n; i++) {
		if (numArray[i] > -800000000) {
			outStream << numArray[i] << "\n"; 
		}
	}

	//closes streams
	inStream.close();
	outStream.close();

	cout << "File operations succesful" << endl;
	return 0;

}