public  class Ellipse extends Figure2D{
  private double a,b;

  public Ellipse(double a,double b){
    this.a = a;
    this.b = b;
  }

  public void surface(){
    System.out.println("S = "+Math.PI*a*b);
  }

  public void perimetre(){
    System.out.println("P = "+2*Math.PI*Math.sqrt((a*a+b*b)/2));
  }

  public String toString(){
    return "Rectangle : demi-grand-axe = "+a+" demi-petit-axe = "+b;
  }
}
