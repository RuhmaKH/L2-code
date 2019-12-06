public class TestFigure{
  public static void main(String[]args){
    Rectangle r = new Rectangle(3,4);
    Carre c=new Carre (5);
    Ellipse e=new Ellipse( 5,3);
    Cercle s= new Cercle(5);

    r.surface();
    r.perimetre();

    c.surface();
    c.perimetre();

    e.surface();
    e.perimetre();

    s.surface();
    s.perimetre();
  }
}
