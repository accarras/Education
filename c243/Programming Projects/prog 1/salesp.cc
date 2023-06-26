/*****************************************************************************
  Author:	Anthony Carrasco(file provided by  Dr. David R. Surma)
  Class:  	C243 Spring 2016
  File name:	main.cc
  Last updated: January 19, 2016
  Description:  This file contains the implementation of the functions found in the salesp.h file. For a full 
                summary of what each function does, see the salesp.h file. 
*****************************************************************************/

#include <iostream>
#include <iomanip>
#include "salesp.h"
using namespace std;

//  Constructor that will initialize the 12 values in the array.
SalesPerson::SalesPerson()
{
  for (int i=0; i<12; i++)
     sales[i] = 0.0;
}


//  Function to input 12 monthly sales figures from the user.
//  This function takes in a sales figure from the user for each month  
//  it calls the function setSales with the number of the month and the salesFigure recorded from the user

void SalesPerson::getSalesFromUser()
{
  double salesFigure;

for (int i = 0; i < 12; i++){
  cout << "Enter number for month " << (i+1) << " : ";
  cin >> salesFigure;
  setSales(i, salesFigure); //calls salesFigure with the month and the number input by the user
  }
}

//  Function to set one of the 12 monthly sales figures.
//  The month value must be from 0 to 11.  If it is
//  not the function will output an error message but will take no
//  corrective action.  The sales amount must be greater than or 
//  equal to zero.  If it is not, an error message is output
//  and the value 0 used.
//
void SalesPerson::setSales( int month, double amount)
{
   if (month < 0 | month > 11){
	cout << "Error, month out of range." << endl;
   }

   if (amount < 0){
	cout <<  "Error, sales cannot be negative. Sales set to 0." << endl;
	sales[month]=0;
	}

   else { 
        sales[month]=amount;
	}
}


//  This function prints the total annual sales to the screen formatted with two
//  decimal places and a dollar sign.
void SalesPerson::printAnnualSales()
{
  cout << setprecision(2)
       << setiosflags(ios::fixed | ios::showpoint )
       << "\nThe total annual sales are: $"
       << totalAnnualSales() 
       << endl;
}


//  This function is a private utility function that computes the annual sales.
double SalesPerson::totalAnnualSales()
{
   double total = 0.0;

   for (int i = 0; i < 12; i++)
    total += sales [i];

   return total;
}


