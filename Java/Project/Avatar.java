import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Avatar extends Personnage{
  private ArrayList<Creature> listeAmis;
  private ArrayList<Acc> listeAcc;
  private double money;
  private static int cpt = 0;
  private final int id;
  private Image image =null;

  public Avatar(String nom, double poids, String  nomFichier){
    super(nom, poids);
    listeAmis = new ArrayList<Creature>();
    listeAcc = new ArrayList<Acc>();
    money = Math.random() * 10 + 5;
    id=cpt;
    cpt++;

    try {
          image = ImageIO.read(new File("./Image/"+nomFichier));
        }
        catch(IOException exc) {
          exc.printStackTrace();
        }

  }

  public String toString(){
    int nbAcc = 0;
    for (Acc acc : listeAcc){
      nbAcc++;
      if (acc instanceof Sac)
        nbAcc += ((Sac) acc).getNbElements();
    }
    return super.toString() + " : " + listeAmis.size() + " amis(s) " + nbAcc + " accessoire(s)";
  }
  public Image getImage(){
    return image;
  }

  public ArrayList<Creature> getAmis(){
    return listeAmis;
  }

  public ArrayList<Acc> getAcc(){
    return listeAcc;
  }

  public double getMoney(){
    return money;
  }

  public boolean estAmi(Creature crea){
    if (listeAmis.contains(crea))
      return true;
    return false;
  }

  private void devenirAmi(Creature crea){
    if (! this.estAmi(crea)){
      crea.newBFF(this);
      listeAmis.add(crea);
      System.out.println(crea.getNom() + " est devenu l'ami de " + getNom());
    }
  }

  private void PerdreAmi(Creature crea){
    if (this.estAmi(crea)){
      crea.looseBFF();
      listeAmis.remove(crea);
      System.out.println(crea.getNom() + " n'est plus l'ami de " + getNom());
    }
  }

  private void rencontrer(Creature crea){
    Acc a = null;
    if (listeAcc.size() > 0){
      a = listeAcc.get(0);
      listeAcc.remove(0);
    }
    
    if (a != null){
      crea.ajouter(a);
      if (!this.estAmi(crea) && a.getPoids() < 50)
        this.devenirAmi(crea);
    }
    else
      if (this.estAmi(crea))
        this.PerdreAmi(crea);
  }

  public double course(){
    double dist = 0;
    for ( Creature crea : listeAmis){
      crea.manger();
      crea.courir();
      dist += crea.getVitesse();
    }
    return dist;
  }

  public Creature getCreaturePlusRapide(){
    Creature rapide = listeAmis.get(0);
    for ( Creature crea : listeAmis)
      if (rapide.getVitesse() > crea.getVitesse())
        rapide = crea;

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


  private void ouvrir(Coffre coffre){
    ArrayList<Item> contenu = coffre.ouvrir();
    for (Item item : contenu){
      if (item instanceof Tresor)
        money += ((Tresor) item).getTresor();
      else
        ramasser((Acc) item, true);
    }
  }


  private void ramasser(Acc acc, boolean msg){
    boolean place = false;
    for (Item i : listeAcc)
        if (i instanceof Sac){
          if((place = ((Sac) i ).ajouter(acc, true)))
            break;
        }
    if (! place)
      listeAcc.add(acc);
    if (msg){
      System.out.println(getNom() + " ramasse " + acc.getNom());
      Monde.supprimerItem(acc);
    }
  }

  public void rencontrerVoisins(){
    Jeu.interact();
    ArrayList<Item> voisins = Monde.getVoisins(this);
    for (Item item : voisins){
      if (item instanceof Avatar)
        System.out.println("Salutation mon ami " + item.getNom());
      if (item instanceof Creature)
        rencontrer((Creature) item);
      if (item instanceof Coffre)
        ouvrir((Coffre) item);
      if (item instanceof Acc)
        ramasser((Acc) item, true);
      if (item instanceof Magasin){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenu.e dans mon magasin " + item.getNom() + "\n Souahaitez-vous :\n\t( 0 )-acheter ?\n\t( 1 )-vendre ?\n\t( 2 )-Partir" );
        switch (sc.nextInt()) {
          case 0:
            ((Magasin) item).acheter(this);
            break;
          case 1:
            vendre((Magasin) item);
        }
      }
      Monde.world.repaint();
    }
    Jeu.interact();
    Jeu.nextPlayer();
  }

  public void seDeplacer(){
    int absi, ordo, taille = Monde.taille;
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
    int taille = Monde.taille;
    int x = getX() + dx;
    int y = getY() + dy;
    if ( (x > 0 && x < taille - 1)  && (y > 0 && y < taille - 1) && (Monde.chercher(x,y)== null)){
      setX(x);
      setY(y);
    }
    Monde.world.repaint();
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
    String discution;
    int num, i = 0;
    do{
        discution = String.format("Le magasin possède %.2f\nVous pouvez vendre : \n", mag.getMoney());
        for (Acc acc : listeAcc){
          discution += String.format("\t( %d )-%s : %.2f\n", i, acc.getNom(), acc.getPrix());
          i++;
          if(acc instanceof Sac && ((Sac) acc).getNbElements() != 0)
            for(Acc a : ((Sac) acc).getTab()){
              discution += String.format("\t\t( %d )-%s : %.2f\n", i, a.getNom(), a.getPrix());
              i++;
            }
        }
        discution += "\t( " + i + " )-Acheter ?\n";
        i++;
        discution += "\t( " + i + " )-Partir ?";
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
    }while(true);
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

  public void dessiner(Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image, getX() * tc + 1, getY() * tc + 1, tc - 2, tc - 2, Monde.world);
      //g.setColor(new Color(0,0,255)); //couleur courante devient bleu
      //g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
