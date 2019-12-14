import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Arbre extends Obstacle{
  private Image image= null;


  public Arbre (){
    super("Arbre");
    try {
      image= ImageIO.read(new File("./Image/arbreblanc_retouche.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
  }

  public Arbre( int x , int y ){
    super( "Arbre" , x ,y);
    try {
      image= ImageIO.read(new File("./Image/arbreblanc_retouche.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
  }

    public void dessiner (Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2, Monde.world);
    }


}
