import java.util.ArrayList;

public class Noisettier extends ArbreFruitier implements CroissanceRapide {
  private ArrayList<Noisette> tab = new ArrayList<Noisette>();

  public Noisettier(){
    super("Noisettier");
  }

  public void produireFruit(){
    if(tab.size < NB_FRUITS_MAX)
      tab.add(new Noisette());
  }

  public int getNbFruits(){
    return tab.size();
  }

  public double poidsFruits(){
    double poids=0;
    for(Noisette p : tab){
      poids += p.getPoids();
    }
    return poids;
  }

  public int getVitesse(){
    return 2;
  }

  public Fruit[] ramasser(){
    int i=0;
    Fruit[] panier = new Fruit[getNbFruits()];
    for(Noisette p : tab){
      panier[i] = p;
      i++;
    }
    tab = new ArrayList<Noisette>();
    return panier;
  }
}
