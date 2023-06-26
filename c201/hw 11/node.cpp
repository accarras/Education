/*****************************************************************************
  Author:        Liqiang Zhang
  File name:     node.cpp
  Last updated:  Sept. 2015
  Description:   Implementation the list Node class                 
******************************************************************************/
#include "stdafx.h"
#include <iostream>
using namespace std;

#include <stdlib.h>
#include "node.h"

// Default constructor
Node::Node()
  :data(0), link(NULL)
{
	//intend to be empty here
}

// Constructor with one parameter
Node::Node(int theData)
  :data(theData), link(NULL)
{
	//intend to be empty here
}

// Constructor with two parameters
Node::Node(int theData, Node* theLink)
	:data(theData), link(theLink)
{
	//intend to be empty here
}
  
// Destructor.  We don't delete the entire list here because we may
// want to be able to delete individual nodes without deleting the
// entire list attached to them.
Node::~Node()
{}

// Copy constructor. It makes a soft copy of the node - the list
// starting from the pointer next is not copied. The next pointer in
// the target object will contain the same value as the pointer next
// in the data object.
Node::Node(const Node& theNode)
	:data(theNode.data), link(theNode.link)
{
	//intend to be empty here
}

//Accessors and Mutators
int Node::getData() const
{
	return data;
}

Node* Node::getLink() const
{
	return link;
}

void Node::setData(int theData)
{
	data = theData;
}

void Node::setLink(Node* pointer)
{
	link = pointer;
}

 
// Assignment operator. It makes a soft copy of the node - the next
// pointer is not copied.
Node& Node::operator=(const Node &theNode)
{
	if (&theNode != this) {
		data = theNode.data;
		link = NULL;
	}
	return *this;
}

// Compare one node with another to see if they contain the same data.
bool Node::operator==(const Node &theNode) const
{
	return (data == theNode.data);
}

