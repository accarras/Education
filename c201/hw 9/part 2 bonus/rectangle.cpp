#include "stdafx.h"
#include "figure.h"
#include "rectangle.h"
Rectangle::Rectangle() {
	setHeight(6);
	setWidth(8);
	setCenterX(0);
	setCenterY(0);
}
Rectangle::Rectangle(int height, int width, int centerX, int centerY) {
	setHeight(height);
	setWidth(width);
	setCenterX(centerX);
	setCenterY(centerY);
}

void Rectangle::setHeight(int i) {
	height = i;
}
int Rectangle::getHeight() const {
	return height;
}

void Rectangle::setWidth(int i) {
	width = i;
}
int Rectangle::getWidth() const {
	return width;
}

void Rectangle::setCenterX(int i) {
	centerX = i;
}
int Rectangle::getCenterX() const {
	return centerX;
}

void Rectangle::setCenterY(int i) {
	centerY = i;
}
int Rectangle::getCenterY() const {
	return centerY;
}

void Rectangle::draw() {

	for (int y = 0; y < (getCenterY() - (getHeight() / 2)); y++) {
		cout << endl;
	}

	for (int n = 0; n < getHeight(); n++) {
		for (int x = 0; x < ((getCenterX() - (getWidth() / 2))/2); x++) {
			cout << " ";
		}
		for (int i = 0; i < getWidth(); i++) {
			cout << "*";
			}	
		cout << endl;
		}
		
	}
