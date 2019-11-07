public class Canard extends AnimalAvecPattes{
  private static int num=1;

  public Canard(int a){
    super("Canard "+num, a, 2);
    num++;
  }

  public Canard(){
    super("Canard "+num,2);
    num++;
  }

  public String toString(){
    return super.toString();
  }

  public void crier(){
    System.out.println("CoinCoin");
  }
}
