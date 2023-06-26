/********************************************************************
  Author:        Tony Carrasco, file provided by Dr. Surma
  Class:         C243 Spring 2016
  File name:     my_array.cc
  Last updated:  Feb 1, 2016
  Description:   This file is an implementation of the my_array.h file
                 For a full description of the functions, see the 
                 my_array.h file.
*********************************************************************/
#include "my_array.h"     // links this file to the my_array.h file
#include <iostream>
using namespace std;
#include <stdlib.h>       //   contains the abs() needed
                          //  for the hamming distance function 


//  int size;             // just a reminder about the internal
//  int *array;           // construction of a My_array object.


// Constructor with given array size, can be used as a default constructor.
My_array::My_array(int the_size)
{
  array = NULL;
  size = 0;
  resize(the_size);
}

// Destructor. If the array is not empty, it must be deallocated.
My_array::~My_array()
{
  empty();
}

// Copy constructor  -- not used for this assignment but provided for reference.
My_array::My_array(My_array &data)
  : size(data.size)
{
  array = new int[size];
  for (int i=0; i<size; i++)
    array[i] = data.array[i];
}

// Overloaded assignment operator.  This method will set the target object equal to
// the passed in My_array object provided that they are different.  If they are the
// same, an error message will be output to the terminal screen.

My_array &My_array::operator =(My_array &data)
{
  if (this != &data) {
    resize(data.size);
    for (int i=0; i<size; i++)
      array[i] = data.array[i];
  }
  else 
    cout << "Attempt to copy an object on itself. " 
	 << "Operation ignored." << endl;
  return *this;
}

// Overloaded comparison operator. This checks to see if two objects 
// of the my_array type are equal. Returns true if equal, and false
// if not equal

bool My_array::operator ==(My_array &data){
  if (size != data.size){
    return false;
  }

  for (int i = 0; i < size; i++){
    if (array[i] != data.array[i]){
      return false; 
    }
  }
  return true;
}

// Overloaded comparison operator. This checks to see if two objects
// of the my_array type are NOT equal. It just takes the output from the 
// overloaded == operator and reverses it

bool My_array::operator !=(My_array &data){
  if (array == data.array){
    return false;
  }
  else {
    return true;
  }
}

void My_array::empty()
  /*  This function will empty the target object setting the size instance 
variable to zero
      and deallocating the smace for the internal array. */
{
  if (size != 0 && array != NULL) {
    size = 0;
    delete [] array;
  }
}

// Resize the array.  Change the size of the internal array to the dimension of the parameter.
void My_array::resize(int the_size)
{
  if (size >= 0) {
    empty();
    if (the_size != 0) {
      size = the_size;
      array = new int[size];
    }
  }
  else
    cout << "Resize attepmted with a negative size. "
	 << "Operation ignored." << endl;
}

// Access an element of the array. If the index (subscript) is out
// of the array, it prints an error message and exits the program.
int &My_array::operator[](int index)
{
  if (index < size) 
    return array[index];
  else {
    cerr << "Illegal access to an element of the array." << endl
	 << "The size of the array was " << size 
	 << " and the index was " << index << endl;
    exit(1);
  }
}

// Accessor - returns the size of the array.
int My_array::get_size()
{
  return size;
}

// This function calculates the hamming distance 
// The hamming distance is the number of positions
// which are different between the two arrays
int My_array::hamming(My_array &data){

  int diff = 0;
  int ham = 0;
  int small = 0;
  
  diff = abs(size - data.size);
                          // calculates the size difference between the two arrays

  if (size < data.size){
    small = size;
  }
  else {
    small = data.size;    // this code sets the size
                          // which is uses to limit the number
                          // of loops to the size of the smaller array
  }

  for (int i = 0; i < small; i++){
    if (array[i] != data.array[i]){
       ham++;
    } 
  }                       // checks to see which numbers are different
                          // between the two arays up to the size
                          // of the smaller array 
  
  ham +=  diff;           // adds the difference in size to the 
                          // amount of different numbers 

  return ham;
}

// randomizes the contents of an array
// numbers are between 1 and 100
void My_array::randomize(int limit){
  for (int i = 0; i < size; i++){
    array[i] = ((rand() % limit) + 1); 
  }
}

// Input elements from the user  to the array
void My_array::input()
{
  for (int i = 0; i < size; i++){
    cout << "Enter integer for position " << i+1 << " in the array: ";
    cin >> array[i];
  }
}

// Output the elements of the array to the terminal screen.
void My_array::output()
{
  cout << "The array of size " << size 
       << " contains the elements:" << endl;
  for (int i=0; i<size; i++)
    cout << array[i] << ' ';
  cout << endl;
}

// We define this so that we can write "cout << a;" if a is an object
// of this class.  Effectively we are overloading the << operator.
ostream &operator<<(ostream &out, My_array &data)
{
  out << "The array of size " << data.size 
      << " contains the elements:" << endl;
  for (int i=0; i<data.size; i++)
    out << data.array[i] << ' ';
  out << endl;
  return out;
}

