import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class MenuDroite extends JPanel{
private static Avatar[] players;

  public MenuDroite(Avatar[] joueur){
    setPreferredSize(new Dimension(650,900));
    //setBackground(Color.YELLOW);
    Avatar[] players= new Avatar[2];
    players[0]=joueur[0];
    players[1]=joueur[1];
  }

  public void paintComponent(Graphics g){
    Font font = new Font("Verdana", Font.BOLD, 30);
    g.setFont(font);
    g.setColor(Color.YELLOW);
    g.fillRect(0, 0 , getWidth() ,getHeight() ) ;
    g.setColor(Color.RED);
    g.drawString("LA COURSE DES DIEUX",200,50);
    g.setColor(Color.BLACK);
    int taille =  50;
    int espace = 10;

    g.setColor(Color.RED);
    g.drawString("AVATAR1",20,385);
    Image image = Toolkit.getDefaultToolkit().getImage("case.jpg");
    for (int i = 0; i< 10; i++ ){
      g.drawImage(image,  i*taille+espace,400, taille,taille,this) ;
      espace+=10;
    }
    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(image,  i*taille+espace,470, taille,taille,this) ;
      espace+=10;
    }

    g.setColor(Color.RED);
    g.drawString("AVATAR2",20, 585);
    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(image,  i*taille+espace,600, taille,taille,this) ;
      espace+=10;
    }
    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(image,  i*taille+espace,670, taille,taille,this) ;
      espace+=10;
    }


  }

}
