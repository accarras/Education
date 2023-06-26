/*****************************************************************************
Author:        Liqiang Zhang
File name:     listDemo.cpp
Last updated:  Sept. 2015
Description:   Demostrate the List class
******************************************************************************/
#include "stdafx.h"
#include <iostream>
#include <string>
#include <cctype>
using namespace std;

#include "list.h"


int main()
{
	List list1, list2;
	list1.headInsert(12);
	list1.headInsert(8);
	list1.headInsert(7);

	cout << "Print list1: " << endl;
	list1.printList();
	if (list1.isOrdered())
		cout << "list1 is ordered." << endl;
	else
		cout << "list1 is not ordered." << endl;

	list1.headInsert(19);
	list1.tailInsert(27);
	list1.tailInsert(21);

	cout << endl << "Print list1: " << endl;
	list1.printList();
	if (list1.isOrdered())
		cout << "list1 is ordered." << endl;
	else
		cout << "list1 is not ordered." << endl;

	list2 = list1;
	cout << endl << "Now we assign list1 to list2." << endl;
	cout << "Print list2: " << endl;
	list2.printList();

	List list3;
	list3.headInsert(15);
	cout << endl << "Print list3: " << endl;
	list3.printList();

	cout << endl << "Now we concatenate list2 to list3." << endl;
	list3.concatenate(list2);
	cout << "Print list3: " << endl;
	list3.printList();
	cout << "Print list2: " << endl;
	list2.printList();

	cout << endl << "Now do a searching on list3." << endl;
	int num;
	char again;
	do {
		cout << "Please input a number to search: ";
		cin >> num;
		if (list3.search(num))
			cout << num << " found in list3!" << endl;
		else
			cout << num << " not found in list3" << endl;
		cout << "Do you want to do another search? (y/n): ";
		cin >> again;
	} while (toupper(again) == 'Y');

	//cout << endl << "Now we sort list3" << endl;
	//list3.selectionSort();
	//list3.printList();
	//if (list3.isOrdered())
	//	cout << "list3 is ordered." << endl;
	//else
	//	cout << "list3 is not ordered." << endl;

}