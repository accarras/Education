#include "stdafx.h"
#include "truck.h"
#include "vehicle.h"
#include <string>
#include <cstdlib>
#include <iostream>

using namespace std;

Truck::Truck() {
	Truck::setTowingCapacity(0);
	Truck::setLoadCapacity(0);
	Truck::setMfgrName("none");
	Truck::setNumberCyl(0);
	Truck::setOwner("none");
}

Truck::Truck(double loadCapacity, int towingCapacity, string mname, int cylinders, string owner) {
	Truck::setLoadCapacity(loadCapacity);
	Truck::setTowingCapacity(towingCapacity);
	Truck::setMfgrName(mname);
	Truck::setNumberCyl(cylinders);
	Truck::setOwner(owner);
}

void Truck::setLoadCapacity(double i) {
	loadCapacity = i;
}
double Truck::getLoadCapacity() const {
	return loadCapacity;
}

void Truck::setTowingCapacity(int i) {
	towingCapacity = i;
}
int Truck::getTowingCapacity() const {
	return towingCapacity;
}

Truck& Truck::operator =(const Truck& rhs) {
	if (this == &rhs)
		return *this;
	setLoadCapacity(rhs.getLoadCapacity());
	setTowingCapacity(rhs.getTowingCapacity());
	setMfgrName(rhs.getMfgrName());
	setNumberCyl(rhs.getNumberCyl());
	setOwner(rhs.getOwner());
	return *this;
}

Truck::Truck(Truck& t) {
	setLoadCapacity(t.getLoadCapacity());
	setTowingCapacity(t.getTowingCapacity());
	setMfgrName(t.getMfgrName());
	setNumberCyl(t.getNumberCyl());
	setOwner(t.getOwner());
}
