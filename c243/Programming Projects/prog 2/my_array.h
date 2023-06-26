/********************************************************************
  Author:        Tony Carrasco, file provided by Dr. Surma
  Class:         C243 Spring 2016
  File name:     my_array.h
  Last updated:  Feb 1, 2016
  Description:   This file contains definitions and interfaces for the
                 My_array class. This class contains an array and the
                 size of the array. Modified operators include "==" and
                 "!=", which check to see if My_arrays are equal or not
                 equal. Newly implemented functions are hamming(), which
                 calculates the number of positions which are different
                 between two arrays, input(), which fills in an array with
                 user input, and randomize(), which fills an array with
                 random numbers.
 *********************************************************************/

#ifndef MY_ARRAY_H
#define MY_ARRAY_H

#include <iostream>
using namespace std;

class My_array {
 protected:
  int size;                 // size is for the dimension of
  int *array;               // the internal array

 public:
  // Constructor with given size, can be used as default constructor.
  My_array(int the_size = 0);

  // Destructor. If the array is not empty, it must be deallocated.
  ~My_array();

  // Copy constructor
  My_array(My_array &data);

  // Assignment operator
  My_array &operator=(My_array &data);

  // Deletes the array and sets the size to 0.
  void empty();

  // Resize the array.
  void resize(int the_size = 0);

  // Access an element of the array. If the index (subscript) is out
  // of the array, it prints an error message and exits the program.
  int &operator[](int index);

  //Checks to see if two My_array objects are equal 
  bool operator ==(My_array &data);

  // Checks to see if two My_array objects are not equal
  bool operator !=(My_array &data);

  // Returns the size of the array.
  int get_size();

  //counts the number of elements in the two arrays that are different
  int hamming(My_array &data);

  // Initialize the elements of the array with random values between 0 and the maximum limit
  void randomize(int limit=100); 

  // Input the elements of the array
  void input();

  // Output the elements of the array.
  void output();

  // We define this so that we can write "cout << a;" if a is an
  // object of this class.
  friend ostream &operator<<(ostream &out, My_array &data);
  //friend istream &operator>>(istream &in, My_array &data);
};

#endif

