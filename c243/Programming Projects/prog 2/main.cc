/********************************************************************
  Author:        Anthony Carrasco, file provided by Dr. Surma
  Class:         C243 Spring 2016
  File name:     main.cc
  Last updated:  Feb 1, 2016
  Description:   This file contains test for the functions that are
                 defined in the my_array class. Four objects of the
                 class my_array are instantiated. The member function
                 randomize(), input(), and hamming() are tested. The
                 overloaded operators "==" and "!=" are also tested.
                 Details of how each of these functions work are given
                 in the my_array class description.
*********************************************************************/

#include "my_array.h"
#include <iostream>
using namespace std;

int main()
{
  int user_inpt;                        // This variable stores the size of the arrays that the user inputs

/*
  My_array a(10);                       // declare object passing array size

  for (int i=0; i<a.get_size(); i++)    // assign values to object's interal
    a[i] = i;                           // array

  cout << a;                            // screen output of the object (overloaded cout)
  cout << a[15] << endl;                // test the "safe" access class method
*/

  cout << "Enter size of arrays: " ;
  cin >> user_inpt;                     // User inputs size for the four
                                        // instantiated objects objects of the my_array class
  My_array b(user_inpt);
  My_array c(user_inpt);
  My_array d(user_inpt);
  My_array e(user_inpt);
  b.randomize();                       // randomizes the contents of the array
  c = b;
  cout << (c == b) << endl;            // outputs a true(1) to demonstrate the comparison operator

  d.input();
  cout << "Hamming distance between first and third arrays: "
       << d.hamming(b)                 // this shows the hamming distance between
       << endl;                        // the input array and the randomized one

  e.randomize();                       // randomizes the contents of the array

  cout << "Is d == e?: " << (d==e) << endl;
                                       // this should return a false,
                                       // demonstrating the "==" operator
  cout << "Is d != e?: " << (d!=e) << endl;
                                       // this should return a true
                                       // demonstrating the "!=" operator

  return 0;

}
