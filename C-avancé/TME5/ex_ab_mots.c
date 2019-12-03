#include <stdlib.h>
#include <stdio.h>
#include "arbre_binaire.h"
#include "fonctions_string.h"

int main(void) {
  PArbreBinaire abm = creer_arbre(1, dupliquer_str, copier_str, detruire_str, afficher_str, comparer_str, ecrire_str, lire_str);

  lire_abr(abm, "french_za_reordered");
  ecrire_ab_map_infixe(abm, "abm.txt");

  detruire_ab(abm);
  return 0;
}
