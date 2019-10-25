public class TestOrchestre{
    public static void main( String [] agrs){

    Orchestre o= new Orchestre(10);
    o.ajouterInstrument(new Piano(3.0, 55.0));

    System.out.println(o.jouer());
    }
  }
