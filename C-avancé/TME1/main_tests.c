#include <stdio.h>
#include <stdlib.h>

#include "utilitaires_tableaux.h"
#include "tris.h"


int main(){
	int p_nbVal;
	int *t;
	t=lire_tableau("10_valeurs.txt",&p_nbVal);
	tri_minimum(t,p_nbVal);
	ecrire_tableau("test.txt",t,p_nbVal);
	afficher_tab(t,p_nbVal);
	return 0;

}
