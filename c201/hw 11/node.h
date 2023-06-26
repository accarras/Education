/*****************************************************************************
  Author:        Liqiang Zhang
  File name:     node.h
  Last updated:  Sept. 2015
  Description:   Definition of the list Node class                
******************************************************************************/

#ifndef NODE_H
#define NODE_H

class Node {
   public:
  	
	// Default constructor
	Node();	

	// Constructor with one parameter
	Node(int theData);

	// Constructor with two parameters
  	Node(int theData, Node* theLink);

  	// Destructor. We don't delete the entire list here because we may
  	// want to be able to delete individual nodes without deleting the
  	// entire list attached to them.
  	~Node();

  	// Copy constructor. It makes a soft copy of the node - the list
  	// starting from the pointer next is not copied. The next pointer in
  	// the target object will contain the same value as the pointer next
  	// in the data object.
  	Node(const Node& theNode);

  	//Accessors and Mutators
  	int getData() const;
  	Node* getLink() const;
  	void setData(int theData);
  	void setLink(Node* pointer);

  	// Assignment operator. It makes a soft copy of the node - the next
  	// pointer is not copied.
  	Node& operator=(const Node &theNode); 

  	// Compare one node with antoher to see if they contain the same data.
 	bool operator==(const Node &theNode) const;

  private:
  	int data;
  	Node *link;
};

#endif
