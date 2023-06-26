/********************************************************************************
Author:			Liqiang Zhang
File name:		linkedList-CStyle.cpp
Last updated:	Sept. 2015
Description:	Define and demo a C-style linked list
Not 100% C though, as we do use string, new, and cout
*********************************************************************************/

#include "stdafx.h"
#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

// node for holding student's name and GPA
// next is a pointer pointing to next node
struct node
{
	string name;
	float gpa;
	node *next;
};

// return the number of nodes contained 
// in the list pointed to by p
int numberOfNodes(node* p);

// return the pointer to the last node 
// contained in the list pointed to by p
node * ptrToLastNode(node* p);

// insert a new node at the front of the list pointed to by p
bool insertAtFront(node* &p, string name, float grade);

// remove a node from the front of the list pointed to by p
bool removeFrontNode(node * &p);

// remove a node from the end of the list pointed to by p
bool removeEndNode(node * &p);

// insert a node at the end of the list pointed to by p
bool insertAtEnd(node* &p, string name, float grade);

// make a copy of the list pointed to by p
// and return the pointer to the copy
node* copyOfList(node* p);

// print the list pointed to by p
void printList(node* p);

// search the list by name and return the pointer to the node if found,
// otherwise, return NULL
node* searchByName(node* p, string name);

// delete the whole list of nodes
void deleteList(node* p);


int main()
{
	node* list1 = NULL;
	insertAtFront(list1, "Frank", 3.1);
	insertAtFront(list1, "Tom", 2.6);
	insertAtEnd(list1, "John", 2.8);
	insertAtEnd(list1, "Steve", 3.4);
	insertAtEnd(list1, "Joe", 3.6);
	cout << "list1 has " << numberOfNodes(list1) << " nodes" << endl;
	cout << "Here is the content of list1:" << endl;
	printList(list1);

	cout << "Now we copy list1 to list2." << endl;
	node* list2 = copyOfList(list1);
	cout << "list2 has " << numberOfNodes(list2) << " nodes" << endl;
	cout << "Here is the content of list2:" << endl;
	printList(list2);

	cout << "Now we remove the node at the front of list2." << endl;
	removeFrontNode(list2);
	cout << "Here is the content of list2:" << endl;
	printList(list2);

	cout << "Now we remove the node at the end of list2." << endl;
	removeEndNode(list2);
	cout << "Here is the content of list2:" << endl;
	printList(list2);

	string aName = "Steve";
	cout << "Now we search list2 by name for " << aName;
	node* searchResult = NULL;
	searchResult = searchByName(list2, aName);
	if (!searchResult)
		cout << " : not found!" << endl;
	else
		cout << " : found! " << aName << "'s GPA is " << searchResult->gpa << endl;

	aName = "Kim";
	cout << "Now we search list2 by name for " << aName;
	searchResult = searchByName(list2, aName);
	if (!searchResult)
		cout << " : not found!" << endl;
	else
		cout << " : found! " << aName << "'s GPA is " << searchResult->gpa << endl;

	cout << endl << "deleting list1......" << endl;
	deleteList(list1);
	cout << endl << "deleting list2......" << endl;
	deleteList(list2);

}

// return the number of nodes contained 
// in the list pointed to by p
int numberOfNodes(node* p)
{
	int count = 0;
	while (p != NULL) {
		++count;
		p = p->next;
	}
	return count;
}

// return the pointer to the last node 
// contained in the list pointed to by p
node * ptrToLastNode(node* p)
{
	if (!p)
		return NULL;
	else {
		while (p->next)
			p = p->next;
		return p;
	}
}

// insert a new node at the front of the list pointed to by p
bool insertAtFront(node* &p, string name, float grade)
{
	node* temp = new node; // Declare a pointer variable and make
						   // it point to a newly allocated node.
	if (!temp)                // If allocation fails, leave function.
		return false;
	else
	{
		temp->name = name;		// The algorithm expressed in these
		temp->gpa = grade;      // four lines is a very important
		temp->next = p;         // one. Note that no test needs to 
		p = temp;               // be made to see whether p is NULL.
		return true;
	}
}

// remove a node from the front of the list pointed to by p
bool removeFrontNode(node * &p)
{
	if (!p)
		return false;
	else {
		node* temp = p;
		p = p->next;
		delete temp;
		return true;
	}
}

// insert a node at the end of the list pointed to by p
bool insertAtEnd(node* &p, string name, float grade)
{
	node* tail = ptrToLastNode(p);
	node* temp = new node;
	if (!temp)
		return false;
	else {
		temp->name = name;
		temp->gpa = grade;
		temp->next = NULL;
		if (!p)
			p = tail = temp;
		else {
			tail->next = temp;
			tail = temp;
		}
		return true;
	}
}

// make a copy of the list pointed to by p
// and return the pointer to the copy
node* copyOfList(node* p)
{
	if (!p)
		return NULL;
	else {
		node* head = NULL, *tail = NULL, *temp;
		while (p) {
			temp = new node;
			temp->name = p->name;
			temp->gpa = p->gpa;
			temp->next = NULL;
			if (!head)
				head = tail = temp;
			else {
				tail->next = temp;
				tail = temp;
			}
			p = p->next;
		}
		return head;
	}
}

// print the list pointed to by p
void printList(node* p)
{
	if (!p)
		return;

	cout << setw(15) << "Name" << setw(8) << "GPA" << endl;
	cout << setw(15) << "============" << setw(8) << "======" << endl;
	while (p) {
		cout << setw(15) << p->name << setw(8) << p->gpa << endl;
		p = p->next;
	}
	cout << endl;
}

// delete the whole list of nodes
void deleteList(node* p)
{
	if (!p)
		return;

	node* temp;
	temp = p;

	while (temp != NULL)
	{
		p = p->next;
		cout << "deleting " << temp->name << endl;
		delete temp;
		temp = p;
	}

	return;
}

// remove a node from the end of the list pointed to by p
bool removeEndNode(node * &p)
{
	// to be completed by students
	// return false for now in order to compile
	if (!p) {
		cout << "list empty" << endl;
		return false;
	}
	else if (p->next == NULL) {
		delete p;
		p = NULL;
	}
	else {
		node* temp = p;
		while (temp->next->next != NULL) {
			temp = temp->next;
		}
		node*tptr = temp->next;
		temp->next = NULL;
		delete tptr;
		return true;
	}
}

// search the list by name and return the pointer to the node if found,
// otherwise, return NULL
node* searchByName(node* p, string name)
{
	
	node* temp = p;
	while (temp != NULL) {
		if (temp->name == name) {
			return temp;
		}
		temp = temp->next;	
	}
	return NULL;
}


