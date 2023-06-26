#ifndef FIGURE_H
#define FIGURE_H
#include <iostream>
class Figure
{
public:
	void erase()
	{
		system("cls");  // to clear the window
	}
	virtual void draw() = 0;
	void center()
	{
		erase();
		draw();
	}
};
#endif