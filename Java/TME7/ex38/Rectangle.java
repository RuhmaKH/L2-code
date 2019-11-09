public  class Rectangle extends Figure2D{
  private double hauteur,largeur;

public Rectangle(double h, double l){
  hauteur = h;
  largeur = l;
}

  public void surface(){
    System.out.println("S = "+hauteur*largeur);
  }

  public void perimetre(){
    System.out.println("P = "+2*largeur+2*hauteur);
  }

  public String toString(){
    return "Rectangle : h = "+hauteur+" l = "+largeur;
  }
}
