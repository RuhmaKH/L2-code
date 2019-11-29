public class Pomme extends Acc implements Mangeable{
  private double rayon;


  public Pomme(){
    super("Pomme");
    rayon=Math.random()* 4 + 3;
  }

  public double getPoids(){
    return (4* Math.PI * (rayon*rayon*rayon))/3000;
  }


  public String toString(){
    return super.toString()+String.format("   %.2f",rayon)+"cm\n";
  }
}
