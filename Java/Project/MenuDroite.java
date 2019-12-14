import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class MenuDroite extends JPanel{
  private Image imageCase = null;
  private Image imageArgent = null;
  private Image imageSac = null;
  private Image imagePomme = null;
  private Image imagePills = null;
  private double money;
  private int sac;
  private int pomme;
  private int pillule;


  public MenuDroite () {
    setPreferredSize(new Dimension(650,0));
    money = 0;
    sac = 0;
    pomme = 0;
    pillule = 0;
    try {
      imageCase= ImageIO.read(new File("./Image/case.jpg"));
      imageArgent = ImageIO.read(new File("./Image/argent_retouche.png"));
      imageSac = ImageIO.read(new File("./Image/bag.png"));
      imagePomme = ImageIO.read(new File("./Image/pomme_retouche.png"));
      imagePills = ImageIO.read(new File("./Image/pillule_retouche.png"));
    }
    catch(IOException exc) {
      exc.printStackTrace();
    }
  }

  public void compteDifferentAcc(Avatar avatar){
    sac = 0;
    pomme = 0;
    pillule = 0 ;
    money = avatar.getMoney();
    ArrayList<Acc> tab = avatar.getAcc();
    for (Acc acc : tab){
      if (acc instanceof Sac){
        compteDifferentAcc((Sac) acc);
        sac++;
        continue;
      }
      if (acc instanceof Pomme){
        pomme++;
        continue;
      }
      if(acc instanceof Pills){
        pillule++;
        continue;
      }
    }
  }

  public void compteDifferentAcc(Sac soussac){
    for (Acc acc : soussac.getTab()){
      if (acc instanceof Sac){
        compteDifferentAcc((Sac) acc);
        sac++;
        continue;
      }
      if (acc instanceof Pomme){
        pomme++;
        continue;
      }
      if(acc instanceof Pills){
        pillule++;
        continue;
      }
    }
  }

  public void paintComponent(Graphics g){
    Font font = new Font("Trajan", Font.BOLD, 40);
    Font font1 =new Font ("Trajan", Font.ITALIC,15);
    Font font2 = new Font ("Trajan", Font.ITALIC,10);
    g.setFont(font);
    g.setColor(Color.YELLOW);
    g.fillRect(0, 0 , getWidth() ,getHeight() ) ;
    g.setColor(Color.RED);
    g.drawString("LA COURSE DES DIEUX",70,50);
    g.setColor(Color.RED);
    Avatar[] players= Jeu.getPlayers();

    int taille =  50;
    int espace = 10;
    g.drawImage(imageCase,  10 ,330 , 45 ,45 , this) ;
    g.drawImage(players[0].getImage(),  8,330, 45 , 42 ,this) ;
    g.drawString(players[0].getNom(),70,360);
    g.drawString(players[1].getNom(),70, 660);

    for (int i = 0; i< 10; i++ ){
      g.drawImage(imageCase,  i*taille+espace,400, taille , taille , this) ;
      espace+=10;
      if (i==0)
        g.drawImage(imageArgent,  i*taille +13 , 404 , 30 , 30 ,this) ;
      if (i==1)
        g.drawImage(imageSac,  i*taille+espace - 10  ,405 , 30 , 30 ,this) ;
      if (i==2)
        g.drawImage(imagePomme,  i*taille+espace - 10 , 405 , 33 ,30 ,this) ;
      if (i==3)
        g.drawImage(imagePills,  i*taille+espace -5, 404 , 15, 35,this) ;
    }
    g.setColor(Color.WHITE);
    compteDifferentAcc(players[0]);


    g.setFont(font1);
    g.drawString("x" + sac, 90 , 445);
    g.drawString("x" + pomme, 150 , 445);
    g.drawString("x" + pillule, 210 , 445);
    g.setFont(font2);
    g.drawString(String.format("%.2f", money) + "€", 25 , 442);


    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(imageCase,  i*taille+espace,470, taille ,taille ,this) ;
      espace+=10;
    }
// repaint

    g.drawImage(imageCase,10,630, taille,taille,this) ;
    g.drawImage(players[1].getImage(),  12 , 635 , 45 , 39,this) ;
    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(imageCase,  i*taille+espace,700, taille , taille , this) ;
      espace+=10;
      if (i==0)
        g.drawImage(imageArgent,  i*taille +13 , 704 , 30 , 30 ,this) ;
      if (i==1)
        g.drawImage(imageSac,  i*taille+espace - 10  ,705 , 30 , 30 ,this) ;
      if (i==2)
        g.drawImage(imagePomme,  i*taille+espace - 10 , 705 , 33 ,30 ,this) ;
      if (i==3)
        g.drawImage(imagePills,  i*taille+espace -5, 704 , 15, 35,this) ;
    }
    espace =10;
    for (int i = 0; i< 10; i++ ){
      g.drawImage(imageCase,  i*taille+espace,770, taille,taille,this) ;
      espace+=10;
    }
    compteDifferentAcc(players[1]);
    g.setFont(font1);
    g.drawString("x"+sac, 90 , 745);
    g.drawString("x"+pomme, 150 , 745);
    g.drawString("x"+pillule, 210 , 745);
    g.setFont(font2);
    g.drawString(String.format("%.2f",money)+"€", 25 , 742);
  }

}
