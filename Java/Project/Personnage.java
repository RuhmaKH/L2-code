public abstract class Personnage{
  private String nom;
  private double poids;

  protected Personnage(String nom, double poids){
    this.nom = nom;
    this.poids = poids;
    name.add(nom);
  }

  protected Personnage(String nom){
    this.nom = nom;
    poids = Math.random()*100+30;
  }

  protected String getNom(){
    return nom;
  }

  protected double getPoids(){
    return poids;
  }

  protected void addPoids(double p){
    if (p>0)
      poids += p;
  }

  public String toString(){
    return nom + String.format("   %.2f",poids) + "kg\n";
  }
}
