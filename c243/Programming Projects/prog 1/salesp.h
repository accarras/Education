/*********************************************************************************
  Author:		Anthony Carrasco(File provided by Dr. David R. Surma)
  Class:		C243 Spring 2016
  File name:		salesp.h
  Last updated:		January 19, 2016
  Description:		This file contains the definitions and interfaces for the sales
			class. It contains the signatures for two private functions. The first is 
                        totalAnnualSales, which calculates the yearly sales amount. The second is setSales, 
                        which takes in a month and a value, and sets that month to the value. There is also a 
                        private array, sales, which contains the 12 values for each month. There are three 
                        public functions. The function SalesPerson is a constructor for the SalesPerson class. 
                        The function getSalesFromUser reads in a sales value for each month. The function 
                        printAnnualSales prints the values of the sales array to the screen. 

 **********************************************************************************/

#ifndef SALESP_H 
#define SALESP_H

class SalesPerson{

private:
  double totalAnnualSales ();  // utility function to compute the yearly (total) sales amount
  
  void setSales(int, double); // sets a single monthly sales amount.  Must test for non-perfect input.
 
  double sales[12]; // array to hold the monthly sales figures

 public:
   SalesPerson();  // constructor

   void getSalesFromUser(); // input monthly sales figures from user
  
   void printAnnualSales(); // outputs amount to the terminal
};

 #endif
