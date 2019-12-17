public class MainEtu{

    public static void main(String[] args){
        int note;
        Etudiant etu = null;
        for( String s : args){
            try{
                note = Integer.parseInt(s);
                //System.out.println(s+" C'est une note !");
                etu.entrerNote(note);
            }catch(NumberFormatException e){
                //System.out.println(s +" C'est un nom !");
                etu = new Etudiant(s);
            }catch(TabNotesPleinException e){
                System.out.println(e.getMessage());
            }
        }
        
        for (Etudiant e : Etudiant.getTabEtudiant()){
            System.out.println(e);
        }
       
    }
}

