import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Jeu{
  public static void main (String [] args)throws InterruptedException{
    System.setProperty("file.encoding", "UTF-8");
    int taille = 20;
    Monde m = new Monde (taille,50);
    Scanner sc = new Scanner(System.in);
    System.out.println("Nom du joueur 1 :");
    Avatar mario = new Avatar(sc.nextLine(), 60.5, m);
    System.out.println("Nom du joueur 2 :");
    Avatar luigi = new Avatar(sc.nextLine(), 100.50, m);
    int NB_TOUR = 3;
    //*********************************************** ITEMS *********************************************** */

    m.ajouterItem(mario);
    m.ajouterItem(luigi);

    for (int j = 0; j < Math.random() * (taille / 2) + 6; j++)
        m.ajouterItem(new Creature());

    for (int j = 0; j < Math.random() * (taille / 4) + taille / 4; j++)
      m.ajouterItem(new Sac());

    for (int j = 0; j < Math.random() * (taille /4 ) + taille /2; j++)
      m.ajouterItem(new Pomme());


    //m.afficher();

    ///// Test Graphique
    JFrame f = new JFrame();
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		f.setContentPane(m);
 		f.pack();
 		f.setVisible(true);


    //*********************************************** JEU *********************************************** */

    for (int i = 0; i < NB_TOUR; i++) {
      mario.seDeplacer();
      //m.afficher();
      System.out.println(mario);
      Thread.sleep(1000);  //ralenti l'affichage
			m.repaint();
      luigi.seDeplacer();
      //m.afficher();
      System.out.println(luigi);
      Thread.sleep(1000);  //ralenti l'affichage
			m.repaint();
    }

    //*********************************************** GAGNANT *********************************************** */
    int amisMario = mario.getAmis().size();
    int amisLuigi = luigi.getAmis().size();
    double distMario = mario.course();
    double distLuigi = luigi.course();
    String ggwp = "";
    double chicken;
    Avatar winner;
    Creature daFast;
    if (amisMario != 0 && amisLuigi != 0) {
      if( distMario == distLuigi){
        ggwp += mario.getNom() + " et " + luigi.getNom() + " ont gagné(e)s par égalité";
      }
      else{
        if (distMario > distLuigi){
          chicken = distMario;
          winner = mario;
        }
        else {
          chicken = distLuigi;
          winner = luigi;
        }

        ggwp += winner.getNom() + " a gagné la course grâce à ses amis :\n";
        daFast = winner.getCreaturePlusRapide();
        for (Creature c : winner.getAmis()){
          ggwp += "\t - ";
          if (c == daFast)
            ggwp += c.getNom() + " is da Speeeed";
          else
            ggwp += c.getNom();
          ggwp += "\n";
        }
        ggwp += "IZels ont parcou.e.s " + chicken + "km.\n";
      }
    }
    else {
      if (amisMario == 0 && amisLuigi == 0)
        ggwp += "La course n'a pas eu lieu car " + mario.getNom() + " et " + luigi.getNom() + " n'ont pas d'amis";
      else {  if(amisMario == 0)
          ggwp += luigi.getNom() + " a gagné la course car " + mario.getNom() + " n'a pas d'amis";
        else
        ggwp += mario.getNom() + " a gagné la course car " + luigi.getNom() + " n'a pas d'amis";
      }
    }

    System.out.println(ggwp);
    sc.close();

  }
}
