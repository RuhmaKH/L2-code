import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Jeu{
  public static void main (String [] args)throws InterruptedException{

    int taille = 20;
    Monde m = new Monde (taille,30);
    Scanner sc = new Scanner(System.in);
    System.out.println("Nom du joueur 1 :");
    Avatar mario = new Avatar(sc.nextLine(), 60.5, m);
    System.out.println("Nom du joueur 2 :");
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

    ///// Test Graphique
       		JFrame f=new JFrame();
    		f.setLocationRelativeTo(null);
    		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    		f.setContentPane(m);
    		f.pack();
    		f.setVisible(true);


    //*********************************************** JEU *********************************************** */

    for(int i = 0; i < 5; i++){
      mario.seDeplacer();
      m.afficher();
      System.out.println(mario);
      Thread.sleep(1000);  //ralenti l'affichage
			m.repaint();
      luigi.seDeplacer();
      m.afficher();
      System.out.println(luigi);
      Thread.sleep(1000);  //ralenti l'affichage
			m.repaint();
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





		for(int i=0; i<2;i++){
      Thread.sleep(1000);  //ralenti l'affichage
			mario.seDeplacer();
			mario.rencontrerVoisins();
			m.repaint();
			m.afficher();
		}

  }
}
