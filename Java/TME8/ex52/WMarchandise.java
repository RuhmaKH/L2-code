public class WMarchandise extends Wagon{
  public final double poids;

  public WMarchandise(double poids,int porte,String m){
    super(porte,m);
    this.poids = poids;
  }
  public String toString(){
    return "Wagon Marchandise "+super.toString();
  }

}
