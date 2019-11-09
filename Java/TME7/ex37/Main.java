public class Main{
  public static void main(String [] args){

    Menagerie m= new Menagerie();
    m.ajouter(new Vache());
    m.ajouter(new Canard(4));
    m.ajouter(new Boa(5));
    m.ajouter(new Saumon());

    System.out.println(m);
    m.midi();
    m.vieillirTous();

    System.out.println(m);

  }
}
