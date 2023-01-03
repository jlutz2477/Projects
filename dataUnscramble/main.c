#include "word.h"
#include "unscramble.h"
#include <stdio.h>

#define COMMAND_SIZE 50


int main(int argc, char **argv)
{
	if ( argc != 3 )
	{
		fprintf(stderr, "Usage: %s <input file> <output file>\n", argv[0] );
		exit(1);
	}
	
	FILE* in = fopen( argv[1], "r" );
	FILE *out = fopen( argv[2], "w" );

	uint16_t size;
	char file_name[COMMAND_SIZE];
	char command[COMMAND_SIZE];

	fscanf(in, "%hu\n", &size);
	fscanf(in, "%s\n", file_name);
	fscanf(in, "%s\n", command);

	fprintf(out, "Bytes in file:\t%hhu\n",size);
	fprintf(out, "Name of file:\t%s\n",file_name);
	fprintf(out, "Clear|Fuzzy:\t%s\n",command);

	uint8_t* data = (uint8_t*)malloc(sizeof(uint8_t)*size);

	FILE* binary_file = fopen(file_name, "rb");

	fread(data, sizeof(uint8_t), size, binary_file);

	if (command[0] == 'c') {
		struct Word_List word_list;
		word_list = clear_unscramble(data);
		display_words(out, word_list);
		free_list(word_list);
	}

	else if (command[0] == 'f') {
		struct Word_List word_list;
		word_list = fuzzy_unscramble(data);
		display_words(out, word_list);
		free_list(word_list);
	}

	fclose(in);
	fclose(out);
	
	free(data);
	fclose(binary_file);
	
	return 0;
}
