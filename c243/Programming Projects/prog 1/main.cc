/*****************************************************************************
  Author:	Anthony Carrasco
  Class:  	C243 Spring 2016
  File name:	main.cc
  Last updated: January 19
  Description:  This file contains the "main" function that interacts
		with the SalesPerson class.  A single object of this
		class is instantiated and two member functions invoked.
		The first gets the values for the SalesPerson object from the user.
		The second prints the values to the screen. Details of the way values 
		are input, and the proper ranges, as well as printing
		details are given in the SalesPerson class description.
******************************************************************************/

//  This files houses main.
//

#include <iostream>
#include "salesp.h"

int main()
{
  SalesPerson s;        	//  Instantiates an object of the SalesPerson class

  s.getSalesFromUser(); 	//  Gets the values from the user
  s.printAnnualSales();		//  Prints the values to the screen

 return 0;
}

