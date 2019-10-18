#include <stdio.h>

#include "utilitaires_tableaux.h"

void tri_minimum(int *tab, int n){
	int k,i,j;
	i=0;
	k=0;
	int boolean=0;
	int temp=tab[0];
	int t;
	for (j=0;j<n-1;j++){
		for (i=j;i<n-1;i++){
			if(tab[i+1]<temp){
				temp=tab[i+1];
				k=i+1;
				boolean=1;
			}
		}
		if (boolean==1){
			t=tab[j];
			tab[j]=temp;
			tab[k]=t;
			boolean =0;
		}
		temp=tab[j+1];
	}	
}

void bulle(int *tab,int n){
	int i;
	int temp;
	int t=1;
	while (t!=0){
		t=0;
		for (i=0;i<n-1;i++){
			if (tab[i]>tab[i+1]){
				temp=tab[i];
				tab[i]= tab[i+1];
				tab[i+1]=temp;
				t++;
			}
		}
	}
}
