public abstract class Acc{
  private static int cpt=0;
  private final int numero;
  private String categorie;

  protected Acc( String categorie){
    this.categorie = categorie;
    numero = cpt++;
  }

  protected String getNom(){
    return categorie;
  }
  public abstract double getPoids();

  public String toString(){
    return categorie+" No " + numero + " " + String.format("pèse %.2f",getPoids()) + "kg";
  }
}
