#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "devel.h"
#define BUF 1024
void *dupliquer_str(const void *src) {
  char *ssrc=(char *)src;
  char *sdst=strdup(ssrc);
  if (sdst==NULL) {
    affiche_message("Erreur d'allocation");
    return NULL;
  }
  return (void *)sdst;
}

void copier_str(const void *src, void *dst) {
  char* ssrc = (char *)src;
  char* sdst = (char *)dst;
  strcpy(sdst,ssrc);
}

void detruire_str(void *data) {
  free(data);
}

void afficher_str(const void *data) {
  char* sdata = (char*)data;
  printf("%s",sdata);
}

int comparer_str(const void *a, const void *b) {
  char* sa = (char*)a;
  char* sb = (char*)b;
  return strcmp(sa,sb);
}

int ecrire_str(const void *data, FILE *f) {
  const char* sd = (char*)data;
  return fprintf(f,"%s",sd);
}

void * lire_str(FILE *f) {
  char* s=(char*)(malloc(BUF*sizeof(char)));
  int r = fscanf(f,"%s",s);
  if(r<1) return NULL;
  return s;
}
