/***********************************************************************
 Author:
 Class:
 File Name:  	pq.h
 Description:
 To be done:  	Complete this header!
 ***********************************************************************/

#ifndef PQ_H
#define PQ_H

#include "job.h"
#include <string>
using namespace std;

class PriorityQueue {

public:

	/*
	 * Constructor: PriorityQueue
	 * Usage: PriorityQueue pq;
	 * ---------------------------
	 * Initializes a new priority queue, which is initially empty.
	 */

	PriorityQueue();

	/*
	 * Destructor: ~PriorityQueue
	 * --------------------------
	 * Frees any array storage associated with this priority queue.
	 */

	~PriorityQueue();

	/*
	 * Method: size
	 * Usage: int n = pq.size();
	 * -------------------------
	 * Returns the number of values in the priority queue.
	 */

	int size();

	/*
	 * Method: isEmpty
	 * Usage: if (pq.isEmpty()) ...
	 * ----------------------------
	 * Returns true if the priority queue contains zero elements.
	 */

	bool isEmpty();

	/*
	 * Method: clear
	 * Usage: pq.clear();
	 * ------------------
	 * Removes all elements from the priority queue.
	 */

	void clear();

	/*
	 * Method: enqueue
	 * Usage: pq.enqueue(value, priority);
	 * -----------------------------------
	 * Adds value to the queue with the specified priority.  Lower priority
	 * numbers correspond to higher priorities, which means that all
	 * priority 1 elements are dequeued before any priority 2 elements.
	 */

	void enqueue(string value, int priority);

	/*
	 * Method: dequeue
	 * Usage: string first = pq.dequeue();
	 * ------------------------------
	 * Removes and returns the highest priority value.  If multiple
	 * entries in the queue have the same priority, those values are
	 * dequeued in the same order in which they were enqueued.
	 */

	string dequeue();

	/*
	 * Method: peek
	 * Usage: string first = pq.peek();
	 * ---------------------------
	 * Returns the value of highest priority in the queue, without
	 * removing it.
	 */

	string peek();

	/*
	 * Method: peekPriority
	 * Usage: int priority = pq.peekPriority();
	 * -------------------------------------------
	 * Returns the priority of the first element in the queue, without
	 * removing it.
	 */

	int peekPriority();

	/*
	 * Method: copy constructor
	 * ------------------------
	 * The copy constructor makes it possible to pass a PriorityQueue by value.
	 * Its signature follows a standard pattern for all copy constructors.
	 */

	PriorityQueue(const PriorityQueue & src);

	/*
	 * Method: assignment operator
	 * ---------------------------
	 * The assignment operator makes it possible to assign a PriorityQueue.
	 * Its signature follows a standard pattern for all assignment operators.
	 */

	PriorityQueue & operator=(const PriorityQueue & src);

	void upheap(int i);

	void downheap(int k);
	

	/* Private section */

private:

	static const int INITIAL_CAPACITY = 10;	//constant denoting ???

	/* Instance variables */

	job *array;
	int count;
	int capacity;

	/* Private methods */
	void expandCapacity();
	void deepCopy(const PriorityQueue & src);

};

#endif


