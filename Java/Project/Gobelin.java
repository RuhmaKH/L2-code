import java.awt.*;

public class Gobelin extends Personnage{
  private Image image = null ;

  public Gobelin(){
    super("Gobelin");
    image = Images.getImage("Gobelin");
  }


  public void dessiner(Graphics g){
      int tc = Monde.tailleCase;
      g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2, Monde.world);
  }

}
