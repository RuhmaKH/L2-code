import java.util.ArrayList;


public class Train {
    private Motrice motrice;
    private ArrayList<Wagon> wagon = new ArrayList<Wagon>(); ;

    public Train(Motrice m,ArrayList<Wagon> w){
      motrice = m;
      wagon.addAll(w);
    }

    public Train(Motrice m){
      motrice=m;
    }

    public void ajoute(Wagon w){
      wagon.add(w);
    }

    public String toString(){
      String s=motrice.toString()+"\n";
      for (Wagon z: wagon){
        s+=z.toString()+"\n";
      }
      return "Bienvenue à bord du TGV 6248 en provenance de Paris-Montparnasse et en direction de Quimber, Notre train est composé de \n"+s;
    }

    public double poids(){
        double p=0;
        for(Wagon z:wagon){
          if(z instanceof WMarchandise){
            p += ((WMarchandise) z).poids;
          }
        }
        return p;
    }
}
