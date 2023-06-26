#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <iomanip>
#include <vector>
using namespace std;

struct employee {
	//structure of employee, and everything an employee contains
	
	string firstN;
	string lastN;
	string SSN;
	double hourlyPay;
	int hours;
	double overtimePay;
	char status;
	double timePay;
	double netPay;
};

//declares functions
void userInput(vector<employee>& eArr, string fileName);
void payCalc(vector<employee>& eArr);
void outPut(const vector<employee>& eArr);


int main()
{
	string unused;
	int numLines = 0;

	string fileName;
	cout << "Please enter a file name to read: ";
	cin >> fileName;

	ifstream inStream;
	inStream.open(fileName);
	if (inStream.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}	

	while (getline(inStream, unused))
		numLines++;

	vector<employee> eArr(numLines);

	userInput(eArr, fileName);
	payCalc(eArr);
	outPut(eArr);

	inStream.close();
	
	/*delete eArr;
	eArr = NULL;*/

	return 0;
}

void userInput(vector<employee>& eArr, string fileName) {
	//enables user input
	/*string fileName;
	cout << "Please enter a file name to read: ";
	cin >> fileName;*/

	ifstream inStream;
	inStream.open(fileName);
	if (inStream.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}

	for (int i = 0; i < eArr.size(); i++) {
		inStream >> eArr[i].firstN;
		inStream >> eArr[i].lastN;
		inStream >> eArr[i].SSN;
		inStream >> eArr[i].hourlyPay;
		inStream >> eArr[i].hours;
		inStream >> eArr[i].status;
	}
}

void payCalc(vector<employee>& eArr) {
	//calculates pay for each employee
	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].hours > 40) {
			eArr[i].timePay = (eArr[i].hourlyPay * 40);
		}
		else {
			eArr[i].timePay = (eArr[i].hours * eArr[i].hourlyPay);
		}
	}

	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].hours > 40) {
			eArr[i].overtimePay = ((1.5 * eArr[i].hourlyPay) * (eArr[i].hours - 40));
		}
		else {
			eArr[i].overtimePay = 0;
		}
	}

	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].hours > 40) {
			eArr[i].netPay = eArr[i].timePay + eArr[i].overtimePay;
		}
		else {
			eArr[i].netPay = eArr[i].timePay;
		}
	}

	//subtracts union fees
	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].status == 'F') {
			eArr[i].netPay = (eArr[i].netPay - 10);
		}
	}
}

void outPut(const vector<employee>& eArr) {

	cout << "Information of the Employee:" << endl;

	cout << right
		<< setw(6) << "First"
		<< setw(10) << "Last"
		<< setw(15) << "SSN"
		<< setw(15) << "Hourly Wage"
		<< setw(13) << "Hours Worked"
		<< setw(12) << "Time Pay"
		<< setw(15) << "Overtime Pay"
		<< setw(10) << "Status"
		<< setw(10) << "Net Pay" << endl;

	std::cout << std::setprecision(2) << std::fixed;
	//sets decimals to two places for numbers

	for (int i = 0; i < eArr.size(); i++) {
		cout << right <<
			setw(6) << eArr[i].firstN <<
			setw(10) << eArr[i].lastN <<
			setw(15) << eArr[i].SSN <<
			setw(15) << eArr[i].hourlyPay <<
			setw(13) << eArr[i].hours <<
			setw(12) << eArr[i].timePay <<
			setw(15) << eArr[i].overtimePay <<
			setw(10) << eArr[i].status <<
			setw(10) << eArr[i].netPay << endl;
	}
}
