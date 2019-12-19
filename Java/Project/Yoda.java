import java.awt.*;

public class Yoda extends Creature{
  public static final Yoda yoda = new Yoda();

  private Yoda (){
    super("Yoda");
  }

  public double getVitesse(){
    return 30;
  }

  public String toString(Avatar avatar){
    return "Maitre Yoda ralentit de 50% les créatures de " + avatar.getNom() ;
  }

}
