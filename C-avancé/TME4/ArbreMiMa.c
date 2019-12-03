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

NdMiMa_t *Construire_arbre(int plateau[H][H], int prof, int couleurQuiJoue){
	if(prof<=0 || Partie_terminee(plateau))
		return NULL;
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* racine =(NdMiMa_t *) malloc(sizeof(NdMiMa_t));
	racine->Couleur = couleurQuiJoue;
	racine->liste_pos = Trouver_liste_pos_jouables(plateau,couleurQuiJoue);
	racine->JoueurBloque=NULL;
	if(!racine->liste_pos){
		racine->JoueurBloque=Construire_arbre(plateau_bis,prof--,couleurQuiJoue==NOIR ? BLANC : NOIR);
		return racine;
	}
	PosJouable_t* cell = racine->liste_pos;
	while(cell){
		Jouer_pion(plateau_bis,cell->i,cell->j,couleurQuiJoue);
		cell->Nd = Construire_arbre(plateau_bis,prof--,couleurQuiJoue==NOIR ? BLANC : NOIR);
		cell = cell->suiv;
	}
	return racine;
}

int MinMax(NdMiMa_t *arbre, int plateau[H][H], int (*EvaluerPlateau)(int plateau[H][H]))
	{
	int res;
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* cell = arbre;
	PosJouable_t* pos = NULL;
	do{
		if(cell->liste_pos)
			pos  = cell->liste_pos;
		else
			cell = cell->JoueurBloque;
	}while(pos==NULL);

	if(arbre->Couleur==NOIR){
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=NOIR;
			if(pos->Nd)
				res = (res > MinMax(pos->Nd,plateau_bis,EvaluerPlateau))? res : MinMax(pos->Nd,plateau_bis,EvaluerPlateau);
			plateau_bis[pos->i][pos->j]=VIDE;
			pos = pos->suiv;
		}
	}
	else{
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=BLANC;
			if(pos->Nd)
				res = (res < MinMax(pos->Nd,plateau_bis,EvaluerPlateau))? res : MinMax(pos->Nd,plateau_bis,EvaluerPlateau);
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
	do{
		if(cell->liste_pos)
			pos  = cell->liste_pos;
		else
			cell = cell->JoueurBloque;
	}while(pos==NULL);

	if(arbre->Couleur==NOIR){
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=NOIR;
			if(res > (k = MinMax(pos->Nd,plateau_bis,EvaluerPlateau)) ){
				res = k;
				*pi = pos->i;
				*pj = pos->j;
			}
			plateau_bis[pos->i][pos->j]=VIDE;
			pos = pos->suiv;
		}
	}
	else{
		while(pos->suiv){
			plateau_bis[pos->i][pos->j]=BLANC;
			if(res < (k = MinMax(pos->Nd,plateau_bis,EvaluerPlateau)) ){
				res = k;
				*pi = pos->i;
				*pj = pos->j;
			}
			plateau_bis[pos->i][pos->j]=VIDE;
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
