/*********************************************************************
 Author:
 Class:
 File name:     interface.h
 Last updated:
 Description:

 **********************************************************************/

#ifndef INTERFACE_H
#define INTERFACE_H
#include "pq.h"
// The main loop of the program: print the menu, input the user's
// choice, perform some action based on it.
void run_the_actions_loop();

// Prints all the options in the program's menu.
void print_menu();

// Reads the user's choice and asks for it again if the choice is not
// a valid one.
int action_from_user();

// Execute the action determined by the user's entry.
bool carry_out_the_action(int choice, PriorityQueue &p);

void enqueue_action(PriorityQueue &p);

void dequeue_action(PriorityQueue &p);

void peek_action(PriorityQueue &p);

void peek_priority(PriorityQueue &p);

void clear_action(PriorityQueue &p);

void task_number(PriorityQueue &p);

void empty_action(PriorityQueue &p);

void list_action(PriorityQueue &p);

#endif

