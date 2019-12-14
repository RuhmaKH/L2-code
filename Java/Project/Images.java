import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Images{
  private static String[] tab = {"Avatar1" , "Avatar2", "Arbre", "Tresor", "Sac", "Pomme",
  "Pills", "Herbe", "Coffre_ouvert", "Coffre_ferme", "Marchand",
    "Chevre", "Bird", "Cheval", "Lion", "Baleine", "Pingouin", "Poule", "Lapin", "Rat", "Tigre", "Singe"};
  private static Image[] tabimage=  new Image[22];

  public Images(){

  try {
  tabimage[0]=ImageIO.read(new File("./Image/link_retouche.png"));
  tabimage[1]=ImageIO.read(new File("./Image/mario_retouche.png"));
  tabimage[2]=ImageIO.read(new File("./Image/arbreblanc_retouche.png"));
  tabimage[3]=ImageIO.read(new File("./Image/argent_retouche.png"));
  tabimage[4]=ImageIO.read(new File("./Image/bag.png"));
  tabimage[5]=ImageIO.read(new File("./Image/pomme_retouche.png"));
  tabimage[6]=ImageIO.read(new File("./Image/pillule_retouche.png"));
  tabimage[7]=ImageIO.read(new File("./Image/herbe_retouche.png"));
  tabimage[8]=ImageIO.read(new File("./Image/coffre_ouvert_retouche.png"));
  tabimage[9]=ImageIO.read(new File("./Image/coffre_ferme_retouche.png"));
  tabimage[10]=ImageIO.read(new File("./Image/marchand.png"));
  tabimage[11]=ImageIO.read(new File("./Image/Chevre.png"));
  tabimage[12]=ImageIO.read(new File("./Image/Bird.png"));
  tabimage[13]=ImageIO.read(new File("./Image/Cheval.png"));
  tabimage[14]=ImageIO.read(new File("./Image/Lion.png"));
  tabimage[15]=ImageIO.read(new File("./Image/Baleine.png"));
  tabimage[16]=ImageIO.read(new File("./Image/Pingouin.png"));
  tabimage[17]=ImageIO.read(new File("./Image/Poule.png"));
  tabimage[18]=ImageIO.read(new File("./Image/Lapin.png"));
  tabimage[19]=ImageIO.read(new File("./Image/Rat.png"));
  tabimage[20]=ImageIO.read(new File("./Image/Tigre.png"));
  tabimage[21]=ImageIO.read(new File("./Image/Singe.png"));
} catch(IOException exc) {
    exc.printStackTrace();
  }
}

  public static Image getImage(String image){
    for ( int i = 0 ; i < tab.length ; i++)
      if (tab[i]==image)
        return tabimage[i];
    return null;
  }

// Dragon Minautore Licorne sonic flash Phoenix Pegasus



}
