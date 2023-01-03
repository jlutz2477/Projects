#ifndef WORD_H
#define WORD_H

#include <assert.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>

struct Word
{
	char* word;
	uint16_t offset;
};

struct Word_List
{
	struct Word* words;
	uint16_t size;
	uint16_t count;
};

//utility functions

/*
Count the number of characters in the given character array
Returns length of character array
*/
uint32_t strlength( const char* const word );

/*
Copy the characters from the source array to the destination array 
    including the null terminated characters. Requires destination array large enough to store
    the number of characters from the source array.
*/
void strcopy( char* const destination, const char* const source );


//word functions

/*
Initializes the char* word to Null and the uint16_t to 0
*/
void init_word( struct Word *word );

/*
Checks if internal word char* is null and if not frees it. Allocates memory for pointer
    to store the given word. Calls utility functions to dynamically allocate array.
*/
void update_word( struct Word* word, const char* const wrd, uint16_t offset );

/*
Outputs the word in the given FILE*
*/
void display_word( FILE* out, struct Word word );

/*
Frees internal word pointer
*/
void free_word( struct Word word );


//word list functions

/*
Dynamically allocates tbw words pointer to an array of the given size,
    store the size and initialize the count
*/
void init_list( struct Word_List *word_list, uint16_t size );

/*
Adds the given struct Word in the array after the last element
Uses realloc if array fills up
*/
void add_word( struct Word_List *word_list, struct Word word );

/*
Copy of the word at the index is returned is index is valid,
    if not "Word Not Found" with offset 0 is returned
Returns copy of word at index
*/
struct Word get_word_at_index( struct Word_List word_list, uint16_t index );

/*
Loops over words in the list and frees each word, then frees the word array itself
*/
void free_list( struct Word_List word_list );

/*
Loops over each word in the list and displays each word
*/
void display_words( FILE* out, struct Word_List word_list );

#endif
