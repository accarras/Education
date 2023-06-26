// File: myStringDemo.cpp
#include "stdafx.h"
#include <iostream>
#include "myString.h"
using namespace std;

int main()
{
	myString strA, strC("CCC");
	myString strB("BBB");

	strA = "AAA";

	myString strD = "DDD";
	myString strAB = strA + strB;
	myString strE(strA);

	if (strA == strB)
		cout << "strA: " << strA << " is same as " << "strB: " << strB << endl;
	else
		cout << "strA: " << strA << " is not same as " << "strB: " << strB << endl;

	if (strC < strD)
		cout << "strC: " << strC << " < " << "strD: " << strD << endl;
	else
		cout << "strC: " << strC << " >= " << "strD: " << strD << endl;

	cout << "strAB: " << strAB << endl;
	strAB[2] = strAB[3];
	cout << "strAB: " << strAB << endl;

	myString strF = strA + "FFF";
	cout << "strF: " << strF << endl;
	
}