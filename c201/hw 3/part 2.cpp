#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <algorithm>
#include <string> 

using namespace std;

void merge(ifstream& input1, ifstream& input2, ofstream& output) 
{
	const int size = 50;
	const int fsize = 100;
	int array1[size] = {};
	int array2[size] = {};
	int finalArray[fsize] = {};

	//puts first file into array
	for (int i = 0; i < size; ++i)
	{
		input1 >> array1[i];
	}

	//puts seconf file into array
	for (int j = 0; j < size; ++j)
	{
		input2 >> array2[j];
	}

	//puts first array into final array
	for (int k = 0; k < size; k++) {
		finalArray[k] = array1[k];
	}

	//puts second array into final array
	for (int l = 0; l < size; l++) {
		finalArray[l + size] = array2[l];
	}

	//sorts final array
	sort(finalArray, finalArray + fsize);

	//puts final array into outfile
	for (int m = 0; m < fsize; m++) {
		if (finalArray[m]!= 0) {
			output << finalArray[m] << "\n";
		}
	}


	}


int main()
{
	//takes file input from user
	string fileName1;
	cout << "Enter first file name" << endl;
	cin >> fileName1;

	string fileName2;
	cout << "Enter second file name" << endl;
	cin >> fileName2;

	//opens file stream
	ifstream input1;
	input1.open(fileName1);
	if (input1.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}
	//opens second file stream
	ifstream input2;
	input2.open(fileName2);
	if (input2.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}

	//opens output file
	ofstream output;
	output.open("outfile.txt");
	if (output.fail()) {
		cout << "Output failed. \n";
		exit(1);
	}

	//calls the merge method
	merge(input1, input2, output);

	//closes all used files
	input1.close();
	input2.close();
	output.close();

    return 0;
}

