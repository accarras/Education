#include "stdafx.h"
#include <string>
#include <cstdlib>
#include <iostream>
#include "person.h"
#include "vehicle.h"
using namespace std;

Vehicle::Vehicle() {
	setMfgrName("noManfV");
	setNumberCyl(0);
	setOwner("noOwnV");
}

Vehicle::Vehicle(string theMName, int theCylinders, string theOwner) {
	setMfgrName(theMName);
	setNumberCyl(theCylinders);
	setOwner(theOwner);
}

void Vehicle::setMfgrName(string s) {
	mfgrName = s;
}
string Vehicle::getMfgrName() const{
	return mfgrName;
}

void Vehicle::setNumberCyl(int i) {
	numberCyl = i;
}
int Vehicle::getNumberCyl() const{
	return numberCyl;
}

void Vehicle::setOwner(string p) {
	owner = Person::Person(p);
}
string Vehicle::getOwner() const{
	return owner.getName();
}

Vehicle& Vehicle::operator =(const Vehicle& rhs) {
	if (this == &rhs)
		return *this;
	setMfgrName(rhs.getMfgrName());
	setNumberCyl(rhs.getNumberCyl());
	setOwner(rhs.getOwner());
	return *this;
}

Vehicle::Vehicle(Vehicle& v) {
	setMfgrName(v.getMfgrName());
	setNumberCyl(v.getNumberCyl());
	setOwner(v.getOwner());
}
