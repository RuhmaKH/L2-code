import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Pills extends Acc implements Mangeable {
    private double poids;
    private Image image=null;

    public Pills(){
        super("Pills");
        poids = Math.random();
        try {
          image = ImageIO.read(new File("./Image/pillule_retouche.png"));
        }
        catch(IOException exc) {
          exc.printStackTrace();
        }
      }

    public double getPoids(){
        return poids;
    }

    public double getPrix(){
        return 10 * poids + 8;
    }

    public String toString(){
        return super.toString();
      }

        public void dessiner ( Graphics g , Monde m){
          int tc = m.getTailleCase();
          g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
        }
}
