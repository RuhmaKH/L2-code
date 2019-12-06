import java.util.ArrayList ;

public class Menagerie{
  private ArrayList<Animal> menagerie;

  public Menagerie(){
      menagerie=new ArrayList<Animal>();
  }

  public void ajouter(Animal a){
    menagerie.add(a);
  }

  public String toString(){
    String s="";
    for(Animal a : menagerie)
      s += a+"\n";
    return s;
  }

  public void midi(){
    for(Animal a : menagerie)
      a.crier();
  }

  public void vieillirTous(){
    for(Animal a : menagerie)
      a.vieillir();
  }
}
