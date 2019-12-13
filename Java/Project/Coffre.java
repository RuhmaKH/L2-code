import java.util.ArrayList;

public class Coffre extends Item {
    private ArrayList<Item> contenu;

    public Coffre(){
        super("Coffre");
        contenu = new ArrayList<Item>();
        if (Math.random() < 0.125)
            ajouter(new Tresor());
    }

    public void ajouter(Item item){
        contenu.add(item);
    }

    public ArrayList<Item> ouvrir(){
        ArrayList<Item> tab = contenu;
        contenu = new ArrayList<Item>();
        return tab;
    }

    public ArrayList<Item> getContenu(){
        return contenu;
    }
}