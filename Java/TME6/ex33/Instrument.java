public class Instrument{
  private double poids,prix;

  public Instrument(double poids, double prix){
      this.poids=poids;
      this.prix=prix;
  }

  public String toString(){
    return "poids: "+poids+"et prix"+prix;
  }

  public String jouer(){
      return "a";
  }
}
