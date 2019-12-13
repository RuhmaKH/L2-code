import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Sac extends Acc{
  private Acc[] tab;
  private Image image =null;

  public Sac(int n) {
    super("Sac");
    tab= new Acc[n];
    try {
      image = ImageIO.read(new File("./Image/bag.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
  }

  public Sac(){
    super("Sac");
    tab = new Acc[(int)(Math.random() * 10) + 1];
  }

  public Acc[] getTab(){
    return tab;
  }

  public boolean ajouter(Acc a){
    for(Acc acc : tab)
      if(acc instanceof Sac)
        if( ((Sac) acc).ajouter(a, "Sous"))
          return true;
    int nb;
    if((nb = getNbElements()) < tab.length){
      tab[nb] = a;
      return true;
    }
    else{
      System.out.println("Pas la place !");
      return false;
    }
  }

  private boolean ajouter(Acc a, String niv){
    for(Acc acc : tab)
      if(acc instanceof Sac)
        if( ((Sac) acc).ajouter(a, niv + "Sous "))
          return true;
    int nb;
    if((nb = getNbElements()) < tab.length){
      tab[nb] = a;
      System.out.println("L'item a été placé dans le " + niv + "sac !");
      return true;
    }
    else{
      System.out.println("Pas la place dans le " + niv + "sac !");
      return false;
    }
  }

  public Acc obtenir(int i){
    if (i < getNbElements()){
      Acc finale = tab[i];
      for (int j = i; j < tab.length - 1; j++)
        tab[j] = tab[j+1];
      tab[tab.length-1] = null;
      return finale;
    }
    return null;
  }

  public int getNbElements(){
    int cpt=0;
    for (int i = 0; i < tab.length; i++) {
        if (tab[i] != null)
          cpt++;
    }
    return cpt;
  }

  public double getPoids(){
    double poids=0;
    for(int i=0; i<getNbElements(); i++){
        poids+=tab[i].getPoids();
    }
    return poids;
  }

  public double getPrix(){
    return 0.5 * tab.length + 0.5;
  }

  public String toString(){
    String s = super.toString() + " contient " + getNbElements() + " accessoires sur " + tab.length + " :\n";
    for(int i=0; i<getNbElements(); i++){
       s=s+"\t"+tab[i].toString()+"\n";
    }
    return s;
  }

  public void dessiner(Graphics g, Monde m){
      int tc = m.getTailleCase();
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
    	//g.setColor(new Color(225,255,0)); //couleur courante devient bleu
    	//g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
