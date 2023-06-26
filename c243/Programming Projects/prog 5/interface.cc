#include "stdafx.h"
/*********************************************************************
 Author:
 Class:
 File name:     interface.cc
 Last updated:
 Description:

 **********************************************************************/

#include <iostream>
#include "interface.h"
using namespace std;

const int NUMBER_OF_MENU_ITEMS = 9;

/*******************  R U N   T H E   A C T I O N S   L O O P  *************

 This function creates two empty linked lists that can be manipulated
 by the interactive user of this program.  It then goes into a loop
 that repeatedly (1) prints a menu of list actions for the user to
 choose from, (2) reads the number of the menu item (action) selected
 by the user, (3) attempts to carry out the action selected.
 Documented by w. knight.  coded by w. knight, modified by
 Dr. Vrajitoru and Dr. Surma */


void run_the_actions_loop() {
	PriorityQueue p;
	int action_number;
	bool time_to_quit;   // Will be set to true when the user wants to quit.

	cout << "Press the Enter key to see a menu of operations you can"
		 << " perform: ";

	while (cin.get() != '\n');   // Clear the user's line.
	cout << endl;                 // Echo their end of line character.

	do {
		print_menu();

		action_number = action_from_user();

		time_to_quit = carry_out_the_action(action_number, p);

	} while (!time_to_quit);
}

/**************************  P R I N T   M E N U  **************************

 This function prints a menu of possible actions that the interactive
 user can choose to perform on the linked list in the program.
 Documented by W. Knight.  Coded by W. Knight, modified by
 Dr. Vrajitoru.  */

void print_menu() {
	cout << " 0) EXIT FROM THE PROGRAM.\n";
	cout << " 1) Enqueue a task.\n";
	cout << " 2) Dequeue a task.\n";
	cout << " 3) Peek at the most urgent task.\n";
	cout << " 4) Peek at the priority of the most urgent task.\n";
	cout << " 5) Clear the queue of all elements.\n";
	cout << " 6) Return the number of the tasks in the queue.\n";
	cout << " 7) Determine if the queue is empty.\n";
	cout << " 8) List the tasks in the priority queue.\n";
}

/*********************  A C T I O N   F R O M   U S E R  ******************/
// Reads the user's choice and asks for it again if the choice is not
// a valid one.
// Coded by W. Knight.
int action_from_user() {
	int choice;
	cout << "\nType a choice from the menu (it will be echoed): ";
	cin >> choice;
	cout << choice << endl;  // Echo the user's choice.

	while (!cin.good() || choice < 0 || choice >= NUMBER_OF_MENU_ITEMS) {
		cout << "\nYou must enter a number from the menu.\n"
				<< "Please try again.\n\n";

		cin.clear();                 // Reset the "good" bit.
		while (cin.get() != '\n')
			;  // Clear line entered by the user.

		cout << "\nYour choice from the menu: ";
		cin >> choice;
		cout << choice << endl;  // Echo the user's choice.
	}

	while (cin.get() != '\n'); // Clear rest of line after the user's
	// choice
	return choice;
}

/****************  C A R R Y   O U T   T H E   A C T I O N  ****************

 This function attempts to carry out an action on a linked list pointed
 to by the parameter "front".  The action is indicated by the parameter
 "choice".  The function consists almost entirely of a lengthy switch
 statement that calls the appropriate list function and then reports
 the results of the call.  Documented by W. Knight.  Coded by
 W. Knight, modified by Dr. Vrajitoru.  */

bool carry_out_the_action(int choice, PriorityQueue &p) {
	cout << "\n\n\n\n" << endl;

	switch (choice) {
	case 0:
		cout << endl << endl;
		return true;  // It's time to quit the program.
	case 1:
		enqueue_action(p);
		break;
	case 2:
		dequeue_action(p);
		break;
	case 3:
		peek_action(p);
		break;
	case 4:
		peek_priority(p);
		break;
	case 5:
		clear_action(p);
		break;
	case 6:
		task_number(p);
		break;
	case 7:
		empty_action(p);
		break;
	case 8:
		list_action(p);
		break;

	} // end of switch statement

	cout << "\nPress the Enter key to continue. ";
	while (cin.get() != '\n'); // Clear the line
	cout << endl;                 // Echo their end of line character.

	return false;  // Do not quit the program.
}

void enqueue_action(PriorityQueue &p) {
	string task;
	int priority;
	cout << "Enter task name: ";
	getline(cin, task);
	cout << endl;
	cout << "Enter priority: ";
	cin >> priority;
	p.enqueue(task, priority);
	cout << endl;

}

void dequeue_action(PriorityQueue &p) {
	cout << "Name of dequeued task is: ";
	cout << p.dequeue();
	cout << endl;
}

void peek_action(PriorityQueue &p) {
	cout << "Name of next task is: " << p.peek() << endl;
}

void peek_priority(PriorityQueue &p) {
	cout << "Priority of next task is: " << p.peekPriority() << endl;
}

void clear_action(PriorityQueue &p) {
	p.clear();
	cout << "Priority Queue is cleared" << endl;
}

void task_number(PriorityQueue &p) {
	cout << "The number of tasks in the Priority Queue is: " << p.size()
			<< endl;
}

void empty_action(PriorityQueue &p) {
	if (p.isEmpty()) {
		cout << "The priority queue is empty." << endl;
	} else {
		cout << "The priority queue is not empty." << endl;
	}
}

void list_action(PriorityQueue &p){
	PriorityQueue pq;
	pq = p;
	int n = p.size();
	cout << "List of queued tasks: " << endl;
	for (int i = 1; i < n+1; i++) {
		cout << pq.dequeue() << endl;
	}
}

