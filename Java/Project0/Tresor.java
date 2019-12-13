import javax.swing.*;
import java.awt.*;

public class Tresor extends Item {
    private double tresor;

    public Tresor(){
        super("Tresor");
        tresor = Math.random() * 11 + 10;
    }

    public double getTresor(){
        return tresor;
    }

    public void dessiner ( Graphics g , Monde m){}
}
