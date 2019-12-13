import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Coffre extends Item {
    private ArrayList<Item> contenu;
    private Image image=null;
    //private boolean state; //false = ferme et true = ouvert

    public Coffre(){
        super("Coffre");
        //state=false;
        contenu = new ArrayList<Item>();
        try {
          image = ImageIO.read(new File("coffre_ferme_retouche.png"));
        }
        catch(IOException exc) {
          exc.printStackTrace();
        }
    }

    public void ajouter(Item item){
        contenu.add(item);
    }

    public ArrayList<Item> getContenu(){
        return contenu;
    }

    public void dessiner(Graphics g, Monde m){
        int tc = m.getTailleCase();
        g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2,m);
    }
}
