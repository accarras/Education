#ifndef VEHICLE_H
#define VEHICLE_H

#include "person.h"
#include <string>
using namespace std;

class Vehicle{

public:
	Vehicle();
	Vehicle(string mname, int cylinders, string owner);
	
	void setMfgrName(string s);
	string getMfgrName() const;

	void setNumberCyl(int i);
	int getNumberCyl() const;

	void setOwner(string p);
	string getOwner() const;

	Vehicle& operator=(const Vehicle& rhs);

	Vehicle(Vehicle& v);


private:
	string mfgrName;
	int numberCyl;
	Person owner;
};
#endif

