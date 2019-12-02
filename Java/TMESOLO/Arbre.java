public abstract class Arbre {
  private String type;
  private int taille;
  private static int nbAbres=0;
  private String id;

  public Arbre(String type){
    this.type = type;
    taille = 100;
    nbAbres++;
    id = String.format("%.2s%02d",type,nbAbres);
  }

  public String getId(){
    return id;
  }

  public void grandir(){
    taille++;
  }

  public static int getNbArbres(){
    return nbAbres;
  }

  public String toString(){
    return type +" "+ id +" "+ taille;
  }
}
