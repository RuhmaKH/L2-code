import java.util.ArrayList;

public abstract class Magasin extends Item {
    protected ArrayList<Acc> stock;
    protected double money;
    
    protected Magasin (String nom, int nb){
        super(nom);
        stock = new Sac(nb);
        money = Math.random() * 30 + 10;
    }

    public Sac getStock(){
        return stock;
    }

    protected abstract void acheter(Avatar a);

    protected abstract double vendre(Acc acc);
}