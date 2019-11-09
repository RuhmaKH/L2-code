public class Boa extends AnimalSansPattes{
  private static int num=1;

  public Boa(int a){
    super("Boa "+num, a);
    num++;
  }

  public Boa(){
    super("Boa "+num);
    num++;
  }

  public String toString(){
    return super.toString();
  }

  public void crier(){
    System.out.println("*fourche Langue*");
  }
}
