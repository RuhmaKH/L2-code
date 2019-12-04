public abstract class Personnage extends Item{
  private double poids;
/*
  protected Personnage(String nom, double poids, int x, int y){
    super(nom, x, y);
    this.poids = poids;
  }

  protected Personnage(String nom, int x, int y){
    this(nom, Math.random() * 100 + 30, x, y);
  }
*/
  protected Personnage(String nom, double poids){
    super(nom);
    this.poids = poids;
  }

  protected Personnage(String nom){
    this(nom, Math.random() * 100 + 30);
  }

  public double getPoids(){
    return poids;
  }

  protected void addPoids(double p){
    if (p>0)
      poids += p;
  }

  public String toString(){
    return getNom() + String.format("   %.2f",poids) + "kg\n";
  }
}
