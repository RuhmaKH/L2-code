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

NdMiMa_t *Construire_arbre(int plateau[H][H], int prof, int couleurQuiJoue)
	{
	/*A Completer*/
	return NULL;
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
