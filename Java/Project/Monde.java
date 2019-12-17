import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


public class Monde extends JPanel{
  private static final long serialVersionUID = 1L;
  private static ArrayList<Item> listeItems;
  private static ArrayList<Creature> listeCreature;
  public static final int taille = 30;
  public static final int tailleCase = 30;
  public final static Monde world = new Monde();
  private static final Image imageHerbe = Images.getImage("Herbe");
  private Image imageredcursor = Images.getImage("Redcursor");

  private Monde(){
    setPreferredSize(new Dimension(taille*tailleCase , taille*tailleCase));
    listeItems = new ArrayList<Item>();
    listeCreature = new ArrayList<Creature>();
    initialize();
  }

  private static int getPositionAlea(){
    return (int) (Math.random() * taille);
  }

  private void initialize(){
    //############# Arbre #############
    for (int j = 0; j < taille; j++)
      ajouterItemAtCoord(new Arbre(0,j));
    for (int j = 1; j < taille; j++)
      ajouterItemAtCoord(new Arbre(j,0));
    for (int j = 1; j < taille; j++)
      ajouterItemAtCoord(new Arbre(taille-1,j));
    for (int j = 1; j < taille-1; j++)
      ajouterItemAtCoord(new Arbre(j,taille-1));
    //############# ArbreMagique #############
    for (int j = 0; j < Math.random() * (taille / 2) + taille / 2; j++)
      ajouterItem(new ArbreMagique());

    //############# Creature #############
    Creature creature;
    for (int j = 0; j < Math.random() * (taille / 2) + 6; j++){
      creature = new Creature();
      listeCreature.add(creature);
      ajouterItem(creature);
      ajouterCreatureOP(new Sonic());
    }
    ajouterCreatureOP(new Gobelin());
    //for (int j = 0; j < Math.random() * (taille / 4) + taille / 2; j++)
    //  ajouterItem(new Ryuk());
    //############# Coffre #############
    for (int j = 0; j < Math.random() * (taille / 2) + taille / 2; j++)
      ajouterItem(new Coffre());
    //############# Accessoire #############
    for (int j = 0; j < Math.random() * (taille / 4) + taille / 4; j++)
      ajouterAcc(new Sac());
    for (int j = 0; j < Math.random() * (taille / 4) + taille / 2; j++)
      ajouterAcc(new Pomme());
    for (int j = 0; j < Math.random() * (taille / 4) + taille / 6; j++)
      ajouterAcc(new Pills());
    ajouterAcc(new LivreMagique());
    ajouterAcc(new Epee());
    //############# Magasin #############
    ajouterItem(new Fruitier());
  }

  public static void deplacerCreature(){
    for ( Creature c : listeCreature)
      c.seDeplacer();
  }


  public static void ajouterItem (Item item) {
    do {
      item.setX(getPositionAlea());
      item.setY(getPositionAlea());
    } while (chercher(item.getX(), item.getY()) != null);
    listeItems.add(item);
  }

  public static void ajouterItemAtCoord(Item item){
    if (chercher(item.getX(), item.getY()) == null)
      listeItems.add(item);
    else
      ajouterItem(item);
  }

  public static void ajouterCreatureOP(Item chanceoupaschance){
    ArrayList<ArbreMagique> listeArbreMagique = new ArrayList<ArbreMagique>();
    for (Item item : listeItems)
      if (item instanceof ArbreMagique)
        listeArbreMagique.add((ArbreMagique) item);
    ( listeArbreMagique.get( (int) (Math.random() * listeArbreMagique.size()) ) ).ajouter(chanceoupaschance);
  }

  private static void ajouterAcc (Acc acc) {
    ArrayList<Coffre> coffres = new ArrayList<Coffre>();
    for (Item item : listeItems)
      if (item instanceof Coffre)
        coffres.add((Coffre) item);
    ( coffres.get( (int) (Math.random() * coffres.size()) ) ).ajouter(acc);
  }

  public static void supprimerItem(Item item){
    listeItems.remove(item);
    item.setX(-1);
    item.setY(-1);
  }

  public static void drop (Item item, int x, int y) {
    for (int i = x - 1; i <= x + 1; i++)
      for (int j = y - 1; j <= x + 1; j++)
        if (chercher(i, j) == null){
          item.setX(i);
          item.setY(j);
          listeItems.add(item);
          return;
        }
  }

  public static Item chercher (int x,int y) {
    for(Item i : listeItems)
      if(i.getX() == x && i.getY() == y)
        return i;
    return null;
  }

  public static ArrayList<Item> getVoisins(Item item){
    ArrayList<Item> voisins = new ArrayList<Item>();
    for(Item i : listeItems)
      if(item.distance(i) <= 2 && i != item)
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

  public static void afficher(){
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

  public void dessinerMap(Graphics g){
    int longueur = getWidth();
    int hauteur = getHeight();
    for (int i = 0; i < longueur / 6; i++)
      for (int j = 0; j < hauteur / 6; j++)
        g.drawImage( imageHerbe, i * tailleCase * 5, j * tailleCase * 5, tailleCase * 5, tailleCase * 5, this) ;
  }

  public void paintComponent(Graphics g){
    dessinerMap(g);
		for(Item itemVoisin : listeItems)
			if( itemVoisin != null)
        itemVoisin.dessiner(g);
    if (Interact.getState() == "talk" || Interact.getState() == "shop"){
      Interact.dessinerTalk(g);
    }

    Avatar currentPlayer = Jeu.getCurrPlay();
    g.drawImage( imageredcursor, currentPlayer.getX()*tailleCase +7  , currentPlayer.getY()*tailleCase -17  , 20 , 20 , this);

  }
}
