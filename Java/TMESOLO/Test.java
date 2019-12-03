public class Test{
  public static void main(String[]args){
    System.out.println(Arbre.getNbArbres());

    Poirier p = new Poirier();
    p.produireFruit();
    p.produireFruit();

    System.out.println(p.getNbFruits());

    System.out.println(Arbre.getNbArbres());
  }
}
