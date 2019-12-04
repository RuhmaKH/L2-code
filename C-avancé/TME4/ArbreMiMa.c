#include <stdlib.h>
#include <stdio.h>
#include "ArbreMiMa.h"
#include "ListePos.h"

int EvaluerPlateau_0(int plateau[H][H])
	{
	int i,j;
	int res =0;
	for(i=0;i<H;i++){
		for(j=0;j<H;j++){
				if(plateau[i][j]==NOIR)
					res++;
				if(plateau[i][j]==BLANC)
					res--;
		}
	}
	return res;
	}
int k=1;
NdMiMa_t *Construire_arbre(int plateau[H][H], int prof, int couleurQuiJoue){
	if(prof<=0 || Partie_terminee(plateau)){
		return NULL;
	}
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* racine = (NdMiMa_t*) malloc(sizeof(NdMiMa_t));
	racine->Couleur = couleurQuiJoue;
	racine->liste_pos = Trouver_liste_pos_jouables(plateau, couleurQuiJoue);
	racine->JoueurBloque = NULL;
	if(!racine->liste_pos){
		racine->JoueurBloque = Construire_arbre(plateau_bis, prof--, Autre_joueur(couleurQuiJoue));
		return racine;
	}
	PosJouable_t* cell = racine->liste_pos;
	while(cell->suiv){
		plateau_bis[cell->i][cell->j] = couleurQuiJoue;
		cell->Nd = Construire_arbre(plateau_bis, prof-1, Autre_joueur(couleurQuiJoue));
		plateau_bis[cell->i][cell->j] = VIDE;
		cell = cell->suiv;
	}
	return racine;
}

int MinMax(NdMiMa_t *arbre, int plateau[H][H], int (*EvaluerPlateau)(int plateau[H][H])){
	if(!arbre){
		return EvaluerPlateau(plateau);
	}
	int res;
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* cell = arbre;
	PosJouable_t* pos = NULL;
	while(pos==NULL){
		if(cell->liste_pos)
			pos  = cell->liste_pos;
		else
			cell = cell->JoueurBloque;
	}
	fprintf(stderr,"NO BUG UNTIL THERE\n");

	if(arbre->Couleur==NOIR){
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=NOIR;
			if(pos->Nd){
				k = MinMax(pos->Nd,plateau_bis,EvaluerPlateau);
				res = (res > k ? res : k);
			}
			plateau_bis[pos->i][pos->j]=VIDE;
			pos = pos->suiv;
		}
	}
	else{
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=BLANC;
			if(pos->Nd){
				k = MinMax(pos->Nd,plateau_bis,EvaluerPlateau);
				res = (res < k ? res : k);
			}
			plateau_bis[pos->i][pos->j]=VIDE;
			pos = pos->suiv;
		}
	}

	return res;
}


int MeilleurPos(NdMiMa_t *arbre, int plateau[H][H], int (*EvaluerPlateau)(int plateau[H][H]),int *pi, int *pj)
{
	int k;
	int res;
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* cell = arbre;
	PosJouable_t* pos = NULL;
	while(pos == NULL){
		if(cell->liste_pos)
			pos  = cell->liste_pos;
		else
			cell = cell->JoueurBloque;
	}
	if(arbre->Couleur == NOIR){
		while(pos->suiv){
			plateau_bis[pos->i][pos->j] = NOIR;
			if(res > (k = MinMax(pos->Nd, plateau_bis, EvaluerPlateau)) ){
				res = k;
				*pi = pos->i;
				*pj = pos->j;
			}
			plateau_bis[pos->i][pos->j] = VIDE;
			pos = pos->suiv;
		}
	}
	else{
		while(pos->suiv){
			plateau_bis[pos->i][pos->j] = BLANC;
			if(res < (k = MinMax(pos->Nd, plateau_bis, EvaluerPlateau)) ){
				res = k;
				*pi = pos->i;
				*pj = pos->j;
			}
			plateau_bis[pos->i][pos->j] = VIDE;
			pos = pos->suiv;
		}
	}
	return res;
}

NdMiMa_t *Detruire_arbre(NdMiMa_t *arbre)
	{
	if (arbre)
		{
		Detruire_liste(arbre->liste_pos);
		free(arbre);
		}
	return NULL;
	}

void afficher_arbre(NdMiMa_t* arbre){
	NdMiMa_t* a = arbre;
	fprintf(stderr,"Noeud\n");
	if(! arbre){
		fprintf(stderr,"Arbre Vide\n");
		return;
	}
	fprintf(stderr,"Couleur %d\n",a->Couleur);
	if(a->liste_pos){
		Afficher_lPosJouables(a->liste_pos);

	}
	else
		fprintf(stderr,"Pas de pos\n");
	if(a->JoueurBloque){

	}
	else
		fprintf(stderr,"Joueur Pas Bloqué\n");
}
