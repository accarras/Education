#include "stdafx.h"
#include "figure.h"
#include "triangle.h"


Triangle::Triangle() {
	setHeight(6);
}
Triangle::Triangle(int height, int centerX, int centerY) {
	setHeight(height);
	setCenterX(centerX);
	setCenterY(centerY);
}

void Triangle::setHeight(int i) {
	height = i;
}
int Triangle::getHeight() const {
	return height;
}

void Triangle::setCenterX(int i) {
	centerX = i;
}
int Triangle::getCenterX() const {
	return centerX;
}

void Triangle::setCenterY(int i) {
	centerY = i;
}
int Triangle::getCenterY() const {
	return centerY;
}

void Triangle::draw() {
	int i = 1;

	for (int y = 0; y < ((getCenterY() - (getHeight() / 2))/2); y++) {
		cout << endl;
	}

	for (int a = 1; a <= getHeight(); a++)
	{
		for (int b = (getHeight() - 1); b >= a; b--)
		{
			cout << " ";  // Printing Space Here
		}

	
			for (int x = 0; x < (((getCenterX() - ((getHeight()-1) / 2)))/2); x++) {
				cout << " ";
			}


		for (int c = 0; c < i; c++)
		{
			cout << "*";  // Printing asterisk here
		}
		cout << endl;   // new line
		i = i + 2;
	}
}