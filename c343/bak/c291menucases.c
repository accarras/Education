/**************************************************************
File creaed by: Tony Carrasco
Date: 10/30/17
Class C291
filename: c291menucases.c
Summary: This program prints a menu using cases
The user picks an option from the menu, and the program will 
either print a shape or quit
**************************************************************/
#include <stdio.h>   // for printf and scanf satements

int main()
{
   char choice; // stores user input
   
   while(choice!='q' && choice!='Q'){
   // program repeats until user selects q
 
       /*Displaing on screen*/
       printf("-------Menu-------\n");
       printf("b) Print a box 1\n");
       printf("o) Print an oval\n");
       printf("l) Print a line\n");
       printf("t) Print a triangle\n");
       printf("h) Print a hut\n");
       printf("Q) Quit Program\n");
     
       // gets input from user
       choice = getchar();
	   
	   // determines which choice corresponds to the user input
	   switch(choice){
     
	    // user picked b
		case 'b':
			printf("You chose to print a box!\n");
			boxPrint();
			break;
	    case 'B':
			printf("You chose to print a box!\n");
			boxPrint();
			break;
		  
        // user picked o
        case 'o':
			printf("You chose to print an oval!\n");
			ovalPrint();
			break;
	    case 'O':
			printf("You chose to print an oval!\n");
			ovalPrint();
			break;
						
        // user picked l
        case 'l':
            printf("You chose to print a line!\n");
            linePrint();
			break;
	    case 'L':
            printf("You chose to print a line!\n");
            linePrint();
			break;
 
        // user picked t
	    case 't':
			printf("You chose to print a triangle!\n");
			trianglePrint();
			break;
		case 'T':
			printf("You chose to print a triangle!\n");
			trianglePrint();
			break;
       
	    // user picked h
        case 'h':
			printf("You chose to print a hut!\n");
			hutPrint();
			break;
		case 'H':
			printf("You chose to print a hut!\n");
			hutPrint();
			break;
        }
    }
   
    return 0;
}

int boxPrint(){ // this method makes a box
    printf("********\n");
    printf("*      *\n");
    printf("*      *\n");
    printf("*      *\n");
    printf("*      *\n");
    printf("*      *\n");
    printf("*      *\n");
    printf("********\n");
    return 0;
}

int ovalPrint(){ // this method makes an oval
    printf("   ***   \n");
    printf("  *   *  \n");
    printf(" *     * \n");
    printf(" *     * \n");
    printf(" *     * \n");
    printf(" *     * \n");
    printf(" *     * \n");
    printf("  *   *  \n");
    printf("   ***   \n");
    return 0;
}

int linePrint(){ // this method makes a line
    printf("        \n");
    printf("        \n");
    printf("        \n");
    printf("        \n");
    printf("********\n");
    printf("        \n");
    printf("        \n");
    printf("        \n");
    printf("        \n");
    return 0;
}

int trianglePrint(){ // this method prints a triangle
    printf("       *       \n");
    printf("      * *      \n");
    printf("     *   *     \n");
    printf("    *     *    \n");
    printf("   *       *   \n");
    printf("  *         *  \n");
    printf(" *           * \n");
    printf("***************\n");
    return 0;
}

int hutPrint(){ // this method prints a hut
    printf("       *       \n");
    printf("      ***      \n");
    printf("     *****     \n");
    printf("    *******    \n");
    printf("   *********   \n");
    printf("   ***   ***   \n");
    printf("   ***   ***   \n");
    printf("   ***   ***   \n");
    return 0;
}