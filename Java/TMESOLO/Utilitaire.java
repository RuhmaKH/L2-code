public class Utilitaire{
  public void peser(Fruit[] panier){
    double poids = 0;
    for(Fruit f : panier)
      poids += f.getPoids();
    String s = String.format("%.2f",poids);
    System.out.println("Les fruits du panier pèsent "+s);
  }
}
