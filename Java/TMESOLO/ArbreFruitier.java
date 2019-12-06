public abstract class ArbreFruitier extends Arbre {
  protected static final int NB_FRUITS_MAX = 1000;


  public ArbreFruitier(String type){
    super(type);
  }

  protected abstract void produireFruit();

  protected abstract int getNbFruits();

  protected abstract double poidsFruits();

  public abstract Fruit[] ramasser();

  public String toString(){
    return super.toString() + "contient "+100+" fruits";
  }

  public boolean equals(Object obj){
    if(this == obj) return true;
    if(obj == null) return false;
    ArbreFruitier af = (ArbreFruitier) obj;
    if(this.getNbFruits() != af.getNbFruits())  return false;
    return true;
  }
}
