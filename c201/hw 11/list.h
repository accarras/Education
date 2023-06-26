/*****************************************************************************
  Author:        Liqiang Zhang
  File name:     list.h
  Last updated:  Sept. 2015
  Description:   Definition of the List class               
******************************************************************************/

#ifndef LIST_H
#define LIST_H

#include "node.h"

class List {
 public:
  	// Default constructor.
  	List();
  	// Destructor.
  	~List();

  	// Insert a new node with the specified content at the front of the
  	// list. It must make sure to update both the head and the tail of
  	// the list if need be.
  	void headInsert(int theData);

  	// Check if the list is empty.
  	bool isEmpty() const;

  	// Convert to boolean. It must return true if the list is not empty,
  	// and false if it is. 
  	operator bool() const;

  	// Delete the entire list and reset all the data.
  	void makeEmpty();

  	// Print the last node in the list: the tail.
  	void printLast() const;

  	// Checks if the list is in ascending order.
  	bool isOrdered() const;

  	// Concatenates the second list to the first and empties the second
  	// list.
  	void concatenate(List &theList);

  	// Difference operator - it must compare the content of the two lists
  	// and not just the pointers to them.
  	bool operator!=(const List &theList) const;

	/*******************************************************************/
	// Functions to be written by the student.

  	// This counts the nodes in the list.
  	int countNodes() const;

	// Print the list. We also want to know how many node are there in
	// the list (you can call countNodes() function) and if the list is empty.
  	void printList() const;

  	// Copy constructor. The target object should contain a hard copy of
  	// the list contained in the theList object.
  	List(const List &theList);

  	// Assignment operator. It must also make a hard copy of the
  	// list. Make sure that the tail of the target object is also set
  	// correctly.
  	List &operator=(const List &theList);

  	// Comparison operator - it must compare the content of the two
  	// lists and not just the pointers to them. If the lists have
  	// different sizes, then it should return false. Otherwise if the
  	// all the nodes in the corresponding positions in the two lists
  	// contain the same values then it should return true.
  	bool operator==(const List &theList) const;

  	// Insert a new node with the specified content at the back of the
  	// list. The code must be supplied by the student.
  	void tailInsert(int theData);

  	// Find the given data item in the list. 
  	bool search(int target) const;

  	// Check if the size stored in the list object is equal to the
  	// number of elements as counted in the list. This must call the
  	// function coundNodes and compare the result with the
  	// stored size.
  	bool checkSize() const;

 private:
  	Node *head, *tail;
  	int size;
};

#endif
