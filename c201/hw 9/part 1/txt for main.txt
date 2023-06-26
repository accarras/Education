#include "stdafx.h"
#include <iostream>
#include "person.h"
#include "vehicle.h"
#include "truck.h"
using namespace std;

//Testing Program
int main()
{
	Vehicle v1("Ford", 4, "James Carter");
	Vehicle v2;
	Vehicle v3(v1);
	v2 = v1;
	cout << "\nCar v1 (constructed) Data:\n";
	cout << v1.getMfgrName() << endl;
	cout << v1.getNumberCyl() << endl;
	cout << v1.getOwner() << endl;
	cout << "\nCar v2 (assigned) Data:\n";
	cout << v2.getMfgrName() << endl;
	cout << v2.getNumberCyl() << endl;
	cout << v2.getOwner() << endl;
	cout << "\nCar v3 (copy constructed) Data:\n";
	cout << v3.getMfgrName() << endl;
	cout << v3.getNumberCyl() << endl;
	cout << v3.getOwner() << endl;
	Truck t1(80.0, 20000, "Mac", 8, "John Q. Driver");
	// 80 tons gross vehicle weight, 20,000 lbs tow capacity
	Truck t3(t1), t2;
	t2 = t1;
	cout << "\nTruck T1 (constructed) data:\n";
	cout << t1.getMfgrName() << endl;
	cout << t1.getNumberCyl() << endl;
	cout << t1.getOwner() << endl;
	cout << t1.getLoadCapacity() << endl;
	cout << t1.getTowingCapacity() << endl;
	cout << "\nTruck T2 (assigned) data:\n";
	cout << t2.getMfgrName() << endl;
	cout << t2.getNumberCyl() << endl;
	cout << t2.getOwner() << endl;
	cout << t2.getLoadCapacity() << endl;
	cout << t2.getTowingCapacity() << endl;
	cout << "\nTruck T3 (copy constructed) data:\n";
	cout << t3.getMfgrName() << endl;
	cout << t3.getNumberCyl() << endl;
	cout << t3.getOwner() << endl;
	cout << t3.getLoadCapacity() << endl;
	cout << t3.getTowingCapacity() << endl;
	return 0;
}
// end testing application