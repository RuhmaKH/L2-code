import java.util.ArrayList;

public class Avatar extends Personnage{
  private ArrayList<Creature> listeAmis;
  private ArrayList<Acc> listeAcc;

  public Avatar(String nom, double poids){
    super(nom,poids);
    listeAmis = new ArrayList<Creature>();
    listeAcc = new ArrayList<Acc>();
  }

  public String toString(){
    return super.toString()+" "+ listeAmis.size()+ " amis(s)"+ listeAcc.size()+" accessoire(s)";
  }

  public boolean estAmi(Creature c){
    if (listeAmis.contains(c))
      return true;
    return false;
  }

  public void devenirAmi(Creature c){
    if (! this.estAmi(c))
      listeAmis.add(c);
  }

  public void PerdreAmi(Creature c){
    if (this.estAmi(c))
      listeAmis.remove(c);
  }

  public void rencontrer(Creature c){
    Acc a = listeAcc.get(0);
    if (a != null){
      c.ajouter(a);
      if (!this.estAmi(c) && a.getPoids()<50)
        this.devenirAmi(c);
    }
    else
      if (this.estAmi(c))
        this.PerdreAmi(c);
  }

  public double course(){
    double distance_t = 0;
    for ( Creature c : listeAmis){
      c.manger();
      c.courir();
      distance_t += c.getVitesse();
    }
    return distance_t;
  }

  public Creature getCreaturePlusRapide(){
    double distance_max=0;
    Creature rapide=null;
    for ( Creature c : listeAmis){
      if (rapide.getVitesse()>c.getVitesse())
        rapide=c;
    }
    return rapide;
  }

  public int compterAccMangeable(){
    int j=0;
    for (int i = 0; i < listeAcc.size(); i++) {
      if (listeAcc.get(i) instanceof Mangeable){
        j++;
      }
    }
    return j;
  }


}
