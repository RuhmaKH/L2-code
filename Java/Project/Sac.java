public class Sac extends Acc{
  private Acc[] tab;

  public Sac(int n) {
    super("Sac");
    tab= new Acc[n];
  }

  public Sac(){
    super("Sac");
    tab=new Acc[(int)(Math.random()*9) +1];
  }

  public Acc getSac_i(int i){
    if (i<0 && i>(tab.length-1))
      return null;
    return tab[i];
  }

  public int size(){
    return tab.length;
  }

  public void ajouter(Acc a){
    boolean test=true;
    for (int i=0; i<tab.length;i++){
      if (tab[i]==null){
        tab[i]=a;
        test=false;
        break;
      }
    }
    if (test)
      System.out.println("Pas la place");
  }

  public Acc obtenir(int i){
    if (i<getNbelements()){
      Acc finale=tab[i];
      for (int j=i;j<tab.length-1;j++){
        tab[j]=tab[j+1];
      }
      tab[tab.length-1]=null;
      return finale;
    }
    return null;
  }

  public int getNbelements(){
    int cpt=0;
    for (int i=0; i<tab.length;i++){
        if (tab[i]!=null)
          cpt++;
    }
    return cpt;
  }
  public double getPoids(){
    double poids=0;
    for(int i=0; i<getNbelements(); i++){
        poids+=tab[i].getPoids();
    }
    return poids;
  }

  public String toString(){
    String s=""+super.toString()+" contient "+getNbelements()+" accessoires sur "+size()+" :\n";
    for(int i=0; i<getNbelements(); i++){
       s=s+"\t"+tab[i].toString()+"\n";
    }
    return s;
  }
}
