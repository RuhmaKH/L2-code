CFLAGS := -g -Wall 

all:  tests_ecosys ecosys

tests_ecosys : main_tests.o ecosys.o
	gcc $(GCC_FLAGS) -o tests_ecosys main_tests.o ecosys.o
	
ecosys : main_ecosys.o ecosys.o
	gcc $(GCC_FLAGS) -o ecosys main_ecosys.o ecosys.o

main_tests.o : main_tests.c
	gcc $(GCC_FLAGS) -c main_tests.c

main_ecosys.o : main_ecosys.c
	gcc $(GCC_FLAGS) -c main_ecosys.c
	
ecosys.o : ecosys.c
	gcc $(GCC_FLAGS) -c ecosys.c

ecosys.c: ecosys.h

clean:
	rm -f *.o *~ $(PROGRAMS)
