/**********************************************************
  File:          porter.cc                             
  Description:   Functions for applying the Porter 
                 transformation to a word.

                                                         
  Author:        Dana Vrajitoru  and Stuart J. Barr                      
  Organization:  IUSB                                    
  Date:          October 5, 2004
***********************************************************/

#include <cstdio>
#include <cstring>
#include "porter.h"

void strip_affixes ( char *string)
{
  to_lower_case(string);
  clean(string);
  strip_prefixes(string);
  strip_suffixes(string);
  string[KEYWORDSIZE] = '\0';
}

void to_lower_case (char *kwd)
{
  int i;
  for ( i=0 ; i < strlen(kwd) ; i++ )
    if ( (kwd[i] >= 'A') && (kwd[i] <= 'Z') )
      kwd[i] += 'a' - 'A';
}

void clean (char *kwd)
{
  int i,j,last=strlen(kwd);
  for ( i=0 ; i < last ; i++ ) {
    if ( isvalid(kwd[i]) != 0 ) {
      for ( j = i ; j < last-1 ; j++ )
	kwd[j] = kwd[j+1];
      kwd[last-1] = '\0';
      last--;
      i--;
    }
  }
}

int isvalid(char l)
{
  if ( (l >= 'a') && (l <= 'z') )
    return(0);
  if ( (l >= 'A') && (l <= 'Z') )
    return(0);
  if ( (l >= '0') && (l <= '9') )
    return(0);
  return(1);
}

void strip_prefixes (char *string )
{
  static char *prefixes[] = { "kilo", "micro", "milli", "intra", "ultra",
			      "mega", "nano", "pico", "pseudo", 0 };
  int i;
  for ( i=0 ; prefixes[i] != 0 ; i++ ) {
    if ( strncmp(prefixes[i],string,strlen(prefixes[i])) == 0 ) {
      int j;
      for ( j=0 ; j<strlen(string)-strlen(prefixes[i]) ; j++ )
	string[j] = string[j+strlen(prefixes[i])];
      string[j] = '\0';
      return;
    }
  }
}

void strip_suffixes ( char *string )
{
  step_1 ( string );
  step_2 ( string );
  step_3 ( string );
  step_4 ( string );
  step_5 ( string );
}

void step_1 ( char *string )
{
  char stem[BIG_KEYWORDSIZE];
  if ( string[strlen(string)-1] == 's' ) {
    if ( (has_suffix(string,"sses",stem) == true) ||
	 (has_suffix(string,"ies",stem) == true ) )
      string[strlen(string)-2] = '\0';
    else
      if ( string[strlen(string)-2] != 's' )
	string[strlen(string)-1] = '\0';
  }
  if ( has_suffix(string,"eed",stem) == true ) {
    if ( measure(stem) > 0 )
      string[strlen(string)-1] = '\0';
  }
  else {
    if ( ( (has_suffix(string,"ed",stem) == true )
	   || (has_suffix(string,"ing",stem) == true ) )
	 && (contains_vowel(stem) == true ) ) {
      string[strlen(stem)] = '\0';
      if ( ( has_suffix(string,"at",stem) == true )
	   || ( has_suffix(string,"bl",stem) == true )
	   || ( has_suffix(string,"iz",stem) == true ) ) {
	string[strlen(string)+1] = '\0';
	string[strlen(string)] = 'e';
      }
      else {	int length = strlen(string);
      if ( (string[length-1] == string[length-2])
	   && (string[length-1] != 'l')
	   && (string[length-1] != 's')
	   && (string[length-1] != 'z') )
	string[length-1] = '\0';
      else
	if ( measure(string) == 1 ) {
	  if ( cvc(string) == true ) {
	    string[strlen(string)+1] = '\0';
	    string[strlen(string)] = 'e';
	  }
	}
      }
    }
  }
  if ( (has_suffix(string,"y",stem) == true) &&
       (contains_vowel(stem) == true) )
    string[strlen(string)-1] = 'i';
}

void step_2 ( char *string )
{
  static char *suffixes[][2] =  { { "ational", "ate" },
				  { "tional",  "tion" },
				  { "enci",    "ence" },
				  { "anci",    "ance" },
				  { "izer",    "ize" },
				  { "iser",    "ize" },
				  { "abli",    "able" },
				  { "alli",    "al" },
				  { "entli",   "ent" },
				  { "eli",     "e" },
				  { "ousli",   "ous" },
				  { "ization", "ize" },
				  { "isation", "ize" },
				  { "ation",   "ate" },
				  { "ator",    "ate" },
				  { "alism",   "al" },
				  { "iveness", "ive" },
				  { "fulness", "ful" },
				  { "ousness", "ous" },
				  { "aliti",   "al" },
				  { "iviti",   "ive" },
				  { "biliti",  "ble" },
				  {  0,        0     } };
  char stem[BIG_KEYWORDSIZE];
  register int index;
  for ( index = 0 ; suffixes[index][0] != 0 ; index++ ) {
    if ( has_suffix ( string, suffixes[index][0], stem ) == true ) {
      if ( measure ( stem ) > 0 ) {
	sprintf ( string, "%s%s", stem, suffixes[index][1] );
	return;
      }
    }
  }
  return;
}

