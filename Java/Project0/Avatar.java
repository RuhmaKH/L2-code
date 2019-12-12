import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Avatar extends Personnage{
  private ArrayList<Creature> listeAmis;
  private ArrayList<Acc> listeAcc;
  private Monde monde;
  private double money;
  private static int cpt = 0;
  private final int id;

  public Avatar(String nom, double poids, Monde monde){
    super(nom,poids);
    listeAmis = new ArrayList<Creature>();
    listeAcc = new ArrayList<Acc>();
    this.monde = monde;
    money = Math.random() * 10 + 5;
    id=cpt;
    cpt++;
  }

  public String toString(){
    return super.toString() + " : " + listeAmis.size() + " amis(s) "+ listeAcc.size() + " accessoire(s)";
  }

  public ArrayList<Creature> getAmis(){
    return listeAmis;
  }

  public double getMoney(){
    return money;
  }

  public boolean estAmi(Creature c){
    if (listeAmis.contains(c))
      return true;
    return false;
  }

  private void devenirAmi(Creature c){
    if (! this.estAmi(c)){
      listeAmis.add(c);
      System.out.println(c.getNom() + " est devenu l'ami de " + getNom());
    }
  }

  private void PerdreAmi(Creature c){
    if (this.estAmi(c)){
      listeAmis.remove(c);
      System.out.println(c.getNom() + " n'est plus l'ami de " + getNom());
    }
  }

  private void rencontrer(Creature c){
    Acc a = (listeAcc.size()>0 ? listeAcc.get(0) : null);
    if (a != null){
      c.ajouter(a);
      if (!this.estAmi(c) && a.getPoids() < 50)
        this.devenirAmi(c);
    }
    else
      if (this.estAmi(c))
        this.PerdreAmi(c);
  }

  public double course(){
    double dist = 0;
    for ( Creature c : listeAmis){
      c.manger();
      c.courir();
      dist += c.getVitesse();
    }
    return dist;
  }

  public Creature getCreaturePlusRapide(){
    Creature rapide = listeAmis.get(0);
    for ( Creature c : listeAmis)
      if (rapide.getVitesse() > c.getVitesse())
        rapide = c;

    return rapide;
  }

  public int compterAccMangeable(){
    int j=0;
    for (int i = 0; i < listeAcc.size(); i++) {
      if (listeAcc.get(i) instanceof Mangeable){
        j++;
      }
    }
    return j;
  }

  private void ramasser(Acc a, boolean msg){
    boolean place = false;
    for (Item i : listeAcc)
        if (i instanceof Sac){
          if((place = ((Sac) i ).ajouter(a)))
            break;
        }
    if (! place)
      listeAcc.add(a);
    if (msg){
      System.out.println(getNom() + " ramasse " + a.getNom());
      monde.supprimerItem(a);
    }
  }

  public void rencontrerVoisins(){
    Jeu.interact();
    ArrayList<Item> voisins = monde.getVoisins(this);
    for(Item i : voisins){
      if(i instanceof Avatar)
        System.out.println("Salutation mon ami " + i.getNom());
      if(i instanceof Creature)
        rencontrer((Creature) i);
      if(i instanceof Acc)
        ramasser((Acc) i, true);
      if(i instanceof Magasin){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenu.e dans mon magasin " + i.getNom() + "\n Souahaitez-vous :\n\t( 0 )-acheter ?\n\t( 1 )-vendre ?\n\t( 2 )-Partir" );
        switch (sc.nextInt()) {
          case 0:
            ((Magasin) i).acheter(this);
            break;
          case 1:
            vendre((Magasin) i);
        }
      }
    }
    Jeu.interact();
    Jeu.nextPlayer();
  }

  public void seDeplacer(){
    int absi, ordo, taille = monde.getTaille();
    Scanner sc = new Scanner(System.in);
    String size = String.format("[0,%d]", taille);
    // Récupère les coordonnées
    do{
      System.out.println("Veuillez saisir une absicisse entre " + size + " : ");
      absi = sc.nextInt();
    }while(absi > taille || absi < 0);
    do{
      System.out.println("Veuillez saisir une ordonnée entre " + size + " : ");
      ordo = sc.nextInt();
    }while(ordo > taille - 1 || ordo < 0);
    // Déplace le personnage aux coordonnées
    this.setX(absi);
    this.setY(ordo);
    // Rencontre ses voisins

    rencontrerVoisins();
  }

  public void seDeplacer(int dx, int dy){
    int taille = monde.getTaille();
    int x = getX() + dx;
    int y = getY() + dy;
    if ( (x > 0 && x < taille - 1)  && (y > 0 && y < taille - 1)){ 
      setX(x);
      setY(y);
    }
    monde.repaint();
  }

  public double acheter (Acc acc){
    double prix = acc.getPrix();
    if (prix > money){
      System.out.println("Vous n'avez pas assez d'argent pour acheter " + acc.getNom());
      return 0.0;
    }
    money -= prix;
    ramasser(acc, false);
    return prix;
  }

  public void vendre (Magasin mag) {
    Scanner sc = new Scanner(System.in);
    String discution = String.format("Le magasin possède %.2\nVous pouvez vendre : \n");
    int num, i = 0;
    do{
        for (Acc acc : listeAcc){
          discution += "\t( " + i + " )-" + acc.getNom() + " : " + acc.getPrix() + "\n";
          if(acc instanceof Sac)
            for(Acc a : ((Sac) acc).getTab()){
              discution += "\t\t( " + (i++) + " )-" + a.getNom() + " : " + a.getPrix() + "\n";
            }
          i++;
        }
        discution += "\t( " + i + " )-Acheter";
        discution += "\t( " + (i++) + " )-Partir";
        System.out.println(discution);
        System.out.println("Choisissez l'objet que vous désirez vendre : ");
        num = sc.nextInt();
        if (num == i - 1){
          mag.acheter(this);
          return;
        }
        if (num == i){
          return;
        }
        for (Acc acc : listeAcc){
          if (num == 0){
            money += mag.vendre(acc);
            if ( acc instanceof Sac)
              for (Acc a : ((Sac) acc).getTab())
                ramasser(a , false);
            listeAcc.remove(acc);
            break;
          }
          if (acc instanceof Sac){
            if ((num = inSac(mag, num, (Sac) acc)) == 0){
              break;
            }
          }
          num--;
        }
        System.out.println("Voulez-vous vendre autre chose ? [o/n]");
    }while( sc.nextLine() != "n");
  }

  private int inSac(Magasin mag, int num, Sac sac){
    int cmpt = 0;
    for (Acc acc : sac.getTab()){
      num--;
      if (num == 0){
        money += mag.vendre(acc);
        if (acc instanceof Sac)
          for (Acc a : sac.getTab())
            ramasser(a, false);
        sac.obtenir(cmpt);
        return 0;
      }
      if(acc instanceof Sac){
        if ((num = inSac(mag, num, (Sac) acc)) == 0){
          return 0;
        }

      }
      cmpt++;
    }
    return num;
  }

  public void dessiner(Graphics g, Monde m){
      int tc = m.getTailleCase();
      Image image =null;
      if (id==0){
        try {
            image = ImageIO.read(new File("Smiley_Face.JPG"));
          }
          catch(IOException exc) {
            exc.printStackTrace();
          }
          g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
      }else{
        try {
            image = ImageIO.read(new File("smiley_bleu.jpg"));
          }
          catch(IOException exc) {
            exc.printStackTrace();
          }
          g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
      }
      //g.setColor(new Color(0,0,255)); //couleur courante devient bleu
      //g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
