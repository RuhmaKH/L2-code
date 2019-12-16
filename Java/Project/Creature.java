import java.awt.*;

public class Creature extends Personnage{
  protected Sac sac;
  private Avatar bff;
  private Image image= null;

  public Creature(){
    super(Noms.getNom());
    sac = new Sac();
    bff = null;
    image = Images.getImage(Noms.getTab_icourant());
  }

  public Creature(String nom){
    super(nom);
    sac = new Sac();
    image = Images.getImage(nom);
  }

  public Image getImage(){
    return image;
  }

  public void ajouter(Acc acc){
    if (sac.getPoids() + acc.getPoids() < 0.5 * getPoids())
      sac.ajouter(acc, false);
    else{
      Interact.talk( acc.getNom() + " est trop lourd pour " + getNom() + ", l'objet est tombé par terre" );
      //System.out.println(acc.getNom() + " est trop lourd pour " + getNom() + ", l'objet est tombé par terre");
      Monde.drop(acc, getX(), getY());
    }
  }

  public double getVitesse(){
     double v = 0.25 * getPoids() - sac.getPoids();
    if (v < 0)
      return 0;
    return v;
  }

  protected void newBFF (Avatar newBFF){
    if (bff != null && bff != newBFF){
      Interact.talk( String.format("Désolé %s je préfère %s, iel est plus sympa", bff.getNom(), newBFF.getNom()) );
      //System.out.println(String.format("Désolé %s je préfère %s, iel est plus sympa", bff.getNom(), newBFF.getNom()));
      bff.perdreAmi(this);
    }
    if (newBFF == bff)
      bff = null;
    else
      bff = newBFF;
  }

  public void manger(){
    Acc obj;
    int nbElm = sac.getNbElements();
    int i;
    for(i=0; i <= nbElm; i++){
      if ((obj = sac.obtenir(0)) instanceof Mangeable) {
        manger((Mangeable) obj);
        Interact.talk( getNom() + " a mangé " + obj.toString() );
        //System.out.println(getNom() + " a mangé " + obj.toString());
        continue;
      }
      if ((obj = sac.obtenir(0)) instanceof Sac) {
        Sac sol = sac;
        sac = (Sac) obj;
        manger();
        sac = sol;
      }
      sac.ajouter(obj, false);
    }
  }

  public void manger(Mangeable m){
    this.addPoids(m.getPoids());
  }

  public void courir(){
    Interact.talk( toString() + String.format(" court à %.2f", getVitesse()) + "km/h avec le\n" + sac );
		//System.out.println(toString() + String.format(" court à %.2f", getVitesse()) + "km/h avec le\n" + sac);
  }

  public void dessiner(Graphics g){
    	int tc = Monde.tailleCase;
    	g.drawImage(image,getX()*tc+1, getY()*tc+1, tc-2, tc-2, Monde.world);
    }
}
