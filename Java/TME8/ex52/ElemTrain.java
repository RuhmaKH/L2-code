public abstract class ElemTrain{
  private static int d=0;
  private final int id;
  private String marque;

protected ElemTrain(String m){
  marque = m;
  id = d++;
}

public String toString(){
  return id+" ("+marque+")";
}

}
