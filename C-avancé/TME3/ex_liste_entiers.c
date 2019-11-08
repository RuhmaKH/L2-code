
#include <stdlib.h>
#include <assert.h>
#include "liste.h"
#include "devel.h"
#include "fonctions_entiers.h"


int main(void) {
  PListe liste=(PListe)malloc (sizeof (Liste));
  PListe pliste=(PListe)malloc (sizeof (Liste));
  int d1 = 10;
  int d2 = 5;
  int d3 = 7;
  liste->dupliquer=dupliquer_int;
  liste->copier=copier_int;
  liste->detruire=detruire_int;
  liste->afficher=afficher_int;
  liste->comparer=comparer_int;
  liste->ecrire=ecrire_int;
  liste->lire=lire_int;

  pliste->dupliquer=dupliquer_int;
  pliste->copier=copier_int;
  pliste->detruire=detruire_int;
  pliste->afficher=afficher_int;
  pliste->comparer=comparer_int;
  pliste->ecrire=ecrire_int;
  pliste->lire=lire_int;
  inserer_debut(liste,&d2);
  inserer_fin(liste,&d1);
  inserer_place(liste,&d3);
  afficher_liste(liste);
  assert(chercher_liste(liste,&d1));
  assert(chercher_liste_triee(liste,&d3));
  assert(ecrire_liste(liste,"liste_i.txt")==1);
  detruire_liste(liste);
  assert(lire_liste(pliste,"liste_i.txt")==1);
  afficher_liste(pliste);
  detruire_liste(pliste);
  return 0;
}
