public class MonTableau{

    private int[] tab;
    private int lgReelle = 0;
    
    public MonTableau(int taille){
        tab = new int[taille];
    }
    
    public void ajouter(int n) throws TabPleinException{
        if(lgReelle == tab.length) throw new TabPleinException("MonTableauOutOfRange@Index : "+ lgReelle);
        tab[lgReelle] = n;
        lgReelle++;
    }
    
    
    
}
