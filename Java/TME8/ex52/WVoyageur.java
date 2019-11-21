public class WVoyageur extends Wagon{
  private final int place;

  public WVoyageur(int place,int porte,String m){
    super(porte,m);
    this.place = place;
  }
  public String toString(){
    return "Wagon Voyageur "+super.toString();
  }

}
