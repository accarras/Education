/*****************************************************************************
  Author:	Tony Carrasco, Insertionsort and Quicksort provided by Dr. Surma
  Class:  	C243 Spring 2016
  File name:	main.cc
  Last updated: March 2, 2016
  Description:  This file contains the "main" function, which calls four
                different sorting algorithms. These algorithms are the
                bubblesort, selectionsort, insertionsort, and quicksort.
                The implementation of these algorithms are also contained
                within this file. In the main function, the user is asked to
                input a file name. One ifstream object is created from this
                file so that the lines of the file can be counted. Another
                ifstream object is created from this file, and the data is
                read into a dynamic array. This data is then copied to a
                working set array. The workingset is then sorted by each
                algorithm. The time that it takes for each algorithm to
                execute is printed to the screen. An ofstream object is created
                for each algorithm, and the output of each algorithm is output
                to a file. The user is asked if the program should continue to
                part 2. If they continue, the array of sorted data will have 10
                random integers added onto the end.  The data will then be
                sorted and output as in the first part. Finally, all
                ifstream and ofstream objects are closed, and all memory
                allocated with the "new" keyword is given back to the system.
******************************************************************************/


#include <iostream>
#include <fstream>
#include <cstdlib>
#include <string>
//  required for timing
#include <sys/time.h>
using namespace std;

//  declares the before and after structures which
//  will be used for timing the function
struct timeval before, after;
//  this variable will hold the actual time of execution
double timing;

//  this is the declaration for a function which
//  will count the lines of the input file
int countlines(string fileName, int &numLines);

//  declaration for a function which will
//  swap two numbers
void swap(int& v1, int& v2);

//  declarations for the sorting algorithms
//  which will be used by the program
void bubbleSort(int data[], int size);
void selectionSort(int data[], int size);
void insertionSort(int data[], int size);
void quickSort(int data[], int left, int right);

