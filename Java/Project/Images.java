import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Images {
  public final static String[] tab = {"Avatar1" , "Avatar2", "Arbre", "Tresor", "Sac", "Pomme","Pills", "Herbe", "Coffre_ouvert", "Coffre_ferme", "Marchand",
    "Chevre", "Bird", "Cheval", "Lion", "Baleine", "Pingouin", "Poule", "Lapin", "Rat", "Tigre", "Singe", "Dialogue", "Cursor", "Case", "Gobelin", "Banderouge",
  "Redcursor"};
  private static ArrayList<Image> tabimage;
  private final static Images pic = new Images();

  private Images(){
    tabimage = new ArrayList<Image>();
    try {
      tabimage.add( ImageIO.read(new File("./Image/link_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/mario_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/arbreblanc_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/argent_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/bag.png")) );
      tabimage.add( ImageIO.read(new File("./Image/pomme_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/pillule_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/herbe.png")) );
      tabimage.add( ImageIO.read(new File("./Image/coffre_ouvert_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/coffre_ferme_retouche.png")) );
      tabimage.add( ImageIO.read(new File("./Image/marchand.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Chevre.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Bird.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Cheval.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Lion.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Baleine.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Pingouin.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Poule.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Lapin.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Rat.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Tigre.png")) );
      tabimage.add( ImageIO.read(new File("./Image/Singe.png")) );
      tabimage.add( ImageIO.read(new File("./Image/dialogue.png")));
      tabimage.add( ImageIO.read(new File("./Image/cursor.png")));
      tabimage.add( ImageIO.read(new File("./Image/case.jpg")));
      tabimage.add( ImageIO.read(new File("./Image/gobelin.png")));
      tabimage.add( ImageIO.read(new File("./Image/blood_red_bar.png")));
      tabimage.add( ImageIO.read(new File("./Image/redcursor.png")));
    } catch(IOException exc) {
      exc.printStackTrace();
    }
  }

  public static Image getImage(String image){
    for ( int i = 0 ; i < tab.length ; i++)
      if (tab[i] == image)
        return tabimage.get(i);
    System.out.println("Pas d'image trouvé pour " + image);
    return null;
  }

// Dragon Minautore Licorne sonic flash Phoenix Pegasus



}