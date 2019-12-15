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
    image = Images.getImage("Sac");
    /*
    try {
      image = ImageIO.read(new File("./Image/bag.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }*/
  }

  public Sac(){
    super("Sac");
    tab = new Acc[(int)(Math.random() * 10) + 1];
  }

  public Acc[] getTab(){
    return tab;
  }

  public boolean ajouter(Acc acc, boolean msg){
    int nb;
    if((nb = getNbElements()) < tab.length){
      tab[nb] = acc;
      return true;
    }
    else{
      if (msg)
        System.out.println("Pas la place dans le " + getNom());
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
    double poids = 0.1;
    for(int i = 0; i < getNbElements(); i++){
        poids += tab[i].getPoids();
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

  public void dessiner(Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2, Monde.world);
    	//g.setColor(new Color(225,255,0)); //couleur courante devient bleu
    	//g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
