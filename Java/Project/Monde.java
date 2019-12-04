import java.util.ArrayList;

public class Monde {
  private ArrayList<Item> listeItems;
  private int taille;

  public Monde(int taille){
    this.taille = taille;
    listeItems = new ArrayList<Item>();
  }

  public int getPositionAlea(){
    return (int) (Math.random() * taille);
  }

  public int getTaille(){
    return taille;
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
    int dis;
    for(Item i : listeItems)
      if(( dis = item.distance(i)) <= 2 && dis != 0)
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
        return nom.substring(0, 3) + nom.charAt(nom.length()-1);
    }
  }

  public void afficher(){
    Item i;
    String aff="    |";
    for ( int x = 0; x<taille; x++)
      aff +=  getNomCourts("" + x) + "|" ;

    aff += "\n";
    for ( int x = 0; x < taille; x++){
      aff += getNomCourts("" + x) + "|";
      for( int y = 0; y < taille; y++){
        if((i = chercher(x, y)) != null)
          aff += getNomCourts(i.getNom());
        else
          aff += "    ";
        aff += "|";
      }
      aff += "\n";
    }
  System.out.println(aff);
  }

}
