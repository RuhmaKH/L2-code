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
  private Image image = null;


  public Avatar(String nom, double poids, String nomFichier){
    super(nom, poids);
    listeAmis = new ArrayList<Creature>();
    listeAcc = new ArrayList<Acc>();
    dealMoney(Math.random() * 10 + 5);
    //image = Images.getImage(str);
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

  public void supprimePremierAmi(){
    listeAmis.remove(0);
  }

  public boolean estAmi(Creature crea){
    if (listeAmis.contains(crea))
      return true;
    return false;
  }

  private void devenirAmi(Creature crea){
    crea.newBFF(this);
    listeAmis.add(crea);
    System.out.println(crea.getNom() + " est devenu l'ami de " + getNom());
  }

  protected void perdreAmi(Creature crea){
    crea.newBFF(this);
    listeAmis.remove(crea);
    System.out.println(crea.getNom() + " n'est plus l'ami de " + getNom());
  }

  private void rencontrer(Creature crea){
    Acc a = null;
    if (listeAcc.size() > 0){
      a = listeAcc.get(0);
      listeAcc.remove(0);
    }
    if (a != null){
      System.out.println(getNom() + " a donné " + a.getNom() + " à " + crea.getNom());
      crea.ajouter(a);
      if (!this.estAmi(crea) && a.getPoids() < 50)
        this.devenirAmi(crea);
    }
    else
      if (this.estAmi(crea))
        this.perdreAmi(crea);
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
      if (item instanceof Tresor){
        dealMoney( ((Tresor) item).getTresor() );
      }
      else
        ramasser((Acc) item, true);
    }
  }

  private void ramasser(Acc acc, boolean msg){
    boolean place = false;
    if ( acc instanceof LivreMagique || acc instanceof Epee){
      listeAcc.add(acc);
      return;
    }
    for (Item i : listeAcc)
        if (i instanceof Sac){
          if((place = ((Sac) i ).ajouter(acc, msg))){
            if (msg)
              System.out.println(acc.getNom() + " a été placé(e) dans le " + i.getNom() + " de " + this.getNom());
            return;
          }
        }
    if (! place){
      listeAcc.add(acc);
      if (msg){
        System.out.println(getNom() + " ramasse " + acc.getNom());
    }
    Monde.supprimerItem(acc);
    }
  }

  public void rencontrerVoisins(){
    Jeu.interact();
    ArrayList<Item> voisins = Monde.getVoisins(this);
    for (Item item : voisins){
      if (item instanceof Avatar){
        System.out.println("Salutation mon ami " + item.getNom());
        killCreature( (Avatar) item);
        continue;
        }
      if (item instanceof Creature){
        rencontrer((Creature) item);
        continue;
        }
      if (item instanceof Coffre){
        ouvrir((Coffre) item);
        continue;
        }
      if (item instanceof Acc){
        ramasser((Acc) item, true);
        continue;
        }
      if (item instanceof ArbreMagique){
        ArbreMagique arbremagique = (ArbreMagique) item ;
        if( arbremagique.getContenu() instanceof Gobelin){
          arbremagique.changeImage();
          toutPerdre();
        }
        if (arbremagique.getContenu() instanceof Sonic){
          rencontrerSonic ( (Sonic)(arbremagique.getContenu()) , item);
        }
      }

      if (item instanceof Magasin){
        //Monde.dessinerShop();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenu.e dans mon magasin " + item.getNom() + "\n Souhaitez-vous :\n\t( 0 )-acheter ?\n\t( 1 )-vendre ?\n\t( 2 )-Partir" );
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
    if ( (x >= 0 && x < taille)  && (y >= 0 && y < taille) && (Monde.chercher(x,y)== null)){
      setX(x);
      setY(y);
    }
    Monde.world.repaint();
  }

  public void update(){
    int[] coord = MyKeyListener.getDirection();
    seDeplacer(coord[0], coord[1]);
  }

  public double acheter (Acc acc){
    double prix = acc.getPrix();
    if (prix > money){
      System.out.println("Vous n'avez pas assez d'argent pour acheter " + acc.getNom());
      return 0.0;
    }
    dealMoney(-prix);
    ramasser(acc, false);
    return prix;
  }

  public void vendre (Magasin mag) {
    Scanner sc = new Scanner(System.in);
    String discution;
    int num, i = 0, nbItem;
    do{
        discution = String.format("Le magasin possède %.2f\nVous pouvez vendre : \n", mag.getMoney());
        for (Acc acc : listeAcc){
          discution += String.format("\t( %d )-%s : %.2f\n", i, acc.getNom(), acc.getPrix());
          i++;
          if(acc instanceof Sac && ( nbItem = ((Sac) acc).getNbElements() ) != 0){
            Acc[] contenu = ((Sac) acc).getTab();
            for(int j = 0; j < nbItem; j++){
              discution += String.format("\t\t( %d )-%s : %.2f\n", i, contenu[j].getNom(), contenu[j].getPrix());
              i++;
            }
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
            dealMoney( mag.vendre(acc) );
            if (acc instanceof Sac)
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
    for (int i = 0; i < sac.getNbElements(); i++){
      num--;
      if (num == 0){
        dealMoney( mag.vendre( sac.obtenir(i) ) );
        return 0;
      }
    }
    return num;
  }

  private void dealMoney(double argent){
    if (argent > 0)
      System.out.println( String.format("%s a gagné %.2f €", getNom(), argent));
    if (argent < 0)
      System.out.println( String.format("%s a perdu %.2f €", getNom(), argent));
    money += argent;
  }

  public void toutPerdre(){
    money = 0;
    int a = listeAcc.size();
    for ( int i =0 ; i< a ; i++){
      //if ( listeAcc.get(0) instanceof Sac)
      //  ((Sac)(listeAcc.get(0))).vider();
      listeAcc.remove(0);
    }
  }

  public void rencontrerSonic(Sonic sonic, Item item){
    for ( int i =0 ; i< listeAcc.size(); i++){
      if (listeAcc.get(i) instanceof LivreMagique){
        ((ArbreMagique) item ).changeImage();
        sonic.newBFF(this);
        listeAmis.add(sonic);
        listeAcc.remove(i);
      }
    }
  }

  public void killCreature( Avatar avatar){
    ArrayList<Creature> listeCreature = avatar.getAmis();
    if ( listeCreature !=null){
      for ( int i =0 ; i< listeAcc.size();  i++){
        if (listeAcc.get(i) instanceof Epee){
          listeAcc.remove(i);
          Monde.supprimerItem(listeCreature.get(0));
          avatar.supprimePremierAmi();
        }
      }
    }
  }


  public void dessiner(Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image, getX() * tc + 1, getY() * tc + 1, tc - 2, tc - 2, Monde.world);
    }
}
