import java.util.ArrayList;

public class Poirier extends ArbreFruitier {
  private int nbPoires;
  private ArrayList<Poire> tab= new ArrayList<Poire>();;

  public Poirier(){
    super("Poirier");
    nbPoires = 0;
  }

  public void produireFruit(){
    if(nbPoires<NB_FRUITS_MAX){
      nbPoires++;
      tab.add(new Poire());
    }
  }

  public int getNbFruits(){
    return nbPoires;
  }

  public double poidsFruits(){
    double poids=0;
    for(Poire p : tab){
      poids += p.getPoids();
    }
    return poids;
  }

  public Fruit[] ramasser(){
    int i = 0;
    Fruit[] panier = new Fruit[getNbFruits()];
    for(Poire p : tab){
      panier[i] = p;
      i++;
    }
    tab = new ArrayList<Poire>();
    return panier;
  }

}
