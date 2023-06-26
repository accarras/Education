#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <iomanip>
#include <vector>
using namespace std;

class employee {
	//structure of employee, and everything an employee contains

private:
	string firstN;
	string lastN;
	string SSN;
	double hourlyPay;
	int hours;
	double overtimePay;
	char status;
	double timePay;
	double netPay;

public:
	employee(string theFName, string theLName, string theSSN, double theHourlyWage, double theHoursWorked, char theStatus);
	employee();

	static void userInput(vector<employee>& eArr, string fileName);
	static void payCalc(vector<employee>& eArr);
	static void outPut(const vector<employee>& eArr);

	inline void setFirstN(string s) { firstN = s; }
	inline string getFirstN() const { return firstN; }

	inline void setLastN(string s) { lastN = s; }
	inline string getLastN() const { return lastN; }

	inline void setSSN(string s) { SSN = s; }
	inline string getSSN() const { return SSN; }

	inline void setHourlyPay(double d) { hourlyPay = d; }
	inline double getHourlyPay() const { return hourlyPay; }

	inline void setHours(int i) { hours = i; }
	inline double getHours() const { return hours; }

	inline void setOvertimePay(double d) { overtimePay = d; }
	inline double getOvertimePay() const { return overtimePay; }

	inline void setStatus(char c) { status = c; }
	inline char getStatus() const { return status; }

	inline void setTimePay(double d) { timePay = d; }
	inline double getTimePay() const { return timePay; }

	inline void setNetPay(double d) { netPay = d; }
	inline double getNetPay() const { return netPay; }


};

employee::employee(string theFName, string theLName, string theSSN, double theHourlyWage, double theHoursWorked, char theStatus) {
	setFirstN(theFName);
	setLastN(theLName);
	setSSN(theSSN);
	setHourlyPay(theHourlyWage);
	setHours(theHoursWorked);
	setStatus(theStatus);
}

employee::employee() {
	firstN = "name1";
	lastN = "name2";
	SSN = "000-00-0000";
	hourlyPay = 0;
	hours = 0;
	status = 'P';
}

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

	employee::userInput(eArr, fileName);
	employee::payCalc(eArr);
	employee::outPut(eArr);

	inStream.close();

	return 0;
}

void employee::userInput(vector<employee>& eArr, string fileName) {
	//enables user input

	ifstream inStream;
	inStream.open(fileName);
	if (inStream.fail()) {
		cout << "Input failed. \n";
		exit(1);
	}

	for (int i = 0; i < eArr.size(); i++) {
		string first;
		string second;
		string third;
		double fourth;
		double fifth;
		char sixth;

		inStream >> first;
		inStream >> second;
		inStream >> third;
		inStream >> fourth;
		inStream >> fifth;
		inStream >> sixth;

		eArr[i] = employee(first, second, third, fourth, fifth, sixth);
			
	}
}

void employee::payCalc(vector<employee>& eArr) {
	//calculates pay for each employee
	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].getHours() > 40) {
			eArr[i].setTimePay(eArr[i].getHourlyPay() * 40);
		}
		else {
			eArr[i].setTimePay(eArr[i].getHours() * eArr[i].getHourlyPay());
		}
	}

	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].getHours() > 40) {
			eArr[i].setOvertimePay((1.5 * eArr[i].getHourlyPay()) * (eArr[i].getHours() - 40));
		}
		else {
			eArr[i].setOvertimePay(0);
		}
	}

	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].getHours() > 40) {
			eArr[i].setNetPay(eArr[i].getTimePay() + eArr[i].getOvertimePay());
		}
		else {
			eArr[i].setNetPay(eArr[i].getTimePay());
		}
	}

	//subtracts union fees
	for (int i = 0; i < eArr.size(); i++) {
		if (eArr[i].getStatus() == 'F') {
			eArr[i].setNetPay(eArr[i].getNetPay() - 10);
		}
	}
}

void employee::outPut(const vector<employee>& eArr) {

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
			setw(6) << eArr[i].getFirstN() <<
			setw(10) << eArr[i].getLastN() <<
			setw(15) << eArr[i].getSSN() <<
			setw(15) << eArr[i].getHourlyPay() <<
			setw(13) << eArr[i].getHours() <<
			setw(12) << eArr[i].getTimePay() <<
			setw(15) << eArr[i].getOvertimePay() <<
			setw(10) << eArr[i].getStatus() <<
			setw(10) << eArr[i].getNetPay() << endl;
	}
}
