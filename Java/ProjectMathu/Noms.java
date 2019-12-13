public class Noms{
  private static String[] tab = {"Serpent", "Chien", "Dragon", "Lion", "Baleine", "Chèvre"};
  private static int id = 0;
  private static int cpt = 1;

  public static String getNom(){
    if(id == tab.length){
      cpt++;
      id = 0;
    }
    return tab[id++] + "_" + cpt;
  }
}
