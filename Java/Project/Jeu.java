import java.util.Scanner;

public class Jeu{
  public static void main (String [] args){
    int taille = 20;
    Monde m = new Monde (taille,100);
    Scanner sc = new Scanner(System.in);
    Avatar mario = new Avatar(sc.nextLine(), 60.5, m);
    Avatar luigi = new Avatar(sc.nextLine(), 100.50, m);
    double dist1;
    double dist2;
    double chicken;
    String ggwp;
    Avatar winner;
    Creature daFast;

    //*********************************************** ITEMS *********************************************** */

    m.ajouterItem(mario);
    m.ajouterItem(luigi);

    for(int j = 0; j < Math.random() * (taille / 2) + 6; j++)
        m.ajouterItem(new Creature());

    for(int j = 0; j < Math.random() * (taille / 4) + taille / 4; j++)
      m.ajouterItem(new Sac());

    for(int j = 0; j < Math.random() * (taille /4 ) + taille /2; j++)
      m.ajouterItem(new Pomme());


    m.afficher();

    //*********************************************** JEU *********************************************** */

    for(int i = 0; i < 5; i++){
      mario.seDeplacer();
      m.afficher();

      luigi.seDeplacer();
      m.afficher();
    }

    //*********************************************** GAGNANT *********************************************** */

    if((dist1 = mario.course()) > (dist2 = luigi.course())){
      chicken = dist1;
      winner = mario;
    }
    else{
      chicken = dist2;
      winner = luigi;
    }
    ggwp = winner.getNom() + " a gagné la course grâce à ses amis :\n";
    daFast = winner.getCreaturePlusRapide();
    for(Creature c : winner.getAmis()){
      ggwp += "\t - ";
      if(c == daFast)
        ggwp += c.getNom() + " is da Speeeed";
      else
        ggwp += c.getNom();
      ggwp += "\n";
    }
    ggwp += "Iels ont parcou.e.s " + chicken + "\n";

    System.out.println(ggwp);
    sc.close();
  }
}
