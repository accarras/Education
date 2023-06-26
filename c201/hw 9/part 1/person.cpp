#include "stdafx.h"
#include <string>
#include <cstdlib>
#include <iostream>
#include "person.h"
using namespace std;

Person::Person() {
	name = "nonameP";
}

Person::Person(string aName) {
	name = aName;
}

Person::Person(const char* aName) {
	name = aName;
}

string Person::getName() const {
	return name;
}

Person::Person(const Person&) {
	name = this->getName();
}

Person& Person::operator =(const Person& rhs) {
	if (this == &rhs)
		return *this;
	name = rhs.getName();
	return *this;
}

istream& operator >>(istream& is, Person& p) {
	string nname;
	is >> nname;
	p.name = nname;
	return is;
}

ostream& operator <<(ostream& os, const Person& p) {
	Person pp;
	pp.name = p.getName();
	cout << pp.getName();
	return os;
}
