import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class Monde extends JPanel{
  private static ArrayList<Item> listeItems;
  public static final int taille = 30;
  public static final int tailleCase = 30;
  public final static Monde world = new Monde();
  private Image imageHerbe = null;

  private Monde(){
    setPreferredSize(new Dimension(taille*tailleCase , taille*tailleCase));
    listeItems = new ArrayList<Item>();
    initialize();
    imageHerbe = Images.getImage("Herbe");
    /*
    try {
      imageHerbe= ImageIO.read(new File("./Image/herbe.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }*/
  }

  private static int getPositionAlea(){
    return (int) (Math.random() * taille);
  }

  private void initialize(){
    //############# Arbre #############
    for (int j = 0; j < taille; j++){
      ajouterItemAtCoord(new Arbre(0,j));
      ajouterItemAtCoord(new Arbre(j,0));
    }      
    for (int j = 1; j < taille; j++){
      ajouterItemAtCoord(new Arbre(taille-1,j));
      ajouterItemAtCoord(new Arbre(j,taille-1));
    }
    //############# Creature #############
    for (int j = 0; j < Math.random() * (taille / 2) + 6; j++)
      ajouterItem(new Creature());
    for (int j = 0; j < Math.random() * (taille / 4) + taille / 2; j++)
      ajouterItem(new Ryuk());
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
    //############# Magasin #############
    ajouterItem(new Fruitier());
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

  public void dessinerTalk(Graphics g, int height){
    int size = taille * tailleCase;
    Image imageShop = null;
    try {
      imageShop = ImageIO.read(new File("./Image/dialogue.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
    g.setColor(new Color(95, 0, 0));
    g.fillRect(15, size - height, size - 30, height - 15);
    g.drawImage(imageShop, 20, size - height + 3, size - 40, height - 20, this);
  }

  public void paintComponent(Graphics g){
		super.paintComponent(g); //redessine le panneau
    //g.setColor(Color.GREEN);
    //g.fillRect(0, 0 , getWidth() ,getHeight() ) ;
    dessinerMap(g);
    //super.paintComponent(g); //redessine le panneau
    /*
    for (int i = 0; i<getWidth(); i++){
      g.setColor(Color.ORANGE);
      g.drawLine( i*tailleCase, 0, i*tailleCase, getHeight());
    }
    for (int i = 0; i<getHeight(); i++){
      g.setColor(Color.ORANGE);
      g.drawLine(0,  i*tailleCase, getWidth(), i*tailleCase);
    }*/
		for(Item itemVoisin : listeItems)
			if( itemVoisin != null)
				itemVoisin.dessiner(g);
  }
}
