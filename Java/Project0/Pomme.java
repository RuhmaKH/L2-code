import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Pomme extends Acc implements Mangeable{
  private double rayon;

  public Pomme(){
    super("Pomme");
    rayon = Math.random() * 4 + 3;
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

  public void dessiner(Graphics g, Monde m){
      int tc = m.getTailleCase();
      Image image =null;
      try {
        image = ImageIO.read(new File("Apple_video_game.jpg"));
      }
      catch(IOException exc) {
        exc.printStackTrace();
      }
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
    //g.setColor(new Color(255,0,0));// rouge
    //g.fillOval(getX()*tc, getY()*tc,tc,tc); //cercle plein
  }
}
