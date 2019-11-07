public  class Cercle extends Ellipse{
  private final double r;

  public Cercle(double r){
    super(r,r);
    this.r = r;
  }

  public void surface(){
    System.out.println("S = "+Math.PI*r*r);
  }

  public void perimetre(){
    System.out.println("P = "+2*Math.PI*r);
  }

  public String toString(){
    return "Cercle de rayon "+r;
  }
}
