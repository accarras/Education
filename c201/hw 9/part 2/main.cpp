#include "stdafx.h"
#include <iostream>
#include "rectangle.h"
#include "triangle.h"
using namespace std;
int main()
{
	char ch;
	Rectangle rect;
	Triangle tri;
	rect.center();
	cout << "rect.center() called, press enter to continue...";
	cin.get(ch);
	tri.center();
	cout << "tri.center() called, press enter to continue...";
	cin.get(ch);
	Figure *fig1, *fig2;
	fig1 = new Rectangle(8, 10);  // a rectangle with width 8 and height 10
	fig2 = new Triangle(10);  // a isosceles triangle with height 10, base length 19
	fig1->center();
	cout << "fig1->center() called, press enter to continue...";
	cin.get(ch);
	fig2->center();
	cout << "fig2->center() called, press enter to continue...";
	cin.get(ch);
	delete fig1;
	delete fig2;
}