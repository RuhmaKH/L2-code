public class MainTrain{
  public static void main(String [] args ){
    String m="Autobots";
    Train optimus=new Train( new Motrice(1000,m));
    optimus.ajoute(new WVoyageur(22145, 654, m));
    System.out.println("Optimus : '"+optimus+"'");

  }
}
