public  class Carre extends Rectangle{
  private final double c;

  public Carre(double c){
    super(c,c);
    this.c = c;
  }

  public void surface(){
    System.out.println("S = "+c*c);
  }

  public void perimetre(){
    System.out.println("P = "+4*c);
  }

  public String toString(){
    return "Carre de côté "+c;
  }
}
