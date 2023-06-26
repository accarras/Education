
CC          = gcc
CLINKER     = gcc
CCC         = g++
CCLINKER    = $(CCC)
INCLUDE_DIR = 
CFLAGS  = $(INCLUDE_DIR)
CCFLAGS = $(CFLAGS)
OPTFLAGS    = -g
LIB_PATH    =
LIB_LIST    = -lm
LIBS = $(LIB_PATH) $(LIB_LIST)

objects = pq.o job.o interface.o main.o

default: pqtest

pqtest: $(objects)
	g++ -g -o pqtest $(objects)

job.o : job.cc job.h
	g++ -c job.cc -o job.o

pq.o : pq.cpp pq.h job.h 
	g++ -c pq.cpp -o pq.o

interface.o : interface.cc pq.h
	g++ -c interface.cc -o interface.o

main.o : main.cc interface.h
	g++ -c main.cc -o main.o


clean :
	rm *.o
	rm pqtest



