#include "stdafx.h"
#include <iostream>
#include <string>
#include <iomanip>
#include "administrator.h"

using namespace std;

namespace SavitchEmployees
{
	Administrator::Administrator() : SalariedEmployee(), title("admin"), responsibility("n-a"), supervisor("boss name"), annualSalary(0) {
//deliberately left empty
	}

	Administrator::Administrator(string theName, string theSSN, string theTitle, string theResponsibility, string theSupervisor, double theAnnualSalary){
		setName(theName);
		setSsn(theSSN);
		setTitle(theTitle);
		setResponsibility(theResponsibility);
		setSupervisor(theSupervisor);
		setAnnualSalary(theAnnualSalary);
	}

	void Administrator::setTitle(string s) {
		title = s;
	}
	string Administrator::getTitle() {
		return title;
	}
	void Administrator::setResponsibility(string s) {
		responsibility = s;
	}
	string Administrator::getResponsibility() {
		return responsibility;
	}
	void Administrator::setSupervisor(string s) {
		supervisor = s;
	}
	string Administrator::getSupervisor() {
		return supervisor;
	}
	void Administrator::setAnnualSalary(double d) {
		annualSalary = d;
	}
	double Administrator::getAnnualSalary() {
		return annualSalary;
	}

	void Administrator::getAdminData()
	{
		string nname;
		string sssn;
		string ttitle;
		string rrespons;
		string ssupervisor;
		double ssalary;


		cout << "Enter the administrators name: ";
		cin.ignore();
		getline(cin, nname);
		Administrator::setName(nname);
		cout << endl;

		cout << "Enter SSN: ";
		cin >> sssn;
		Administrator::setSsn(nname);
		cout << endl;

		cout << "Enter the administrators title: ";
		cin >> ttitle;
		Administrator::setTitle(ttitle);
		cout << endl;

		cout << "Enter an area of responsibility: ";
		cin >> rrespons;
		Administrator::setResponsibility(rrespons);
		cout << endl;

		cout << "Enter Supervisor name: ";
		cin.ignore();
		getline(cin, ssupervisor);
		Administrator::setSupervisor(ssupervisor);
		cout << endl;

		cout << "Enter Salary: ";
		cin >> ssalary;
		Administrator::setAnnualSalary(ssalary);
		cout << endl;

	}

	void Administrator::print() {
		cout << setprecision(2) << fixed;

		cout << "Name: " << getName() << endl;
		cout << "Annual Salary: $" << getAnnualSalary() << endl;
		cout << "Social Security Number: " << getSsn() << endl;
		cout << "Title: " << getTitle() << endl;
		cout << "Responsibility: " << getResponsibility() << endl;
		cout << "Supervisor: " << getSupervisor() << endl;
		cout << "Annual Salary: " << getAnnualSalary() << endl;
	
	}
};