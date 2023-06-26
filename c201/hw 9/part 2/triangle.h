#ifndef TRIANGLE_H
#define TRIANGLE_H
#include "figure.h" 
using namespace std;

class Triangle : public Figure {

public:
	Triangle();
	Triangle(int height);

	void setHeight(int i);
	int getHeight() const;

	void draw();

private:
	int height;
};
#endif