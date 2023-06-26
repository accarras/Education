#include "stdafx.h"
#include <iostream>
#include <istream> 
using namespace std;

template<class T>
T selectSort(T a[])
{
	T sel;

	for (int i = 0; i < 10; i++)
		cout << a[i] << " ";

	cout << endl;

	for (int i = 0; i < 10 - 1; i++)

		for (int j = i + 1; j < 10; j++)

			if (a[i] > a[j])
			{
				sel = a[i];
				a[i] = a[j];
				a[j] = sel;

				for (int i = 0; i < 10; i++)
					cout << a[i] << " ";
					cout << endl;
			}

	for (int i = 0; i < 10; i++)
		cout << a[i] << " ";

	cout << endl;

	return 0;
}

template<class T>
T bubbleSort(T a[])
{
	T bub;

	for (int i = 0; i < 10; i++)
		cout << a[i] << " ";
		cout << endl;

	for (int i = 0; i < 10; i++)

		for (int j = i + 1; j < 10; j++)

			if (a[j] < a[i])
			{

				bub = a[i];
				a[i] = a[j];
				a[j] = bub;

				for (int i = 0; i < 10; i++)
					cout << a[i] << " ";
					cout << endl;

			}

	for (int i = 0; i < 10; i++)
		cout << a[i] << " ";
		cout << endl;

	return 0;
}

template<class T>
T insertionSort(T a[]) 
{
	T ins;

	for (int i = 0; i < 10; i++)
		cout << a[i] << " ";
		cout << endl;
	
	for (int i = 1; i < 10; i++) {
		int j = i;
		while (j > 0 && a[j - 1] > a[j]) {
			ins = a[j];
			a[j] = a[j - 1];
			a[j - 1] = ins;
			j--;
		}//end of while loop

		for (int i = 0; i < 10; i++)
			cout << a[i] << " ";
			cout << endl;
	}
	return 0;
}

template<class T>
T getArray(T a[])
{
	//this part of the program lets the user input data of type T into the array
	cout << "Enter 10 " << typeid(T).name() << " elements." << endl;
	for (int i = 0; i < 10; i++) 
	{                           
			cin >> a[i];
		}

	cout << "Here is your unsorted array:" << endl;

	return 0;

	}

int main()
{
	while (1) {
		
		int userSelectType;
		int userSelectSort;

		cout << "-----------------------------------------------------------" << endl;
		cout << "First, select which type of array to sort:" << endl;
		cout << "1 ----------- Sort integer array" << endl;
		cout << "2 ----------- Sort double array" << endl;
		cout << "3 ----------- Use character array" << endl;
		cout << "-----------------------------------------------------------" << endl;
		cin >> userSelectType;
		//user selects type of data to sort

		cout << "-----------------------------------------------------------" << endl;
		cout << "Next, select what sorting algorithm to use." << endl;
		cout << "1 ----------- Use selection sort" << endl;
		cout << "2 ----------- Use bubble sort" << endl;
		cout << "3 ----------- Use insertion sort" << endl;
		cout << "-----------------------------------------------------------" << endl;
		cin >> userSelectSort;
		//user selects algorithm to use


		//calls the function to take in an array and then the selected sorting function
		if (userSelectType == 1) {
			int a[10];
			getArray(a);
			if (userSelectSort == 1) {
				selectSort(a);
			}
			else if (userSelectSort == 2) {
				bubbleSort(a);
			}
			else if (userSelectSort == 3) {
				insertionSort(a);
			}
		}
		else if (userSelectType == 2) {
			double a[10];
			getArray(a);
			if (userSelectSort == 1) {
				selectSort(a);
			}
			else if (userSelectSort == 2) {
				bubbleSort(a);
			}
			else if (userSelectSort == 3) {
				insertionSort(a);
			}
		}
		else if (userSelectType == 3) {
			char a[10];
			getArray(a);
			if (userSelectSort == 1) {
				selectSort(a);
			}
			else if (userSelectSort == 2) {
				bubbleSort(a);
			}
			else if (userSelectSort == 3) {
				insertionSort(a);
			}
		}

		char cntn;
		cout << "Would you like to do another test? (y/n):";
		//asks if the user would like to perform another test
		//if user inputs n, then program breaks
		cin >> cntn;
		if (cntn == 'n')
			break;		
	}
	
	cout << "" << endl;
	cout << "Thank you for using the program! Bye!" << endl;
	//program shows its appreciation

	return 0;
}