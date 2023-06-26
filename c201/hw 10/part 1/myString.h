//File: myString.h

#ifndef MYSTRING_H
#define MYSTRING_H

#include <iostream>
#include <string>
using namespace std;

class myString
{
public:

	myString(const char * s ="");	// conversion/default constructor		
	myString(const myString&);		// copy constructor
	~myString();		// destructor

	int getLength() { return length; };

	const myString& operator= (const myString &Rhs);	// assignment operator, v1 
	const myString& operator= (const char *Rhs);		// assignment operator, v2 
	const myString operator+ (const myString &Rhs);	// concatenation operator

	friend ostream& operator << (ostream &Out, const myString &S);	// insertion operator
	friend istream& operator>> (istream &In, myString &S);			// extraction operator

	bool operator!() const; // is String empty?
	bool operator==(const myString &) const; // test s1 == s2
	bool operator<(const myString &) const; // test s1 < s2

	char &operator[](int); // subscript operator (modifiable lvalue)
	char operator[](int) const; // subscript operator (rvalue)

	// test s1 != s2
	bool operator!=(myString &Rhs) const
	{
		return !(*this == Rhs);
	} // end function operator!=

	// test s1 > s2
	bool operator>(const myString& Rhs) const
	{
		return Rhs < *this;
	} // end function operator>

	// test s1 <= s2
	bool operator<=(const myString& Rhs) const
	{
		return !(Rhs < *this);
	} // end function operator <=

	// test s1 >= s2
	bool operator>=(const myString &Rhs) const
	{
		return !(*this < Rhs);
	} // end function operator>=

private:
	int length;		// string length (not counting null terminator)
	char * strPtr;	// pointer to start of pointer-based string
};

#endif