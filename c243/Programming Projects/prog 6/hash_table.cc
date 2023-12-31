/*********************************************************************
 Author:
 Class:
 File name:     hash_table.cc
 Last updated:
 Description:
 **********************************************************************/

#include <iostream>
#include <iomanip>
#include <string>
#include <cstring>
#include <stdlib.h>
using namespace std;

#include "hash_table.h"

// Constructor with given initial capacity.
HTable::HTable(int capacity) :
		storage(capacity) {
	size = capacity;
	nr_objects = 0;
}

// Destructor.
HTable::~HTable() {
	make_empty();
	storage.clear();
}

// Delete all the objects without deleting the storage.
void HTable::make_empty() {
	for (int i = 0; i < size; i++)
		storage[i].clear();
	nr_objects = 0;
}

// Checks if the access to a given position is safe in the
// storage. This means if the position is smaller than the size and
// non negative.
bool HTable::is_safe(int position) {
	return (position >= 0 && position < size);
}

// Prints all the objects in the table.
void HTable::print() {
	List_iter iter;
	int i;

	cout << "The table has " << nr_objects << " objects." << endl;
	if (nr_objects) {
		cout << "Here are the objects:" << endl;
		for (i = 0; i < size; i++)
			if (storage[i].size())
				for (iter = storage[i].begin(); iter != storage[i].end();
						++iter)
					cout << *iter << endl;
	}
}

// Increases the size of the array up to the given position.
bool HTable::increase(int position) {
	if (position >= 0)
		if (position > size) {
			List_wordc empty_list;
			for (int i = size; i <= position; i++)
				storage.push_back(empty_list);
			size = position + 1;
			return true;
		}
	return false;
}

// This function inserts an object in the table when we already know
// what position we want it to be inserted at. It is declared as
// private because the user should not be able to call it. It is
// designed to be called internally by the public insertion function
// after getting the position from the hashing table.
bool HTable::insert(Word_counter &wordc, int position) {
	cout << "big_insert was called" << endl;
	if (position >= 0) {
		if (!is_safe(position))
			increase(position);
		storage[position].push_back(wordc);
		nr_objects++;
		return true;
	} else
		return false;
}

// This function removes the word counter object from the list at the given
// position. It is also intended to be called internally because
// public remove function should determine the position based on the
// hash function before it calls this one. They key is used to locate
// the object which is copied to the object wordc before it is erased.
bool HTable::remove(Word_counter &wordc, int position) {
	cout << "big_remove was called" << endl;
	if (!is_safe(position))
		return false;
	else {
		List_iter iter = storage[position].begin();
		while (iter != storage[position].end() && key(*iter) != key(wordc))
			++iter;
		if (iter == storage[position].end())
			return false;
		else {
			wordc = *iter;
			storage[position].erase(iter);
			nr_objects--;
			return true;
		}
	}
}

// Private functions to be implemented by the student.

// This function should find if the string s has been used as a key to
// insert an object in the list at the given position. If yes, then it
// should copy that object into the object wordc and return true. If
// not, it should return false.
bool HTable::find(Word_counter &wordc, string s, int position) {
	cout << "find was called" << endl;
	if (!is_safe(position))
		return false;
	else {
		List_iter iter = storage[position].begin();
		while (iter != storage[position].end() && key(*iter) != key(wordc))
			++iter;
		if (iter == storage[position].end())
			return false;
		else {
			wordc = *iter;
			return true;
		}
	}
}

// The most important function of the class. This function takes a
// string (a key) and determines the index in the storage array where
// an object with that key should go.
int HTable::hashing(string s) {
	int hash_val = 0;
	for (int k = 0; k < s.length(); ++k)
		hash_val += int(s[k]);
	return hash_val % size;
}

// Public functions to be implemented by the student.

// This function should first get the hash index for the key of that
// object, then find out if the key has been inserted in the list at
// that index, and if not, then it should insert the object in that
// list and return true. If the key has already been used in the
// table, it should return false.
bool HTable::insert(Word_counter &wordc) {
	string s = key(wordc);
	int index = hashing(s);
	if (!find(wordc, s, index)) {
		insert(wordc, index);
		return true;
	} else {
		return false;
	}
	//return insert(wordc, index);
}

// This function should first get the hash index for the string, then
// find out if the string has been used as a key to insert an object in
// the list at that index. If yes, it should copy that object into the
// object wordc and return true, and if not, it should just return false.
bool HTable::access(Word_counter &wordc, string s) {
	int index = hashing(s);
	if (find(wordc, s, index)) {
	 wordc.set_word(s);
	 return true;
	 }
	 return false;
	//return find(wordc, s, index);
}

// This function should first get the hash index for the string, then
// find out if the string has been used as a key to insert an object in
// the list at that index. If yes, it should copy that object into the
// object wordc, remove the object from that list, and return true. If
// not, it should just return false.
bool HTable::remove(Word_counter &wordc, string s) {
	int index = hashing(s);
	if (find(wordc, s, index)) {
		//wordc.set_word(s);
		remove(wordc, index);
		return true;
	}
	return false;
	//*/
	//return remove(wordc, index);
}

// This function should print a statistic of usage of each index in
// the table (number of objects stored at each index).
void HTable::statistic() {
	/*Word_counter wordc;
	for (int i =0; i < size; i++){
		wordc=storage[i];
		cout << wordc << endl;*/

	}
