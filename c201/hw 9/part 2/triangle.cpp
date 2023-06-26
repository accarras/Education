#include "stdafx.h"
#include "figure.h"
#include "triangle.h"


Triangle::Triangle() {
	setHeight(6);
}
Triangle::Triangle(int height) {
	setHeight(height);
}

void Triangle::setHeight(int i) {
	height = i;
}
int Triangle::getHeight() const {
	return height;
}

void Triangle::draw() {
	int i = 1;

	for (int a = 1; a <= getHeight(); a++)
	{
		for (int b = (getHeight()-1); b >= a; b--)
		{
			cout << " ";  // Printing Space Here
		}
		for (int c = 0; c<i; c++)
		{
			cout << "*";  // Printing asterisk here
		}
		cout << endl;   // new line
		i = i + 2;
	}
}