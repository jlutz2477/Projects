#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include "word.h"


/*
Reads the first two bytes of data, makes word list to store words, reads the next two bytes and sotres in offset.
while offset not 0: reads the number of bytes in the word, 
                    reads the next offset, creates memory for letters, makes word struct object,
                    updates word struct object with word and offset of word,
                    adds word to list of words, updates offset to be next offset
Returns list of words
*/
struct Word_List clear_unscramble(uint8_t* data);


/*
Reads the first two bytes of data, makes word list to store words, reads the next two bytes and sotres in offset.
while offset not 0: reads the number of bytes in the word, 
                    reads the next offset, creates memory for letters, makes word struct object,
                    flips the nybbles on the length of the record, 
                    blends the flipped length with each letter in the word,
                    updates word struct object with word and offset of word,
                    adds word to list of words, 
                    uses unfuzzed first letter in word to make two byte mask,
                    use mask to xor the next offset to unfuzz it,
                    updates offset to be next offset
Returns list of words
*/
struct Word_List fuzzy_unscramble(uint8_t* data);

