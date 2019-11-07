public class MillePattes extends AnimalAvecPattes{
  private static int num=1;

  public MillePattes(int a){
    super("MillePattes "+num, a, 1000);
    num++;
  }

  public MillePattes(){
    super("MillePattes "+num,1000);
    num++;
  }

  public String toString(){
    return super.toString();
  }

  public void crier(){
    System.out.println("xsxsxsxs");
  }
}
