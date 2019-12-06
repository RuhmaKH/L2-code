public class Motrice extends ElemTrain{
  private double puissance;

  public Motrice(double p,String m){
    super(m);
    puissance=p;
  }

  public String toString(){
    return "Motrice de puissance : "+puissance+" "+super.toString();
  }
}
