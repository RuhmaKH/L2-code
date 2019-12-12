import java.util.ArrayList;

public class Coffre extends Item {
    private ArrayList<Acc> contenu;

    public Coffre(){
        super("Coffre");
        contenu = new ArrayList<Acc>();
    }

    public void ajouter(Acc acc){
        contenu.add(acc);
    }

    public ArrayList<Acc> getContenu(){
        return contenu;
    }
}