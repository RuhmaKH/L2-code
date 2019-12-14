import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Pomme extends Acc implements Mangeable{
  private double rayon;
  private Image image=null;

  public Pomme(){
    super("Pomme");
    rayon = Math.random() * 4 + 3;
    image=Images.getImage("Pomme");
    /*try {
      image = ImageIO.read(new File("./Image/pomme_retouche.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
    */
  }

  public double getPoids(){
    return (4 * Math.PI * (Math.pow(rayon, 3))) / 3000;
  }

  public double getPrix(){
    return rayon * 1.2;
  }

  public String toString(){
    return super.toString() + String.format("   %.2f", rayon) + "cm\n";
  }

  public void dessiner(Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2, Monde.world);
    //g.setColor(new Color(255,0,0));// rouge
    //g.fillOval(getX()*tc, getY()*tc,tc,tc); //cercle plein
  }
}
