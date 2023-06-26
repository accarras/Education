/**************************************************************
File creaed by: Tony Carrasco
Date: 10/30/17
Class C291
filename: c291symbols.c
Summary: This program get a number of lines to print, 
number of symbols to print per line, and symbol to print from the user.
It then prints the number of lines and number of symbols per line,
using the specified symbol. Every even line has a number of tabs
inserted at the front corresponding to the number of even lines
Number of lines must be greater than 7. Number of symbols must be 
between 7 and 27
 **************************************************************/
#include <stdio.h> // for printf and scanf satements

int main()
{
    int num_lines=0; // stores number of lines from user
    int num_symbols=0;// stores number of symbols per line
    char userSymbol; // stores the symbol to use
    
    printf("Enter a number of lines that is at least 7\n");
    scanf("%d", &num_lines); // scans in number of lines
    while (num_lines<7){ 
	// handles out-of-range number
        printf("Number of lines not large enough, please enter again\n");
        scanf("%d", &num_lines);
    }
    
    printf("Enter a symbol to make up the lines\n");
    scanf(" %c", &userSymbol); // scans in symbol to use
    
    printf("Enter the number of symbols that you would like in in each line\n");
    printf("Number of symbols must be between 7 and 27\n");
    scanf("%d", &num_symbols); // scans in number of symbols per line
    while (num_symbols<7 || num_symbols >27){ 
	// handles out-of-range number
        printf("Number of symbols out of range, please enter again\n");
        scanf("%d", &num_symbols);
    }
    
    int tabs=0; // stores number of times line is indented
    
    for (int i = 0; i <= num_lines; i++) {
    //print lines
        if (!(i%2)==0){ // indents line if even
            tabs++;
            for (int j = 0; j < tabs; j++){
                printf("\t"); // prints tab
            }
		}
        for(int k = 0; k <= num_symbols; k++){
	    // prints symbols
            printf("%c", userSymbol);
        }
        
    printf("\n"); // goes to nextline  
    }
    

    return 0;
}



