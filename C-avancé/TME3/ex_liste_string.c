
#include <stdlib.h>
#include "liste.h"
#include "devel.h"
#include "fonctions_string.h"
#include <assert.h>

int main(void) {
  PListe liste=(PListe)malloc (sizeof (Liste));
  PListe pliste=(PListe)malloc (sizeof (Liste));
  char* d1 = "aaa";
  char* d2 = "bbb";
  char* d3 = "ccc";
  liste->dupliquer=dupliquer_str;
  liste->copier=copier_str;
  liste->detruire=detruire_str;
  liste->afficher=afficher_str;
  liste->comparer=comparer_str;
  liste->ecrire=ecrire_str;
  liste->lire=lire_str;

  pliste->dupliquer=dupliquer_str;
  pliste->copier=copier_str;
  pliste->detruire=detruire_str;
  pliste->afficher=afficher_str;
  pliste->comparer=comparer_str;
  pliste->ecrire=ecrire_str;
  pliste->lire=lire_str;

  inserer_debut(liste,d1);
  inserer_fin(liste,d2);
  inserer_place(liste,d3);
  afficher_liste(liste);

  assert(chercher_liste(liste,d1));
  assert(chercher_liste_triee(liste,d3));
  assert(ecrire_liste(liste,"liste_s.txt")==1);
  detruire_liste(liste);
  assert(lire_liste(pliste,"liste_s.txt")==1);
  afficher_liste(pliste);
  detruire_liste(pliste);
  printf("%d",comparer_str(d1,d2));


  return 0;
}
