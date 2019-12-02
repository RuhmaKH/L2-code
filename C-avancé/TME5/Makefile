S3DIR="../TME3"
GCC_FLAGS=-Wall -g -I $(S3DIR)
LINK_FLAGS=-L $(S3DIR)
CC=gcc
EXE=ex_ab_entiers ex_ab_mots main_expr
LIB=libarbre_binaire.a

all: $(EXE) $(LIB)

%.o: %.c
	$(CC) $(GCC_FLAGS) -c $^

ex_ab_entiers: ex_ab_entiers.o arbre_binaire.o
	$(CC) $(GCC_FLAGS) $(LINK_FLAGS) -o $@ $^  -l fonctions_entiers	

ex_ab_mots: ex_ab_mots.o arbre_binaire.o
	$(CC) $(GCC_FLAGS)  $(LINK_FLAGS) -o $@ $^  -l fonctions_string	

main_expr: arbre_expr.o arbre_binaire.o main_expr.o
	$(CC) $(GCC_FLAGS)  $(LINK_FLAGS) -o $@ $^ 

lib%.a: %.c
	$(CC) $(GCC_FLAGS) $(GCC_LIB_FLAGS) -c -o $@ $^

clean:
	rm -f *.o $(EXE) $(LIB) *~ 
