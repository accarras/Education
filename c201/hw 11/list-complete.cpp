/*****************************************************************************
  Author:        Liqiang Zhang
  File name:     list.cpp
  Last updated:  Sept. 2015
  Description:   Implementation the List class               
******************************************************************************/
#include "stdafx.h"
#include <iostream>
using namespace std;

#include <stdlib.h>
#include "list.h"

// Default constructor.
List::List()
  : head(NULL), tail(NULL), size(0)
{
	//intend to be empty here
}

// Destructor.
List::~List()
{
  makeEmpty();
}

// Insert a new node with the specified content at the front of the
// list. It must make sure to update both the head and the tail of the
// list if need be.
void List::headInsert(int theData)
{
  Node* temp = new Node(theData, head);
  if (!temp)
  {
    cout << "Dynamic allocation failed.  Program being terminated."
	 << endl;
    exit(1);
  }
  size++;
  head = temp;
  if (size == 1) 
    tail = temp;
}

// Check if the list is empty.
bool List::isEmpty() const
{
  return (size == 0);
}

// Convert to boolean. It must return true if the list is not empty,
// and false if it is. 
List::operator bool() const
{
  return !isEmpty();
}

// Delete the entire list and reset all the data.
void List::makeEmpty()
{
  if (size) {
    Node *temp;
    while(head) {
      temp = head;
      head = head->getLink();
      delete temp;
    }   
    tail = NULL;
    size = 0;
  }
}

// Print the last node in the list: the tail.
void List::printLast() const
{
  if (tail) 
    cout << "The last node in the list contains " 
	 << tail->getData() << endl;
  else
    cout << "The list is empty, it has no last node" << endl;
}


// Difference operator - it must compare the content of the two lists
// and not just the pointers to them.
bool List::operator!=(const List &theList) const
{
  return !(*this == theList);
}

// Concatenates the second list to the first and empties the second
// list.
void List::concatenate(List &theList)
{
  if (this == &theList) {
    cout << "Attempt to concatenate a list to itself; operation aborted."
         << endl;
    return;
  }
  if (theList) {
    tail->setLink(theList.head);
    tail = theList.tail;
    size += theList.size;
    theList.head = NULL;
    theList.tail = NULL;
    theList.size = 0;
  }
}

// Copy constructor. The target object should contain a hard copy of
// the list contained in the data object.
// If the data object is empty, the target object should be initialized 
// with default values.
List::List(const List &theList)
: head(NULL), tail(NULL), size(0)
{
	if (theList) {

		head = new Node(*theList.head);	// use the copy constructor of Node class
		tail = head;					// both head and tail are pointing to the new node
		Node *temp = theList.head;		// temp will move through the theList

		temp = temp->getLink();			//move temp to the second node of theList

		while (temp) {
			tail->setLink(new Node(*temp));
			temp = temp->getLink();
			tail = tail->getLink();
		}
		size = theList.size;
	}
}

// Assignment operator. It must also make a hard copy of the
// list. Make sure that the tail of the target object is also set
// correctly.
// The function must verify that the data and the target object are not 
// the same object.
// The function should make sure that no problem arises if the data object 
// is empty. In this case the target object should also result empty after 
// the operation. 
List &List::operator=(const List &theList)
{
	if (this != &theList) {
		makeEmpty();
		if (theList) {

			head = new Node(*theList.head);	// use the copy constructor of Node class
			tail = head;					// both head and tail are pointing to the new node
			Node *temp = theList.head;		// temp will move through the theList

			temp = temp->getLink();			//move temp to the second node of theList

			while (temp) {
				tail->setLink(new Node(*temp));
				temp = temp->getLink();
				tail = tail->getLink();
			}
			size = theList.size;
		}
	}
	return *this;
}

// Check if the size stored in the list object is equal to the
// number of elements as counted in the list. This must call the
// function coundNodes and compare the result with the
// stored size.
bool List::checkSize() const
{
	return (size == countNodes());
}


/*******************************************************************/
// Functions to be written by the student.

// This counts the number of nodes in the list.
int List::countNodes() const
{
		Node *temp;
		temp = head;
		int count = 0;
		while (temp) {
			++count;
			temp = temp->getLink();
		}	
		return count;
		// This line is placed here temporarily to allow
					// compilation. Replace it with the code for this function.
	   // Code to be supplied by the student
}

// Print the list. We also want to know how many node are there in
// the list (you can call countNodes() function) and if the list is empty.
void List::printList() const
{
		if (size) {
			Node *temp;
			temp = head;
			cout << "This list has " << countNodes() << " nodes." << endl;
			cout << "Here are the nodes of the list:" << endl;
			while (temp) {
				cout << temp->getData() << " ";
				temp = temp->getLink();
			}
			cout << endl;
		}
		else {
			cout << "This list has " << size << " nodes." << endl;
		}
		// Code to be supplied by the student

  // Code to be supplied by the student
}
	  

// Comparison operator - it must compare the content of the two
// lists and not just the pointers to them.
// This function should compare each pair of nodes in the two lists 
// using the operator overloaded for class Node. 
// Before that it should compare the sizes 
// of the two lists and should make sure that the head pointers in the two 
// lists are not null before dereferencing them.
bool List::operator==(const List &theList) const
{
	Node *temp;
	Node *temp2;
	temp = head;
	temp2 = theList.head;

	if (this->countNodes() != theList.countNodes()) {
		return false;
	}
	while (temp) {
		if (temp->getData() != (temp2->getData())) {
			return false;
		}
		else {
			temp = temp->getLink();
			temp2 = temp2->getLink();
		}
	}
  return true; // This line is placed here temporarily to allow
               // compilation. Replace it with the code for this function.
  // Code to be supplied by the student.
}

// Insert a new node with the specified content at the end of the
// list. 
void List::tailInsert(int theData)
{
	Node *temp = new Node;
	Node *temp2 = head;
	temp->setData(theData);
	temp->setLink(NULL);
	if (!temp2) {
		tail = temp;
	}
	else {
		tail->setLink(temp);
		tail = temp;
	}
  // Code to be supplied by the student.
}

// Checks if the list is in ascending order.
bool List::isOrdered() const
{
	if (size) {
		Node *temp;
		temp = head;
		while (temp->getLink() != NULL){
			if (temp->getData() > (temp->getLink())->getData()) {
				return false;
			}
			else {
				temp = temp->getLink();
			}
		}
	}
		return true;
  // compilation. Replace it with the code for this function.
  // Code to be supplied by the student.
}

// Find the given number in the list. You will need to use the
// function getLink() from the Node class.
bool List::search(int theData) const
{
	if (size) {
		Node *temp;
		temp = head;
		while (temp) {
			if (temp->getData() == theData) {
				return true;
			}
			else {
				temp = temp->getLink();
			}
		}
	}
	return false;
  // Code to be supplied by the student.
}

