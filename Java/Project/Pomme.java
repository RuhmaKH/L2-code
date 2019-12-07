import java.awt.*;
import javax.swing.*;


public class Pomme extends Acc implements Mangeable{
  private double rayon;

  public Pomme(){
    super("Pomme");
    rayon = Math.random()* 4 + 3;
  }

  public double getPoids(){
    return (4 * Math.PI * (Math.pow(rayon, 3))) / 3000;
  }

  public String toString(){
    return super.toString() + String.format("   %.2f", rayon) + "cm\n";
  }

  public void dessiner(Graphics g, Monde m){
    int tc = m.getTailleCase();
    g.setColor(new Color(255,0,0));// rouge
    g.fillOval(getX()*tc, getY()*tc,tc,tc); //cercle plein
  }
}
