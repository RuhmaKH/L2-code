import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


public abstract class Magasin extends Item {
    protected ArrayList<Acc> stock;
    protected double money;

    protected Magasin (String nom){
        super(nom);
        stock = new ArrayList<Acc>();
        money = Math.random() * 30 + 10;
    }

    public ArrayList<Acc> getStock(){
        return stock;
    }

    public abstract void acheter(Avatar a);

    public abstract double vendre(Acc acc);
    public abstract void dessiner ( Graphics g , Monde m);
}
