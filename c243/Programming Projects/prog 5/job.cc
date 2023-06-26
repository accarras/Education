#include "stdafx.h"
#include <iostream>
#include <string>
#include "job.h"

//using namespace std;

void job::setPriority(int val){
	job::priority = val;
}

void job::setTaskName(std::string tname){
	job::taskName = tname;
}

int job::getPriority(){
	return job::priority;
}

std::string job::getTaskName(){
	return job::taskName;
}

job & job::operator=(const job &src) {
	if (this != &src) {
		setPriority(src.priority);
		setTaskName(src.taskName);
		return *this;
	}
}
