public class Poule extends Animal{
  public static int cpt=0;
  public final int id;


  public Poule(int x, int y ){
      super(x,y,"poule");
      id = cpt++;
    }

  public String toString(){
      return String.format("p%02d", id);
  }

  public void afficher(){
      System.out.println(super.getNom()+"en position "+super.getX()+" et "+super.getY() );
  }

}
