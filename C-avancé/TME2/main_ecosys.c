#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ecosys.h"
#include <unistd.h>

#define NB_PROIES 20
#define NB_PREDATEURS 20
#define T_WAIT 800000


  /* Parametres globaux de l'ecosysteme (externes dans le ecosys.h)*/
float p_ch_dir=0.5;
float d_proie=1;
float d_predateur=1;
float p_manger=0.1;
float p_reproduce=0.2;
float energie=50;


int main(void) {
	srand(time(NULL));
	Animal *liste_proie = NULL;
  	Animal *liste_predateur = NULL;
  	int nb_proies = 0, nb_predateurs = 0;
	int i;
	// Création de la liste de proies
	for(i=0;i<NB_PROIES;i++){
		ajouter_animal(rand()%SIZE_X, rand()%SIZE_Y, &liste_proie);
	}
	nb_proies = compte_animal_rec(liste_proie);
	// Création de la liste de prédateurs
	for(i=0;i<NB_PREDATEURS;i++){
		ajouter_animal(rand()%SIZE_X, rand()%SIZE_Y, &liste_predateur);
	}
	nb_predateurs = compte_animal_rec(liste_predateur);
	//Rafraichissement proies
	while(nb_proies>0 && nb_predateurs>0){
		clear_screen();
		afficher_ecosys(liste_proie,liste_predateur);
		rafraichir_proies(&liste_proie);
		rafraichir_predateurs(&liste_predateur,&liste_proie);
		usleep(T_WAIT);
	}

  return 0;
}

