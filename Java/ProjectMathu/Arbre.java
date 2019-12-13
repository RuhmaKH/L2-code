import javax.swing.*;
import java.awt.*;

public class Arbre extends Obstacle{

  public Arbre (){
    super("Arbre");
  }

  public Arbre( int x , int y ){
    super( "Arbre",x ,y);
  }

    public void dessiner ( Graphics g , Monde m){}
}
