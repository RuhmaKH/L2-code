public class Renard extends Animal{
  public static int cpt=0;
  public final int id;


  public Renard(int x, int y, String nom){
      super(x,y,"Renard_");
      id=cpt++;
    }

  public String toString(){
      return String.format("r%02d", id);
  }

  public void afficher(){
      System.out.println(super.getNom()+"en position "+super.getX()+" et "+super.getY() );
  }
}
