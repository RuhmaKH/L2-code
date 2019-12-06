public class Vipere extends Animal{
  public static int cpt=0;
  public final int id;


  public Vipere(int x, int y){
      super(x,y,"vipere");
      id=cpt++;
    }

  public String toString(){
      return String.format("v%02d", id);
  }

  public void afficher(){
      System.out.println(super.getNom()+"en position "+super.getX()+" et "+super.getY() );
  }
}
