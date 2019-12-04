import java.util.ArrayList;

public class Monde {
  private ArrayList<Item> listeItems;
  private int taille;

  public Monde(int taille){
    this.taille = taille;
    listeItems = new ArrayList<Item>();
  }

  public int getPositionAlea(){
    return (int) Math.random() * taille;
  }

  public void ajouterItem(Item item){
    item.setX(getPositionAlea());
    item.setY(getPositionAlea());
    listeItems.add(item);
  }

  public void supprimerItem(Item item){
    listeItems.remove(item);
    item.setX(-1);
    item.setY(-1);
  }

  public Item chercher(int x,int y){
    for(Item i : listeItems)
      if(i.getX() == x && i.getY() == y)
        return i;

    return null;
  }

  public ArrayList<Item> getVoisins(Item item){
    ArrayList<Item> voisins = new ArrayList<Item>();
    for(Item i : listeItems)
      if(item.distance(i) <= 2)
        voisins.add(i);

    return voisins;
  }

  private static String getNomCourts(String nom){
    switch (nom.length()){
      case 1 :
        return "  " + nom + " ";
      case 2 :
        return " " + nom + " ";
      case 3 :
        return nom + " ";
      default :
        return nom.substring(0, 4);
    }
  }

  public void afficher(){
    
  }

}
