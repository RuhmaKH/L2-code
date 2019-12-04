public class Creature extends Personnage{
  private Sac sac;

  public Creature(){
    super(Noms.getNom());
    sac = new Sac();
  }
/*
  public Creature(int x, int y){
    super(Noms.getNom(), x, y);
    sac = new Sac();
  }
*/
  public void ajouter(Acc a){
    if (sac.getPoids( ) < 0.5 * getPoids())
      sac.ajouter(a);
    else
      System.out.println("Trop lourd");
  }

  public double getVitesse(){
     double v=0.25 * getPoids() - sac.getPoids();
    if (v<0)
      return 0;
    return v;
  }

  public void manger(){
    Acc obj;
    int nbElm = sac.getNbElements();
    int i;
    for(i=0; i <= nbElm; i++){
      if ((obj = sac.obtenir(0)) instanceof Mangeable) {
        manger((Mangeable) obj);
        System.out.println(getNom() + " a mangé " + obj.toString());
      }
      else
        sac.ajouter(obj);
    }
  }

  public void manger(Mangeable m){
    this.addPoids(m.getPoids());
  }

  public void courir(){
		System.out.println(toString() + String.format(" court à %.2f", getVitesse()) + "km/h avec le\n" + sac);
  }
}
