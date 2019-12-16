import java.awt.*;

public class Tresor extends Item {
    private double tresor;

    public Tresor(){
        super("Tresor");
        tresor = Math.random() * 15 + 10;
    }

    public double getTresor(){
        return tresor;
    }

    public void dessiner (Graphics g){}
}
