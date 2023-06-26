#include "stdafx.h"
#include <iostream>
#include "rectangle.h"
#include "triangle.h"
using namespace std;
int main()
{
	char ch;
	Figure *fig3, *fig4, *fig5;
	fig3 = new Rectangle(8, 8, 6, 8); // a retancle with width 8, height 8, centeredat(6, 8)
	fig4 = new Triangle(8, 9, 6);  // a triangle with height 8, base length 15,centered at(9, 6)
	fig5 = new Triangle(10, 4, 3);  // a triangle with height 10, base length 19, centered at(4, 3)
	fig3->center();
	cout << "fig3->center() called, press enter to continue...";
	cin.get(ch);
	fig4->center();
	cout << "fig4->center() called, press enter to continue...";
	cin.get(ch);
	fig5->center();
	cout << "fig5->center() called, press enter to continue...";
	cin.get(ch);
	delete fig3;
	delete fig4;
	delete fig5;
}
