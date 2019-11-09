public class Vache extends AnimalAvecPattes{
  private static int num=1;

  public Vache(int a){
    super("Vache "+num, a, 4);
    num++;
  }

  public Vache(){
    super("Vache "+num,4);
    num++;
  }

  public String toString(){
    return super.toString();
  }

  public void crier(){
    System.out.println("Meuuuuuuh");
  }
}
