typedef struct _LettreFrequence {
    char lettre;
    unsigned int nb_occ;
} LettreFrequence;

int* disti_code (FILE* f){
    int i;
    char c;
    int* tab = (int*) malloc(256 * sizeof(int));
    if (! tab){
        fprintf(f,"Erreur malloc`\n");
        return NULL;
    }
    for (i = 0; i < 256; i++){
        tab[i] = 0;
    }
    while ((c = f.getc()) != EOF){
        tab[c]++;
    }
    return tab;
}

PArbreBinaire cree_arbre_huffman (char lettre, unsigned nb_occ){
    ParbreBinaire pab = creer_arbre(1, NULL, dupliquer_lf, copier_lf, detruire_lf, afficher_lf, comparer_lf, ecrire_lf, lire_lf);
    LettreFrequence lf = {lettre, nb_occ};
    pab->racine = creer_noeud_binaire(pab, &lf);
    return pab;
}

PListe cree_liste_occ (int* occ) {
    PListe* pl = (PListe) malloc(sizeof(Liste));
    pl->element = NULL;
    pl->dupliquer = dupliquer_ah;
    pl->copier = copier_ah;
    pl->detruire = detruire_ah;
    pl->afficher = afficher_ah;
    pl->comparer = comparer_ah;
    pl->ecrire = ecrire_ah;
    pl->lire = lire_ah;

    int i;
    PArbreBinaire pab = cree_arbre_huffman(0, 0);
    for (i = 0; i < 256; i++){
        if (occ[i] > 0){
            LettreFrequence* plf = (LettreFrequence*) pab->racine->data;
            plf->lettre = i;
            plf->nb_occ = occ[i];
            inserer_place(pl, pab);
        }
    }
}