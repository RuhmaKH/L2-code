import java.awt.*;
import javax.swing.*;


public class Creature extends Personnage{
  protected Sac sac;
  private Avatar bff;

  public Creature(){
    super(Noms.getNom());
    sac = new Sac();
    bff = null;
  }

  public Creature(String nom){
    super(nom);
    sac = new Sac();
  }

  public void ajouter(Acc a){
    if (sac.getPoids() + a.getPoids() < 0.5 * getPoids())
      sac.ajouter(a);
    else{
      System.out.println("Trop lourd");
      
    }
  }

  public double getVitesse(){
     double v = 0.25 * getPoids() - sac.getPoids();
    if (v < 0)
      return 0;
    return v;
  }

  public void newBFF (Avatar newBFF){
    if (bff != null)
      System.out.println(String.format("Désolé %s je préfère %s, iel est plus sympa", bff.getNom(), newBFF.getNom()));
    bff = newBFF;
  }

  public void looseBFF (){
    bff = null;
  }

  public void manger(){
    Acc obj;
    int nbElm = sac.getNbElements();
    int i;
    for(i=0; i <= nbElm; i++){
      if ((obj = sac.obtenir(0)) instanceof Mangeable) {
        manger((Mangeable) obj);
        System.out.println(getNom() + " a mangé " + obj.toString());
        continue;
      }
      if ((obj = sac.obtenir(0)) instanceof Sac) {
        Sac sol = sac;
        sac = (Sac) obj;
        manger();
        sac = sol;
      }
      sac.ajouter(obj);
    }
  }

  public void manger(Mangeable m){
    this.addPoids(m.getPoids());
  }

  public void courir(){
		System.out.println(toString() + String.format(" court à %.2f", getVitesse()) + "km/h avec le\n" + sac);
  }

  public void dessiner(Graphics g, Monde m){
    	int tc = m.getTailleCase();
    	g.setColor(Color.CYAN); //couleur courante devient bleu
    	g.fillRect(getX()*tc, getY()*tc, tc, tc); //carre plein
    }
}
