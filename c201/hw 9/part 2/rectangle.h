#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "figure.h"" 
using namespace std;

class Rectangle : public Figure {

public :
	Rectangle();
	Rectangle(int height, int width);

	void setHeight(int i);
	int getHeight() const;

	void setWidth(int i);
	int getWidth() const;

	void draw();

private:
	int height;
	int width;
};
#endif
