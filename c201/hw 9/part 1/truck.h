#ifndef TRUCK_H
#define TRUCK_H
#include "vehicle.h" 
#include <string>
using namespace std;

class Truck : public Vehicle 
{
public:
	Truck();
	Truck(double loadCapacity, int towingCapacity, string mname, int cylinders, string owner);

	void setLoadCapacity(double i);
	double getLoadCapacity() const;

	void setTowingCapacity(int i);
	int getTowingCapacity() const;

	Truck& operator=(const Truck& rhs);

	Truck(Truck& t);

private:
	double loadCapacity;
	int towingCapacity;
};
#endif
