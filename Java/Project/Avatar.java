import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Avatar extends Personnage{
  private ArrayList<Creature> listeAmis;
  private ArrayList<Acc> listeAcc;
  private Monde monde;

  public Avatar(String nom, double poids, Monde monde){
    super(nom,poids);
    listeAmis = new ArrayList<Creature>();
    listeAcc = new ArrayList<Acc>();
    this.monde=monde;
  }

  public String toString(){
    return super.toString()+" "+ listeAmis.size()+ " amis(s)"+ listeAcc.size()+" accessoire(s)";
  }

  public ArrayList<Creature> getAmis(){
    return listeAmis;
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
    Acc a=(listeAcc.size()>0 ? listeAcc.get(0) : null);
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
    Creature rapide = null;
    for ( Creature c : listeAmis)
      if (rapide.getVitesse()>c.getVitesse())
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

  private void ramasser(Acc a){
    boolean place = false;
    for (Item i : listeAcc)
        if (i instanceof Sac)
          place = ramasser(a, (Sac) i);
    if (! place)
      listeAcc.add(a);
      System.out.println(getNom() + " ramasse " + a.getNom());
      monde.supprimerItem(a);
  }

  private boolean ramasser(Acc a, Sac sac){
    boolean place = false;
    for ( Item i : sac.getTab())
        if (i instanceof Sac)
          place = ramasser(a, (Sac) i );
    if(! place){
      sac.ajouter(a);
      System.out.println(getNom() + " ramasse " + a.getNom());
      monde.supprimerItem(a);
      return true;
    }
    return false;
  }

  public void rencontrerVoisins(){
    ArrayList<Item> voisins = monde.getVoisins(this);
    for(Item i : voisins){
      if(i instanceof Avatar)
        System.out.println("Salutation mon ami " + i.getNom());
      if(i instanceof Creature)
        rencontrer((Creature) i);
      if(i instanceof Acc)
        ramasser((Acc) i);
    }
  }

  public void seDeplacer(){
    int absi, ordo, t = monde.getTaille();
    Scanner sca = new Scanner(System.in);
    String size = String.format("[0,%d]", t);
    do{
      System.out.println("Veuillez saisir une absicisse entre " + size + " : ");
      absi = sca.nextInt();
    }while(absi > t);
    System.out.println(t);
    do{
      System.out.println("Veuillez saisir une ordonnée entre " + size + " : ");
      ordo = sca.nextInt();
    }while( ordo > t);
    this.setX(ordo);
    this.setY(absi);
  //  sca.close();
    rencontrerVoisins();
  }

  public void dessiner(Graphics g, Monde m){
    	int tc=m.getTailleCase();
    	g.setColor(new Color(0,0,255)); //couleur courante devient bleu
    	g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