void step_3 ( char *string )
{
  static char *suffixes[][2] = { { "icate", "ic" },
				 { "ative", "" },
				 { "alize", "al" },
				 { "alise", "al" },
				 { "iciti", "ic" },
				 { "ical",  "ic" },
				 { "ful",   "" },
				 { "ness",  "" },
				 { 0,       0 } };
  char stem[BIG_KEYWORDSIZE];
  register int index;
  for ( index = 0 ; suffixes[index][0] != 0 ; index++ ) {
    if ( has_suffix ( string, suffixes[index][0], stem ) == true )
      if ( measure ( stem ) > 0 ) {
	sprintf ( string, "%s%s", stem, suffixes[index][1] );
	return;
      }
  }
  return;
}

void step_4 ( char *string )
{
  static char *suffixes[] = { "al", "ance", "ence", "er", "ic", "able",
			      "ible", "ant", "ement", "ment", "ent", "sion", "tion",
			      "ou", "ism", "ate", "iti", "ous", "ive", "ize", "ise", 0 };
  char stem[BIG_KEYWORDSIZE];
  register int index;
  for ( index = 0 ; suffixes[index] != 0 ; index++ ) {
    if ( has_suffix ( string, suffixes[index], stem ) == true )
      if ( measure ( stem ) > 1 ) {
	strcpy( string, stem );
	return;
      }
  }
  return;
}

void step_5 ( char *string )
{
  if ( string[strlen(string)-1] == 'e' ) {
    if ( measure(string) > 1 )
      /* measure(string)==measure(stem) if ends in vowel */
      string[strlen(string)-1] = '\0';
    else
      if ( measure(string) == 1 ) {
	char stem[BIG_KEYWORDSIZE];
	strcpy(stem,"");
	strncat( stem, string, strlen(string)-1 );
	if ( cvc(stem) == false )
	  string[strlen(string)-1] = '\0';
      }
  }
  if ( (string[strlen(string)-1] == 'l')
       && (string[strlen(string)-2] == 'l')
       && (measure(string) > 1) )
    string[strlen(string)-1] = '\0';
}

bool has_suffix ( char *word, char *suffix, char *stem )
{
  char tmp[BIG_KEYWORDSIZE];
  if ( strlen(word) <= strlen(suffix) )
    return(false);
  if ( (strlen(suffix) > 1) &&
       ( word[strlen(word)-2] != suffix[strlen(suffix)-2] ) )
    return(false);
  strcpy ( stem, "" );
  strncat ( stem, word, strlen(word) - strlen(suffix) );
  strcpy ( tmp, stem );
  strcat ( tmp, suffix );
  if ( strcmp (  tmp, word ) == 0 )
    return(true);
  else
    return(false);
}

bool cvc ( char *string )
{
  int length=strlen(string);
  if ( length < 3 )
    return(false);
  if ( (vowel(string[length-1],string[length-2]) == false)
       && (string[length-1] != 'w')
       && (string[length-1] != 'x')
       && (string[length-1] != 'y')
       && (vowel(string[length-2],string[length-3]) == true)
       && ( ( (length==3) && (vowel(string[0],'a') == false) )
	    || (vowel(string[length-3],string[length-4])==false) ) )
    return(true);
  else
    return(false);
}

bool vowel ( char ch, char prev )
{
  switch ( ch ) {
  case 'a':
  case 'e':
  case 'i':
  case 'o':
  case 'u': return(true);
    break;
  case 'y': return( vowel(prev,'?') == true );
    break;
  default : return(false);
    break;
  }
}

int measure ( char *stem )
{
  register int i=0, count = 0;
  int length=strlen(stem);
  while ( i < length ) {
    for ( ; i < length ; i++ ) {
      if ( i > 0 ) {
	if ( vowel(stem[i],stem[i-1]) == true )
	  break;
      }
      else {	if ( vowel(stem[i],'a') == true )
	break;
      }
    }
    for ( i++ ; i < length ; i++ ) {
      if ( i > 0 ) {
	if ( vowel(stem[i],stem[i-1]) == false )
	  break;
      }
      else {	if ( vowel(stem[i],'?') == false )
	break;
      }
    }
    if ( i < length ) {
      count++;
      i++;
    }
  }
  return(count);
}

bool contains_vowel ( char *word )
{
  register int i;
  for ( i=0 ; i < strlen(word) ; i++ )
    if ( i > 0 ) {
      if ( vowel(word[i],word[i-1]) == true )
	return(true);
    }
    else {	if ( vowel(word[0],'a') == true )
      return(true);
    }
  return(false);
}

