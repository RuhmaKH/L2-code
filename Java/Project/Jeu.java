public class Jeu{
  public static void main (String [] args){
  /*Sac s=new Sac(10);
  s.ajouter(new Pomme());
  s.ajouter(new Pomme());
  System.out.println(s);*/
  Monde m = new Monde (10);
  m.afficher();
  Creature c = new Creature();
  c.courir();
  c.ajouter(new Sac());
  c.ajouter(new Pomme());
  c.ajouter(new Pomme());
  c.ajouter(new Pomme());
  c.courir();
  c.manger();
  c.courir();

  Avatar bob = new Avatar("Bob", 100.50, m);
  m.ajouterItem(bob);
  m.afficher();
  m.ajouterItem(new Pomme());
  m.ajouterItem(new Sac());
  m.ajouterItem(c);
  bob.seDeplacer();
  m.afficher();
  }
}
