#ifndef TRIANGLE_H
#define TRIANGLE_H
#include "figure.h" 
using namespace std;

class Triangle : public Figure {

public:
	Triangle();
	Triangle(int height, int centerX, int centerY);

	void setHeight(int i);
	int getHeight() const;

	void setCenterX(int i);
	int getCenterX() const;

	void setCenterY(int i);
	int getCenterY() const;

	void draw();

private:
	int height;
	int centerX;
	int centerY;
};
#endif