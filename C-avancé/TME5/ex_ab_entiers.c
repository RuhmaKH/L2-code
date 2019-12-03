#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "arbre_binaire.h"
#include "fonctions_entiers.h"

int main(void) {
  PArbreBinaire abi = creer_arbre(1, dupliquer_int, copier_int, detruire_int, afficher_int, comparer_int, ecrire_int, lire_int);

  int x = rand()%100;
  ajouter_abr(abi,&x);

  int i;
  for(i=0;i<10;i++){
    x = rand()%100;
    ajouter_abr(abi,&x);
  }

  ecrire_ab(abi,"abi.txt");

  PArbreBinaire abiBis = creer_arbre(1, dupliquer_int, copier_int, detruire_int, afficher_int, comparer_int, ecrire_int, lire_int);
  lire_abr(abiBis,"abi.txt");

  afficher_ab_prefixe(abiBis);

  detruire_ab(abi);
  detruire_ab(abiBis);
  return 0;
}
