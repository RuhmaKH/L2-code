public class Noisette implements Fruit {
  private final double poids = Math.random();

  public Noisette(){
  }

  public double getPoids(){
    return poids;
  }

  public String toString(){
    String s ="Noisette "+ String.format("%.2f",poids) + "kg";
    return s;
  }
}
