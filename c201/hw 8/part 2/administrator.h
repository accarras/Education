#ifndef ADMINISTRATOR_H
#define ADMINISTRATOR_H

#include <string>
#include "employee.h"
#include "salariedemployee.h"
using std::string;

namespace SavitchEmployees {

	class Administrator : public SalariedEmployee
	{

	public:
		Administrator();
		Administrator(string theName, string theSSN, string theTitle, string theResponsibility, string theSupervisor, double theSalary);

		void setTitle(string s);
		string getTitle();
		void setResponsibility(string s);
		string getResponsibility();
		void setSupervisor(string s);
		string getSupervisor();
		void setAnnualSalary(double d);
		double getAnnualSalary();

		void getAdminData();
		void print();

	private:
		string title;
		string responsibility;
		string supervisor;
		double annualSalary;
	};
}
#endif