import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Eau extends Obstacle{

  public Eau (){
    super("Eau");
  }

  public Eau( int x , int y ){
    super( "Eau",x ,y);
  }

    public void dessiner (Graphics g){}
}