int main() {
	//  puts program in a loop
	//  allows "break" to be used
	while(1){

		//  will contain the number of lines in the file
		int numLines = 0;

		//  this string will store the name of the file
		string fileName;
		cout << "Enter File Name: ";
		cin >> fileName;

		//  this accesses the file as named by the user
		ifstream inStream;
		inStream.open(fileName.c_str());
		if (inStream.fail()) {
			cout << "Input Failed. \n";
			exit(1);
		}

		//  calls the function which will count the number of lines
		countlines(fileName, numLines);

		// the data array will store data from the file and won't be altered
		int *data = new int[numLines];
		// the working set will be a copy of the  data array
		//  this will be what the algorithms modify
		int *workingSet = new int[numLines];

        // inputs each line of data into a space in the array
		for (int i = 0; i < numLines; i++) {
			inStream >> data[i];
		}
        //  closes the file as it is no longer needed
		inStream.close();

		//  copies data to working set
		for (int i = 0; i < numLines; i++) {
			workingSet[i] = data[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		bubbleSort(workingSet, numLines);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete bubble sort was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream bubble("bubblesort.dat");
		for (int i = 0; i < numLines; i++) {
			bubble << workingSet[i] << endl;
		}
		cout << "The values sorted by bubble sort have been written to the file bubblesort.dat"
		     << endl;
		//  closes the ofstream
		bubble.close();

		//  copies the data to the working set
		for (int i = 0; i < numLines; i++) {
			workingSet[i] = data[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		selectionSort(workingSet, numLines);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete selection sort was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream selection("selectionsort.dat");
		for (int i = 0; i < numLines; i++) {
			selection << workingSet[i] << endl;
		}
		cout << "The values sorted by selection sort have been written to the file selectionsort.dat"
			 << endl;
		//  closes the ofstream
		selection.close();

		//  copies the data to the working set
		for (int i = 0; i < numLines; i++) {
			workingSet[i] = data[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		insertionSort(workingSet, numLines);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete insertion sort was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream insertion("insertionsort.dat");
		for (int i = 0; i < numLines; i++) {
			insertion << workingSet[i] << endl;
		}
		cout << "The values sorted by insertion sort have been written to the file insertionsort.dat"
		     << endl;
		//  closes the ofstream
		insertion.close();

		//  copies the data to the working set
		for (int i = 0; i < numLines; i++) {
			workingSet[i] = data[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		quickSort(workingSet, 0, numLines-1);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete quick sort was " << (timing) << " ms."
		     << endl;
		//  outputs the sorted data to a file
		ofstream quick("quicksort.dat");
		for (int i = 0; i < numLines; i++) {
			quick << workingSet[i] << endl;
		}
		cout  << "The values sorted by quick sort have been written to the file quicksort.dat"
			  << endl;
		//  closes the ofstream
		quick.close();

		//  asks the user if they would like to initiate the
		//  second part of the program
		//  if the user doesn't select yes, the program is stopped
		//  all memory allocated with new is deleted
		char cntn;
		cout << "Would you like to run Part 2? (y/n):";
		cin >> cntn;
		if (cntn == 'n') {
			cout << "Program ended before part 2" << endl;
			delete data;
			delete workingSet;
			data = NULL;
			workingSet = NULL;
			break;
		}

        //  data set 2 is ititialize with size increased by 10
		int *data2 = new int[numLines + 10];
		int *workingSet2 = new int[numLines + 10];
        //  data 2 is copied from the sorted data from part 1
		for (int i = 0; i < numLines; i++) {
			data2[i] = workingSet[i];
		}

		// this enables random number generation based on system time
		srand(time( NULL));

		//  for every extra spot that data has,
		//  generates a random number to fill that spot
		for (int i = numLines; i < numLines + 10; i++) {
			data2[i] = (rand() % 32000) + 1;
		}

		//  copies the data set to the working set
		for (int i = 0; i < numLines + 10; i++) {
			workingSet2[i] = data2[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		bubbleSort(workingSet2, numLines + 10);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete bubble sort 2 was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream bubble2("bubblesort2.dat");
		for (int i = 0; i < numLines + 10; i++) {
			bubble2 << workingSet2[i] << endl;
		}
		cout  << "The values sorted by bubble sort have been written to the file bubblesort2.dat"
			  << endl;
		//  closes ofstream
		bubble2.close();

		//  copies the data set to the working set
		for (int i = 0; i < numLines + 10; i++) {
			workingSet2[i] = data2[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		selectionSort(workingSet2, numLines + 10);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete selection sort 2 was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream selection2("selectionsort2.dat");
		for (int i = 0; i < numLines + 10; i++) {
			selection2 << workingSet2[i] << endl;
		}
		cout
				<< "The values sorted by selection sort have been written to the file selectionsort2.dat"
				<< endl;
		//  closes ofstream
		selection2.close();

		//  copies the data set to the working set
		for (int i = 0; i < numLines + 10; i++) {
			workingSet2[i] = data2[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		insertionSort(workingSet2, numLines + 10);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete insertion sort 2 was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream insertion2("insertionsort2.dat");
		for (int i = 0; i < numLines + 10; i++) {
			insertion2 << workingSet2[i] << endl;
		}
		cout  << "The values sorted by insertion sort have been written to the file insertionsort2.dat"
			  << endl;
		//  closes ofstream
		insertion2.close();

		//  copies the data set to the working set
		for (int i = 0; i < numLines + 10; i++) {
			workingSet2[i] = data2[i];
		}
		//  times the function before and after the function call
		gettimeofday(&before, 0);
		quickSort(workingSet2, 0, numLines + 9);
		gettimeofday(&after, 0);
		timing = (double) ((double) after.tv_sec
			   + (double) after.tv_usec / (1000 * 1000))
			   - (double) ((double) before.tv_sec
			   + (double) before.tv_usec / (1000 * 1000));
		//  prints the running time to the screen
		cout << "The time to complete quick sort 2 was " << (timing) << " ms."
			 << endl;
		//  outputs the sorted data to a file
		ofstream quick2("quicksort2.dat");
		for (int i = 0; i < numLines + 10; i++) {
			quick2 << workingSet2[i] << endl;
		}
		cout  << "The values sorted by quick sort have been written to the file quicksort2.dat"
			  << endl;
		//  closes ofstream
		quick2.close();

        //  deallocates memory that was allocated to dynamic arrays
		delete data;
		delete workingSet;
		data = NULL;
		workingSet = NULL;

		delete data2;
		delete workingSet2;
		data2 = NULL;
		workingSet2 = NULL;

		return 0;
	}
}

    //  this function counts the lines in a file
int countlines(string fileName, int &numLines){
	//  string is uses in the getline function
	string unused;

	//  declares an ifstream object which
	//  will take in the user defined file
	ifstream inStream;
	inStream.open(fileName.c_str());
	  if (inStream.fail()) {
	    cout << "Input Failed. \n";
		exit(1);
	  }
	 //  as long as there are lines in the file,
	 //  keep adding to numLines
	  while (getline(inStream, unused))
	     numLines++;
	 // closes the ofstream
	  inStream.close();
	 // returns the number of lines to main
	  return numLines;
}

    //  switches two numbers - used for sorting algorithms
void swap(int& v1, int& v2) {
	int temp;
	temp = v1;
	v1 = v2;
	v2 = temp;
}
    //  sorts an array by comparing every pair of numbers
    //  switches them if the first is bigger than the second
void bubbleSort(int data[], int size) {
	bool sorted = false;
	while (!sorted) {
		sorted = true;
		for (int i = 0; i < size - 1; i++) {
			if (data[i] > data[i + 1]) {
				swap(data[i], data[i + 1]);
				sorted = false;
			}
		}
	}
}

void selectionSort(int data[], int size) {
   //pos_min is short for position of min
	int pos_min;

	for (int i = 0; i < size - 1; i++) {
		pos_min = i;
	//set pos_min to the current index of array

		for (int j = i + 1; j < size; j++) {
			if (data[j] < data[pos_min])
				pos_min = j;
	//pos_min will keep track of the spot that min is in
		}

    //if i is less than min, values must be swapped
		if (pos_min != i) {
			swap(data[i], data[pos_min]);
		}
	}
}

void insertionSort(int data[], int size) {
	int temp;
	int j;

	for (int i = 1; i < size; i++) {
		temp = data[i];
		for (j = i; (j > 0) && (temp < data[j - 1]); j--) {
			data[j] = data[j - 1];
		}
		data[j] = temp;
	}
}

void quickSort(int data[], int left, int right) {
	int i, j;
	int partition;

	if (right > left) {
		partition = data[right];
		i = left - 1;
		j = right;

		for (;;) {
			while (data[++i] < partition);
			while (data[--j] > partition);
			if (i >= j)
				break;
			swap(data[i], data[j]);
		}

		swap(data[i], data[right]);

		quickSort(data, left, i - 1);
		quickSort(data, i + 1, right);
	}
}

