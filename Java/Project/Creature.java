public class Creature extends Personnage{
  private Sac sac;

  public Creature(){
    super(Noms.getNom());
<<<<<<< HEAD
    sac=new Sac(1);
  }

  public void ajouter(Acc a){
    if (a.getPoids()+sac.getPoids()<0.5*super.poids)
=======
    sac = new Sac();
  }

  public void ajouter(Acc a){
    if (sac.getPoids( ) < 0.5 * getPoids())
>>>>>>> 28ba4c176067cbddeed05287ea16f2cd645ae644
      sac.ajouter(a);
    else
      System.out.println("Trop lourd");
  }

  public double getVitesse(){
     double v=0.25*super.poids-sac.getPoids();
    if (v<0)
      return 0;
    return v;
  }

  public void manger(){
    /*Acc a,b;
    	int i=0;

		while(sac.getSac_i(i)!=null){
			a=sac.getSac_i(i);

			if(!(a instanceof Sac)){
				b=sac.obtenir(i);
				if(b instanceof Mangeable){
					System.out.println(this.getNom()+" mange "+b.getNom());
					this.addPoids(b.getPoids());
				}
				else{
					ajouter(a);
				}
				i=0;
			}
			else {
				i++;
			}
		}
  }*/
    Acc obj;
    int i;
    for(i=0; i<sac.getNbElements(); i++){
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
<<<<<<< HEAD
		System.out.println(toString()+String.format(" court à %.2f",this.getVitesse())+"km/h avec le\n"+sac.toString());
=======
		System.out.println(toString() + String.format(" court à %.2f", getVitesse()) + "km/h avec le\n" + sac);
>>>>>>> 28ba4c176067cbddeed05287ea16f2cd645ae644
  }
}
