import java.util.ArrayList;

public class Coffre extends Item {
    private ArrayList<Item> contenu;

    public Coffre(){
        super("Coffre");
        contenu = new ArrayList<Item>();
    }

    public void ajouter(Item item){
        contenu.add(item);
    }

    public ArrayList<Item> getContenu(){
        return contenu;
    }
}