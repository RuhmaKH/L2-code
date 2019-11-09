public class Orchestre{
  private  int max;
  private Instrument[] tab;
  private  int nbInstruments=0;

  public Orchestre( int max){
      this.max=max;
      tab=new Instrument[max];

  }

  public void ajouterInstrument(Instrument i){
    nbInstruments++;
    if (nbInstruments+1<max){
      tab[nbInstruments]=i;
    }
    else{
      System.out.println("L'Orchestre est rempli");
    }
  }

    public String jouer(){
      String s="";
      System.out.println(nbInstruments);
        for (Instrument i: tab){
          if(i!=null)
            s=s+i.jouer();
                  }
      return s;
    }

}
