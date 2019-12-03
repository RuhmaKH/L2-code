public class Jardin {
  private Arbre[][] terrain;
  private int nbC;
  private int nbL;
  private static int nbArbres=0;


  public Jardin(int nbL, int nbC){
    this.nbL = nbL;
    this.nbC = nbC;
    terrain = new Arbre[nbL][nbC];
  }

  public boolean planter(Arbre a, int i, int j){
    if(terrain[i][j]==null){
      terrain[i][j]=a;
      nbArbres++;
      return true;
    }
    return false;
  }

  public boolean planter(Arbre a){
    return planter(a,(int) Math.random()*nbL,(int) Math.random()*nbC);
  }

  public static int compterArbres(){
    return nbArbres;
  }

  public int compterFruits(){
    int fruits=0;
    for(int i=0;i<nbL;i++)
      for(int j=0;j<nbC;j++)
        if(terrain[i][j] instanceof ArbreFruitier)
          fruits += ((ArbreFruitier) terrain[i][j]).getNbFruits();
    return fruits;
  }

  public void afficher(){
    String t="";
    for(int i=0;i<nbL;i++){
      for(int j=0;j<nbC;j++){
        if(terrain[i][j] instanceof Arbre)
          t += terrain[i][j].toString()+"\t";
        else
          t += "....\t";
      }
      t += " \n";
    }
    System.out.println(t);
  }
}
