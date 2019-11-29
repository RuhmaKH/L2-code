public class Jeu{
  public static void main (String [] args){
  /*Sac s=new Sac(10);
  s.ajouter(new Pomme());
  s.ajouter(new Pomme());
 System.out.println(s);*/


 Creature c= new Creature();
 System.out.println(c);
 c.courir();
 c.ajouter(new Sac());
 c.ajouter(new Pomme());
 c.ajouter(new Pomme());
 c.ajouter(new Pomme());
 c.courir();
 c.manger();
 c.courir();
  }
}
