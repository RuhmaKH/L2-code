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
    Image image =null;
    try {
			image = ImageIO.read(new File("Smiley_Face.JPG"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}


		super.paintComponent(g); //redessine le panneau
    g.setColor(Color.WHITE);
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
