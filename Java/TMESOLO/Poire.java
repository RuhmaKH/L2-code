public class Poire implements Fruit {
  private final double poids = Math.random();

  public Poire(){
  }

  public double getPoids(){
    return poids;
  }

  public String toString(){
    String s ="Poire "+ String.format("%.2f",poids) + "kg";
    return s;
  }
}
