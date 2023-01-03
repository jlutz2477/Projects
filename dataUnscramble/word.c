#include "word.h"
#include <assert.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>

//utility functions


/*
Count the number of characters in the given character array
Returns length of character array
*/
uint32_t strlength( const char* const word )
{
    uint32_t len = 0;
    for (int i=0; *(word + i) != '\0'; i++) {
        len++;
    }
    return len;
}


/*
Copy the characters from the source array to the destination array 
    including the null terminated characters. Requires destination array large enough to store
    the number of characters from the source array.
*/
void strcopy( char* const destination, const char* const source ) 
{
    int i = 0;
    while(*(source + i) != '\0') {
        *(destination + i) = *(source + i);
        i++;
    }
    *(destination + i) = '\0';
}


//word functions


/*
Initializes the char* word to Null and the uint16_t to 0
*/
void init_word( struct Word *word )
{
    word->word = NULL;
    word->offset = 0;
}


/*
Checks if internal word char* is null and if not frees it. Allocates memory for pointer
    to store the given word. Calls utility functions to dynamically allocate array.
*/
void update_word( struct Word* word, const char* const wrd, uint16_t offset )
{
    if (word->word != NULL) {
        free(word->word);
    }
    uint32_t strlen = strlength(wrd) + 1;
    word->word = (char*)malloc(strlen * sizeof(char));
    strcopy(word->word, wrd);
    word->offset = offset;
}


/*
Outputs the word in the given FILE*
*/
void display_word( FILE* out, struct Word word )
{
    fprintf(out, "%-20s @ Offset: %02x\n",word.word,word.offset);
}

/*
Frees internal word pointer
*/
void free_word( struct Word word ) {
    if (word.word != NULL) {
        free(word.word);
        word.word = NULL;
    }
}



//word list functions


/*
Dynamically allocates tbw words pointer to an array of the given size,
    store the size and initialize the count
*/
void init_list( struct Word_List *word_list, uint16_t size ) {
    word_list->words = (struct Word*)malloc(size * sizeof(struct Word));
    word_list->size = size;
    word_list->count = 0;
}

/*
Adds the given struct Word in the array after the last element
Uses realloc if array fills up
*/
void add_word( struct Word_List *word_list, struct Word word ) {
    if (word_list->size == word_list->count) {
        word_list->size *= 2;
        word_list->words = realloc(word_list->words, (word_list->size) * sizeof(struct Word));
    }
    //update_word((word_list->words + word_list->count),word.word,strlength(word.word));
    word_list->words[word_list->count] = word;
    (word_list->count)++;
}


/*
Copy of the word at the index is returned is index is valid,
    if not "Word Not Found" with offset 0 is returned
Returns copy of word at index
*/
struct Word get_word_at_index( struct Word_List word_list, uint16_t index ) {
    struct Word word;
    init_word(&word);
    if (index > word_list.count) {
         update_word(&word, "Word Not Found",0);
         return word;
    }
    update_word(&word,(word_list.words + index)->word, (word_list.words + index)->offset);
    return word;
}

/*
Loops over words in the list and frees each word, then frees the word array itself
*/
void free_list( struct Word_List word_list ) {
    if ((word_list.words + 0)->word != NULL) {
        for(int i=0;i<word_list.count;i++) {
            free_word(*(word_list.words + i));
        }
    }
    if (word_list.words != NULL) {
        free(word_list.words);
        word_list.words = NULL;
    }
}


/*
Loops over each word in the list and displays each word
*/
void display_words( FILE* out, struct Word_List word_list ) {
    for(int i=0;i<word_list.count;i++) {
        display_word(out, *(word_list.words + i));
    }
}