#include <stdlib.h>
#include <stdio.h>
#include "ArbreMiMa.h"
#include "ListePos.h"

int EvaluerPlateau_0(int plateau[H][H])
	{
	int i,j;
	int res =0;
	/*A Completer*/
	return res;
	}

NdMiMa_t *Construire_arbre(int plateau[H][H], int prof, int couleurQuiJoue){
	if(prof<=0 || Partie_terminee(plateau))
		return NULL;
	int plateau_bis[H][H];
	Copier_plateau(plateau,plateau_bis);
	NdMiMa_t* racine = malloc(sizeof(NdMiMa_t));
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
	/*A Completer*/
	int min 0;
	return min;
	}

int MeilleurPos(NdMiMa_t *arbre, int plateau[H][H], int (*EvaluerPlateau)(int plateau[H][H]),int *pi, int *pj)
	{
	*pi = 0;
	*pj = 0;

	/*A Completer*/
	return 0;
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
