#include "stdafx.h"
#include "figure.h"
#include "rectangle.h"
Rectangle::Rectangle() {
	setHeight(6);
	setWidth(8);
}
Rectangle::Rectangle(int height, int width) {
	setHeight(height);
	setWidth(width);
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

void Rectangle::draw() {

	for (int n = 0; n < getHeight(); n++) {
		for (int i = 0; i < getWidth(); i++) {
			cout << "*";
		}
		cout << endl;
	}
}