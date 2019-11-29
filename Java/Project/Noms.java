public class Noms{
  private static String[] tab={"Serpent", "Chien","Dragon","Lion","Baleine"};
  private static int id=0;
  private static int cpt=0;

  public static String getNom(){
    if(id==tab.length){
      cpt++;
      id=0;
    }
    if (cpt>0)
      return tab[id++]+"_"+cpt;
    return tab[id++] ;
  }
}
