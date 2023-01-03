#include "word.h"
#include "unscramble.h"
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>

/*
Reads the first two bytes of data, makes word list to store words, reads the next two bytes and sotres in offset.
while offset not 0: reads the number of bytes in the word, 
                    reads the next offset, creates memory for letters, makes word struct object,
                    updates word struct object with word and offset of word,
                    adds word to list of words, updates offset to be next offset
Returns list of words
*/
struct Word_List clear_unscramble(uint8_t* data) {

    uint16_t numWords = *(uint16_t*)(data);
    uint16_t offset = *(uint16_t*)(data + 2);

    struct Word_List wl;
    init_list(&wl, numWords);

    while (offset != 0) {
        uint8_t numBytes = *(data + offset);
        uint16_t nextOff = *(uint16_t*)(data + offset + 1);
        char* temp = calloc(numBytes - 2, sizeof(char));
        
        for (int i=0;i<numBytes - 3;i++) {
            temp[i] = *(data + offset + i + 3);
        }

        struct Word word;
        init_word(&word);

        update_word(&word, temp, offset+3);

        add_word(&wl, word);

        offset = nextOff;

        free(temp);
    }

    return wl;

}



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
struct Word_List fuzzy_unscramble(uint8_t* data) {
    uint16_t numWords = *(uint16_t*)(data);
    uint16_t offset = *(uint16_t*)(data + 2);

    struct Word_List wl;
    init_list(&wl, numWords);

    while (offset != 0) {
        uint8_t numBytes = *(data + offset);
        uint16_t nextOff = *(uint16_t*)(data + offset + 1);
        char* temp = calloc(numBytes - 2, sizeof(char));
        for (int i=0;i<numBytes - 3;i++) {
            temp[i] = *(data + offset + i + 3);
        }

        struct Word word;
        init_word(&word);

        // flip the nybbles on the length of the record
        uint8_t temp1 = *(data + offset) << 4;
        uint8_t temp2 = *(data + offset) >> 4;
        uint8_t temp3 = temp1 | temp2;

        // Blend the flipped length with each letter in the word
        for (int i=0;i<numBytes - 3;i++) {
            temp[i] = (char)(*(data + offset + i + 3) ^ temp3);
        }

        update_word(&word, temp, offset+3);

        add_word(&wl, word);

        // use the unfuzzed first letter in the word to make a two byte mask
        uint16_t Mask = temp[0];
        uint16_t Mask2 = Mask << 8;
        Mask = Mask | Mask2;

        // use this two byte mask to xor the next offset to unfuzz the next offset
        nextOff = Mask ^ *(uint16_t*)(data + offset + 1); 

        offset = nextOff;

        free(temp);
    }

    return wl;

}