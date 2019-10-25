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
  *sdst = *ssrc;
  return (void *)sdst;
}

void copier_str(const void *src, void *dst) {
  char* ssrc = (char *)src;
  char* sdst = (char *)dst;
  *sdst = *ssrc;
}

void detruire_str(void *data) {
  free(data);
}

void afficher_str(const void *data) {
  char* sdata = (char*)data;
  printf("%c",*sdata);
}

int comparer_str(const void *a, const void *b) {
  /* a completer */
  return 0; // pour que cela compile
}

int ecrire_str(const void *data, FILE *f) {
  /* a completer */
  return 0; // pour que cela compile
}

void * lire_str(FILE *f) {
  /* a completer */
  return NULL; // pour que cela compile
}
