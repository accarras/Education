/*
 * job.h
 *
 *  Created on: Mar 30, 2016
 *  Author: Tony
 */
#ifndef JOB_H
#define JOB_H
#include <iostream>
#include <string>
//using namespace std;

class job {

public:
	
	job() {
		priority = 0;
		taskName = "task0";
	}

	void setPriority(int val);
	int getPriority();

	void setTaskName(std::string tname);
	std::string getTaskName();

	job & operator=(const job & src);
	
	//bool & operator>(const job & src);
	//bool & operator<(const job & src);

private:

	int priority;
	std::string taskName;

};

#endif /* JOB_H_ */

