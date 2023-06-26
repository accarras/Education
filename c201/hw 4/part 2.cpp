#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

class Pizza {

public:
//my pizza functions are public
	void input();
	void computePrice();
	void outputDescription();

//my pizza data is private	
private :
	int size;
	int crust;
	int cheese;
	int pepperoni;
	int cost = 0;
};

int main() {
//creates pizza object
	Pizza pizza1;

	pizza1.input();
	pizza1.computePrice();
	pizza1.outputDescription();

}

void Pizza::input()
{
//takes in user input
	cout << "Choose size of pizza: \n"
		<< "Enter 1 for small \n"
		<< "Enter 2 for medium \n"
		<< "Enter 3 for large" 
		<< endl;
	cin >> size;

	cout << "Choose type of crust: \n"
		<< "Enter 1 for deep dish \n"
		<< "Enter 2 for hand tossed \n"
		<< "Enter 3 for pan pizza" 
		<< endl;
	cin >> crust;

	cout << "Enter number of cheese toppings you would like: ";
	cin >> cheese;

	cout << "Enter number of pepperoni toppings you would like: ";
	cin >> pepperoni;
}

void Pizza::computePrice()
{
//computes price based on ingredients
	if (size == 1) {
		cost = cost + 10;
	}
	else if(size == 2) {
		cost = cost + 14;
	}
	else if (size == 3) {
		cost = cost + 17;
	}

	cost = cost + (cheese * 2);
	cost = cost + (pepperoni * 2);
}

void Pizza::outputDescription() {

//outputes a description of the pizza based on how it was mad, what size it is, and how many toppings it has.

	cout << "You selected a ";
	if (size == 1) { cout << "small"; }
	else if (size == 2) { cout << "medium"; }
	else if (size == 3) { cout << "large"; }
	
	cout << ", ";
	
	if (crust == 1) { cout << "deep dish"; }
	else if (crust == 2) { cout << "hand tossed"; }
	else if (crust == 3) { cout << "pan"; }

	cout << " pizza, with " 
		<< cheese 
		<< " cheese, and " 
		<< pepperoni 
		<< " pepperoni, for a total cost of $"
		<< cost
		<<"." << endl;

}