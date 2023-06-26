#include "stdafx.h"
#include <iostream>
#include "administrator.h"
using namespace std;
using namespace SavitchEmployees;
int main()
{
	Administrator adm1, adm2;
	cout << "We have two administrators" << endl;
	cout << "Now enter the data for the first administrator." << endl;
	adm1.getAdminData();
	cout << endl << "Now enter the data for the second administraor." << endl;
	adm2.getAdminData();
	adm1.print();
	cout << "Here is the pay check for " << adm1.getName() << endl;
	adm1.printCheck();
	adm2.print();
	cout << "Here is the pay check for " << adm2.getName() << endl;
	adm2.printCheck();
}