import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class Monde extends JPanel{
  private ArrayList<Item> listeItems;
  private int taille;
  private int tailleCase;

  public Monde(int taille, int tailleCase){
    setPreferredSize(new Dimension(taille*tailleCase , taille*tailleCase));
    this.taille = taille;
    this.tailleCase=tailleCase;
    listeItems = new ArrayList<Item>();
  }

  public int getTailleCase(){
    return tailleCase;
  }

  public int getPositionAlea(){
    return (int) (Math.random() * taille);
  }

  public int getTaille(){
    return taille;
  }

    public void initialize(){
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

    public void ajouterItem(Item item){
      do {
        item.setX(getPositionAlea());
        item.setY(getPositionAlea());
      } while (chercher(item.getX(), item.getY()) != null);
      listeItems.add(item);

    }

    private void ajouterAcc(Acc acc){
      ArrayList<Coffre> coffres = new ArrayList<Coffre>();
      for (Item item : listeItems)
        if (item instanceof Coffre)
          coffres.add((Coffre) item);
      coffres.get( (int) (Math.random() * coffres.size()) ).ajouter(acc);;
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

  public void paintComponent(Graphics g){
		super.paintComponent(g); //redessine le panneau
    g.setColor(Color.GREEN);
    g.fillRect(0, 0 , getWidth() ,getHeight() ) ;
    for (int i = 0; i<getWidth(); i++){
      g.setColor(Color.ORANGE);
      g.drawLine( i*tailleCase, 0, i*tailleCase, getHeight());
    }
    for (int i = 0; i<getWidth(); i++){
      g.setColor(Color.ORANGE);
      g.drawLine(0,  i*tailleCase, getWidth(), i*tailleCase);
    }
		for(Item itemVoisin : listeItems){
			if( itemVoisin != null){
				itemVoisin.dessiner(g,this);
			}
		}
  }
}
