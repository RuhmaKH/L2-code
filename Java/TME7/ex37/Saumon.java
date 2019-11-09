public class Saumon extends AnimalSansPattes{
  private static int num=1;

  public Saumon(int a){
    super("Saumon "+num, a);
    num++;
  }

  public Saumon(){
    super("Saumon "+num);
    num++;
  }

  public String toString(){
    return super.toString();
  }

  public void crier(){
    System.out.println("o0o°o0o°");
  }
}
