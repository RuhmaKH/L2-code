#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "devel.h"

void *dupliquer_str(const void *src) {
  char *ssrc=(char *)src;
  char *sdst=malloc(sizeof(char));
  if (sdst==NULL) {
    affiche_message("Erreur d'allocation");
    return NULL;
  }
  sdst = ssrc;
  return (void *)sdst;
}

void copier_str(const void *src, void *dst) {
  char* ssrc = (char *)src;
  char* sdst = (char *)dst;
  *sdst = *ssrc;
}

void detruire_str(void *data) {

}

void afficher_str(const void *data) {
  char* sdata = (char*)data;
  printf("%s",sdata);
}

int comparer_str(const void *a, const void *b) {
  char* sa = (char*)a;
  char* sb = (char*)b;
  while(*sa!='\0' && *sb!='\0'){
    if(*sa!=*sb){
      return 0;
    }
    sa++;
    sb++;
  }
  return 1;
}

int ecrire_str(const void *data, FILE *f) {
  const char* sd = (char*)data;
  return fprintf(f,"%s",sd);
}

void * lire_str(FILE *f) {
  char* s=(char*)(malloc(sizeof(char)));
  int r = fscanf(f,"%s",s);
  if(r<1) return NULL;
  char* ps=(char*)(malloc(sizeof(char)));
  ps = s;
  return ps;
}
