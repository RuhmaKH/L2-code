import java.awt.*;

public class Sonic extends Creature{
  private Image image = null;

  public Sonic (){
    super("Sonic");
    image = Images.getImage("Sonic");
  }

  public double getVitesse(){
    return 50;
  }

}
