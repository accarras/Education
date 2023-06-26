/******************************************************************************
 Author:
 Class:
 File name:	pq.cpp
 Description:
 This file implements the pqueue.h interface using a dynamic array as
 the underlying representation.  Most of the code is already in place.
 The only thing that is missing is the implementation of the actual
 commands.

 ******************************************************************************/
#include "stdafx.h"
#include <iostream>
#include <string>
#include "pq.h"
using namespace std;

//size of priority queue
PriorityQueue::PriorityQueue() {
	array = new job[INITIAL_CAPACITY];
	count = 0;
	capacity = INITIAL_CAPACITY;
}

PriorityQueue::~PriorityQueue() {
	clear();
}

int PriorityQueue::size() {
	return count;
}

bool PriorityQueue::isEmpty() {
	return (count < 1);
}

void PriorityQueue::clear() {
	if (count != 0) {
		delete[] array;
		array = new job[INITIAL_CAPACITY];
		count = 0;
		capacity = INITIAL_CAPACITY;
	}
}

void PriorityQueue::enqueue(string value, int priorityNum) {
		
	count++;
	if (count >= capacity) {
		expandCapacity();
		cout << "Capacity has been expanded" << endl;
	}
	array[count].setPriority(priorityNum);
	array[count].setTaskName(value);
	upheap(count);
}

string PriorityQueue::dequeue() {
	if (isEmpty()) {
		return "";
	}
	string blah = array[1].getTaskName();
	array[1] = array[count];
	count--;
	downheap(1);
	return blah;

}

string PriorityQueue::peek() {
	if (isEmpty()) {
		cout << "Queue is empty" << endl;
		return "";
	}
	return array[1].getTaskName();
}

int PriorityQueue::peekPriority() {
	if (isEmpty()) {
		cout << "Queue is empty" << endl;
		return 0;
	}
	return array[1].getPriority();
}

/*
 * Implementation notes: copy constructor and assignment operator
 * --------------------------------------------------------------
 *
 */

PriorityQueue::PriorityQueue(const PriorityQueue & src) {
	deepCopy(src);
}

PriorityQueue & PriorityQueue::operator=(const PriorityQueue & src) {
	if (this != &src) {
		if (array != NULL)
			delete[] array;
		deepCopy(src);
	}
	return *this;
}

void PriorityQueue::upheap(int k) {
	job v;
	v = array[k];
	while (array[k / 2].getPriority() >= v.getPriority()){
		array[k] = array[k / 2];
		k = k / 2;
		array[k] = v;
	}
}

void PriorityQueue::downheap(int k) {
	job v;
	int j;
	v = array[k];
	while (k / 2 >= k) {
		j = k + k;
		if (j < k && array[j].getPriority() < array[j + 1].getPriority())
			j++;
		if (v.getPriority() <= array[j].getPriority())
			break;
		array[k] = array[j];
		k = j;
	}
	array[k]=v;
}

void PriorityQueue::expandCapacity() {
	job *oldArray = array;
	capacity *= 2;
	array = new job[capacity];
	for (int i = 0; i < count; i++) {
		array[i] = oldArray[i];
	}
	delete[] oldArray;
}

void PriorityQueue::deepCopy(const PriorityQueue & src) {
	count = capacity = src.count;
	if (capacity < INITIAL_CAPACITY)
		capacity = INITIAL_CAPACITY;
	array = new job[capacity];
	for (int i = 0; i < count; i++) {
		array[i] = src.array[i];
	}
}