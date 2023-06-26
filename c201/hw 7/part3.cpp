// ConsoleApplication15.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <cstdlib>
#include <cmath>
using namespace std;

class Rational {

public:

	int getNumerator() const {
		return numerator;
	}
	void setNumerator(int n) {
		numerator = n;
	}

	int getDenominator() const {
		return denominator;
	}
	void setDenominator(int d) {
		denominator = d;
	}

	Rational(int firstNum, int secondNum) {
		setNumerator(firstNum);
		setDenominator(secondNum);
	}

	Rational(int wholeNumber) {
		setNumerator(wholeNumber);
		setDenominator(1);
	}

	Rational() {
		setNumerator(0);
		setDenominator(1);
	}

	friend istream &operator>>(istream  &input, Rational &D)
	{
		char dummy;
		int inp1;
		int inp2;

		input >> inp1;
		input >> dummy;
		input >> inp2;

		D.setNumerator(inp1);
		D.setDenominator(inp2);

		return input;
	}


	friend ostream &operator<<(ostream &output, const Rational &D)
	{
		Rational rat;

		int g = gcd(D.getNumerator(), D.getDenominator());

		rat.setNumerator(D.getNumerator() / g);
		rat.setDenominator(D.getDenominator() / g);

		if ((rat.getNumerator() > 0) && (rat.getDenominator() < 0)) {
			rat.setNumerator(-rat.getNumerator());
			rat.setDenominator(abs(rat.getDenominator()));
		}

		output << rat.getNumerator() << "/" << rat.getDenominator();
		return output;
	}

	static int gcd(int a, int b) {
		return (b == 0 ? a : gcd(b, a%b));
	}

	static Rational simplify(Rational& num) {
		Rational r0;
		if ((num.getNumerator() > 0) && (num.getDenominator() < 0)) {
			num.setNumerator(-num.getNumerator());
			num.setDenominator(abs(num.getDenominator()));
		}
		int g = gcd(num.getNumerator(), num.getDenominator());
		r0.setNumerator(num.getNumerator() / g);
		r0.setDenominator(num.getDenominator() / g);
		return r0;
	}

	Rational operator +(const Rational& num) {
		Rational r1;
		r1.setNumerator(this->numerator * num.getDenominator() + this->denominator*num.getNumerator());
		r1.setDenominator(this->denominator * num.getDenominator());
		return (simplify(r1));
	}

	Rational operator -(const Rational& num) {
		Rational r2;
		r2.setNumerator(this->numerator * num.getDenominator() - this->denominator*num.getNumerator());
		r2.setDenominator(this->denominator * num.getDenominator());
		return (simplify(r2));
	}

	Rational operator *(const Rational& num) {
		Rational r3;
		r3.setNumerator(this->numerator * num.getNumerator());
		r3.setDenominator(this->denominator * num.getDenominator());
		return (simplify(r3));
	}

	Rational operator / (const Rational& num) {
		Rational r4;
		r4.setNumerator(this->numerator * num.getDenominator());
		r4.setDenominator(this->denominator * num.getNumerator());
		return (simplify(r4));
	}

	bool operator ==(Rational& num) const {
		Rational r5(this->numerator, this->denominator);
		simplify(r5);
		simplify(num);
		if ((this->numerator == num.getNumerator()) && (this->denominator == num.getDenominator()))
			return true;

		else { return false; }
	}

	bool operator >(const Rational& num) const {
		if ((this->numerator * num.getDenominator()) > (this->denominator * num.getNumerator()))
			return true;

		else
			return false;
	}

	bool operator <(const Rational& num) const {
		if ((this->numerator * num.getDenominator()) < (this->denominator * num.getNumerator()))
			return true;

		else
			return false;
	}

	bool operator >=(const Rational& num) const {
		if ((this->numerator * num.getDenominator()) >= (this->denominator * num.getNumerator()))
			return true;

		else
			return false;
	}

	bool operator <=(const Rational& num) const {
		if ((this->numerator * num.getDenominator()) <= (this->denominator * num.getNumerator()))
			return true;

		else
			return false;
	}


private:
	int numerator;
	int denominator;
};

int main()
{
	cout << "Testing declarations" << endl;
	cout << "Rational x, y(2), z(-5,-6), w(1,-3);" << endl;
	Rational x, y(2), z(-5, -6), w(1, -3);
	cout << "z = " << z << ", y = " << y << ",  z = " << z
		<< ", w = " << w << endl;

	cout << "Testing >> overloading: \nEnter a fraction in the format "
		<< "integer_numerator/integer_denominator" << endl;
	cin >> x;
	cout << "You entered the equivalent of: " << x << endl;
	cout << z << " -  (" << w << ") = " << z - w << endl;

	cout << "Testing the constructor and normalization routines: " << endl;
	y = Rational(-128, -48);
	cout << "y =Rational(-128, -48) outputs as " << y << endl;
	y = Rational(-128, 48);
	cout << "y =Rational(-128, 48 ) outputs as " << y << endl;
	y = Rational(128, -48);
	cout << "y = Rational(128, -48) outputs as " << y << endl;
	Rational a(1, 1);
	cout << "Rational a(1,1); a outputs as: " << a << endl;
	Rational ww = y*a;
	cout << y << " * " << a << " = " << ww << endl;

	w = Rational(25, 9);
	cout << "w = Rational(25, 9);" << endl;
	z = Rational(3, 5);
	cout << "z = Rational(3, 5);" << endl;
	cout << "Testing arithmetic and relational operator overloading"
		<< endl;
	cout << w << " * " << z << " = " << w * z << endl;
	cout << w << " + " << z << " = " << w + z << endl;
	cout << w << " - " << z << " = " << w - z << endl;
	cout << w << " / " << z << " = " << w / z << endl;

	cout << w << " <  " << z << " = " << (w < z) << endl;
	cout << w << " < " << w << " = " << (w < w) << endl;
	cout << w << " <= " << z << " = " << (w <= z) << endl;
	cout << w << " <= " << w << " = " << (w <= w) << endl;

	cout << w << " >  " << z << " = " << (w > z) << endl;
	cout << w << " > " << w << " = " << (w > w) << endl;
	cout << w << " >= " << z << " = " << (w >= z) << endl;
	cout << w << " >= " << w << " = " << (w >= w) << endl;

	w = Rational(-21, 9);
	cout << "w = Rational(-21, 9);" << endl;
	z = Rational(3, 5);
	cout << "z = Rational(3, 5);" << endl;
	cout << w << " * " << z << " = " << w * z << endl;
	cout << w << " + " << z << " = " << w + z << endl;
	cout << w << " - " << z << " = " << w - z << endl;
	cout << w << " / " << z << " = " << w / z << endl;
	cout << w << " <  " << z << " = " << (w < z) << endl;
	cout << w << " < " << w << " = " << (w < w) << endl;
	cout << w << " <= " << z << " = " << (w <= z) << endl;
	cout << w << " <= " << w << " = " << (w <= w) << endl;

	cout << w << " >  " << z << " = " << (w > z) << endl;
	cout << w << " > " << w << " = " << (w > w) << endl;
	cout << w << " >= " << z << " = " << (w >= z) << endl;
	cout << w << " >= " << w << " = " << (w >= w) << endl;

	cout << "7%(-2)=" << 7 % (-2) << endl;
	cout << "-7%2=" << -7 % 2 << endl;
	return 0;
}