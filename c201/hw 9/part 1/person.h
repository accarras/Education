#pragma once
#ifndef PERSON_H
#define PERSON_H
#include <string>
using namespace std;
class Person
{
public:
	Person();
	Person(string aName);
	Person(const char* aName);
	string getName() const;
	Person(const Person&);
	Person& operator =(const Person& rhs);
	friend istream& operator >>(istream& is, Person& p);
	friend ostream& operator <<(ostream& os, const Person& p);
private:
	string name;
};

#endif