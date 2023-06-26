// File: myString.cpp
#include "stdafx.h"
#include <string.h>
#include <iostream>
#include <string>
#include "myString.h"
using namespace std;

// conversion (and default) constructor converts char * to String
myString::myString(const char *s)
: length((s != 0) ? strlen(s) : 0)
{
	cout << "Conversion (and default) constructor: " << s << endl;
	strPtr = new char[length + 1];
	for (int i = 0; i < length; i++)
		strPtr[i] = s[i];
	strPtr[length] = '\0';
} // end myString conversion constructor


// is this String empty?
bool myString::operator!() const
{
	return length == 0;
} // end function operator! 

// Is this String less than right String?
bool myString::operator<(const myString & Rhs) const
{
	return strcmp(strPtr, Rhs.strPtr) < 0;
} // end function operator<

// return reference to character in String as a modifiable lvalue
char &myString::operator[](int subscript)
{
	// test for subscript out of range
	if (subscript < 0 || subscript >= length)
	{
		cerr << "Error: Subscript " << subscript
			<< " out of range" << endl;
		exit(1); // terminate program
	} // end if

	return strPtr[subscript]; // non-const return; modifiable lvalue
} // end function operator[]

// return reference to character in String as rvalue
char myString::operator[](int subscript) const
{
	// test for subscript out of range
	if (subscript < 0 || subscript >= length)
	{
		cerr << "Error: Subscript " << subscript
			<< " out of range" << endl;
		exit(1); // terminate program
	} // end if

	return strPtr[subscript]; // returns copy of this element
} // end function operator[]

// overloaded output operator
ostream &operator<<(ostream &output, const myString &s)
{
	output << s.strPtr;
	return output; // enables cascading
} // end function operator<<

// overloaded input operator
istream &operator>>(istream &input, myString &s)
{
	char temp[100]; // buffer to store input
	input >> temp;
	s = temp; // use myString class assignment operator
	return input; // enables cascading
} // end function operator>>


//=========================================================
// The following functions are to be completed by students
//=========================================================

// copy constructor
myString::myString(const myString &copy)
{
	length = copy.length;
	strPtr = new char[length];
	for (int i = 0; i < (length); i++)
	{
		strPtr[i] = copy.strPtr[i];
	}

	if (strPtr[length - 1] != '\0') { strPtr[length - 1] = '\0'; }
	// to be completed by students
} // end myString copy constructor

// Destructor
myString::~myString()
{
	delete[] strPtr;
	// to be completed by students
} // end ~myString destructor

// overloaded = operator, v1
const myString &myString::operator =(const myString& Rhs)
{
	length = Rhs.length;
	delete[] strPtr;
	strPtr = new char[length];
	for (int i = 0; i < length; i++) {
		strPtr[i] = Rhs.strPtr[i];
	}

	if (strPtr[length] != '\0') { strPtr[length] = '\0'; }

	return *this;
	// to be completed by students
} // end function operator= v1

// overloaded = operator, v2
const myString& myString::operator =(const char *Rhs)
{	
	length = strlen(Rhs);
	delete[] strPtr;
	strPtr = new char[length];
	for (int i = 0; i < length; i++) {
		strPtr[i] = Rhs[i];
	}

	if (strPtr[length] != '\0') { strPtr[length] = '\0'; }

	return *this;
	// to be completed by students
} // end function operator= v2

const myString myString::operator +(const myString &Rhs)
{
	myString ex2;
	ex2.length = (((length) + (Rhs.length)+1));
	ex2.strPtr = new char[ex2.length];

	for (int i = 0; i < (length); i++) {
		ex2.strPtr[i] = strPtr[i];
	}
	int j = 0;
	for (int k = (length), j = 0; k < (ex2.length); k++, j++)
	{
		ex2.strPtr[k] = Rhs.strPtr[j];
	}

	if (ex2[ex2.length-1] != '\0') { ex2[ex2.length - 1] = '\0'; }
	
	return ex2;
}

// Is this String equal to right String?
bool myString::operator ==(const myString & Rhs) const
{
	if (length == Rhs.length) {
		int i = length;
		int j = 0;
		int n = 0;
		while (n != i) {
			if (strPtr[n] != Rhs.strPtr[n]) { 
				j++; 
			}
			n++;
		}
		if (j != 0) { return false; }
		else { return true; }
	}
	else {
		return false;
	}

	// to be completed by students
} // end function operator==