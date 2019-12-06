public abstract class Wagon extends ElemTrain{
  private final int porte;

  protected Wagon(int p,String m){
    super(m);
    porte = p;
  }

  public String toString(){
    return super.toString();
  }
}
