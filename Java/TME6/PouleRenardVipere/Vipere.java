public class Vipere extends Animal{
  public static int cpt=0;
  public final int id;


  public Vipere(int x, int y, String nom){
      super(x,y,"Vipere_");
      id=cpt++;
    }

  public String toString(){
      return String.format("%02d", id);
  }

  public void afficher(){
      System.out.println(super.getNom()+"en position "+super.getX()+" et "+super.getY() );
  }
}
