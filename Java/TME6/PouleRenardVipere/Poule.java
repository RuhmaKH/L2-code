public class Poule extends Animal{
  public static int cpt=0;
  public final int id;


  public Poule(int x, int y, String nom){
      super(x,y,"Poule_");
      id=cpt++;
    }

  public String toString(){
      return String.format("p%02d", id);
  }

  public void afficher(){
      System.out.println(super.getNom()+"en position "+super.getX()+" et "+super.getY() );
  }
}
