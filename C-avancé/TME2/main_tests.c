#include <assert.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ecosys.h"

#define NB_PROIES 2
#define NB_PREDATEURS 2

/* Parametres globaux de l'ecosysteme (externes dans le ecosys.h)*/
float p_ch_dir=0.01;
float d_proie=1;
float d_predateur=1;
float p_manger=0.2;
float p_reproduce=0.01;
float energie=500;


int main(void) {
	srand(time(NULL));
	
  Animal *liste_proie = NULL;
  Animal *liste_predateur = NULL;
  int nb_proies = 0, nb_predateurs = 0;
	int i;
	for(i=0;i<NB_PROIES;i++){
		ajouter_animal(rand ()% SIZE_X,rand ()% SIZE_Y, &liste_proie);
	}
	nb_proies = compte_animal_rec(liste_proie);
	printf("%d\n",nb_proies);
	for(i=0;i<NB_PREDATEURS;i++){
		ajouter_animal(rand ()% SIZE_X,rand ()% SIZE_Y, &liste_predateur);
	}
	nb_predateurs = compte_animal_rec(liste_predateur);
	printf("%d\n",nb_predateurs);
	
    afficher_ecosys(liste_proie,liste_predateur);  
    
    bouger_animaux(liste_predateur);
    bouger_animaux(liste_proie);
    afficher_ecosys(liste_proie,liste_predateur);
    
    reproduce(&liste_proie);
    reproduce(&liste_predateur);
    bouger_animaux(liste_predateur);
    bouger_animaux(liste_proie);
    afficher_ecosys(liste_proie,liste_predateur);
    
  return 0;
}
