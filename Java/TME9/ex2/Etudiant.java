import java.util.ArrayList;

public class Etudiant{
    
    private static ArrayList<Etudiant> tabEtudiant = new ArrayList<Etudiant>(); 
    
    private String nom;
    private int[] tabNote = new int[5];
    private int cpt=0;
    
    public Etudiant(String nom){
        this.nom = nom;
        tabEtudiant.add(this);
    }
    
    public String toString(){
     String s="";
     for (int i =0; i<cpt ;i++)
        s+= " "+ tabNote[i] +"/20";
     return nom+ " : " + s;
    }
    
    public void entrerNote(int note) throws TabNotesPleinException{
        if (cpt==5) throw new TabNotesPleinException("Le tableau de notes de "+ nom+" est plein");  
        tabNote[cpt] = note;
        cpt++;
    }
    
    public static ArrayList<Etudiant> getTabEtudiant(){
        return tabEtudiant;
    }    
    
}
