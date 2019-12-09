public abstract class Magasin extends Item {
    protected Sac stock;
    protected double money;
    
    protected Magasin (String nom, int nb){
        super(nom);
        stock = new Sac(nb);
        money = Math.random() * 30 + 10;
    }

    public Sac getStock(){
        return stock;
    }

    public abstract void interagir(Avatar a);

    protected abstract void acheter(Avatar a);

    protected abstract void vendre(Avatar a);
}