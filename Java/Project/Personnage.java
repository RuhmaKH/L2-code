public abstract class Personnage{
  protected String nom;
  protected double poids;


  protected Personnage(String nom, double poids){
    this.nom=nom;
    this.poids=poids;
  }

  protected Personnage(String nom){
    this.nom=nom;
    poids=Math.random()*100+30;
}

protected String getNom(){
  return nom;
}

  protected void addPoids(double p){
    if (p>0) {
      poids+=p;
    }
  }

  public String toString(){
    return nom+ String.format("   %.2f",poids)+"kg\n";
  }
}
