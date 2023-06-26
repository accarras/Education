/**************************************************************
File creaed by: Tony Carrasco
Date: 10/30/17
Class C291
filename: c291menu.c
Summary: This program prints a menu using only if-else statements
The user picks an option from the menu, and the program 
will either print a shape or quit
**************************************************************/
#include <stdio.h>   // for printf and scanf satements

int main()
{
   char choice; // stores user input
   
   while(choice!='q' && choice!='Q'){ 
   // program repeats until user selects q
 
       // prints menu
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
       if (choice=='b' || choice=='B') // user picked b
       {
          printf("You chose to print a box!\n");
          boxPrint();
       }
       else if (choice=='o' || choice=='O') // user picked o
       {
          printf("You chose to print an oval!\n");
          ovalPrint();
       }
       else if (choice=='l' || choice=='L') // user picked l
       {
          printf("You chose to print a line!\n"); 
          linePrint();
       }
       else if (choice=='t' || choice=='T') // user picked t
       {
          printf("You chose to print a triangle!\n");
          trianglePrint();
       }
       else if (choice=='h' || choice=='H') // user picked h
       {
          printf("You chose to print a hut!\n");
          hutPrint();
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