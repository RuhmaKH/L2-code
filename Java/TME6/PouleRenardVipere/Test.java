public class Test{
    public static void main(String [] args ){
      Animal poule = new Poule(1,0);
      Animal poule1 = new Poule(1,0);
      Animal maitre_renard = new Renard(1,2);
      Animal vipere = new Vipere(2,0);
      System.out.println(poule);
      System.out.println(poule1);
      System.out.println(poule.getCoordonnees());

      maitre_renard.fait_le_move(2,0);
      poule.fait_le_move(2,3);
      vipere.fait_le_move(1,0);
      System.out.println(poule.getCoordonnees());

    }
}
