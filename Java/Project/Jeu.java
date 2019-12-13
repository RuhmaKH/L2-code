import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class Jeu extends JFrame{
  private static Avatar currentPlayer;
  private static Avatar[] players = new Avatar[2];
  private static boolean interact;

  public static void main (String [] args) throws InterruptedException{
    System.setProperty("file.encoding", "UTF-8");
    int taille = 30;
    Monde monde = new Monde (taille,30);


    int NB_TOUR;
    String nom1 = JOptionPane.showInputDialog("Nom du joueur 1 :");
		String nom2 = JOptionPane.showInputDialog("Nom du joueur 2 :");
		if(nom1 == "" || nom1 == null) {
			nom1 = "J1";
		}
		if(nom2 == "" || nom2 == null) {
			nom2 = "J2";
		}

		try {
			NB_TOUR = Integer.parseInt(JOptionPane.showInputDialog("Nombre de Tour :"))*2;
		}catch(NumberFormatException e) {
			NB_TOUR = 10;
			JOptionPane.showMessageDialog(null, "Erreur : Nombre de tour 10");
		}
		Avatar mario = new Avatar(nom1 , 60.5, monde);
    Avatar luigi = new Avatar(nom1, 100.50, monde);

    //*********************************************** ITEMS *********************************************** */
    monde.ajouterItem(mario);
    players[0] = mario;
    monde.ajouterItem(luigi);
    players[1] = luigi;
    monde.initialize();

    //m.afficher();

    ///// Test Graphique
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    monde.setLayout(new BorderLayout());
 		f.add(monde, BorderLayout.CENTER);
    MenuDroite menuDroite = new MenuDroite(players);
    f.add(menuDroite, BorderLayout.EAST);
 		f.pack();
    f.setVisible(true);
    f.setResizable(false);
    f.setFocusable(true);
    f.setLocationRelativeTo(null);
    f.addKeyListener(new MyKeyListener());

    //*********************************************** JEU *********************************************** */
    currentPlayer = mario;
    interact = false;
    for (int i = 1; i < NB_TOUR; i++) {
      //m.afficher();
      System.out.println(mario);
      monde.repaint();
      while (currentPlayer == mario){
        Thread.sleep(1000);  //ralenti l'affichage
      }

      //m.afficher();
      System.out.println(luigi);
      monde.repaint();
      while (currentPlayer == luigi){
        Thread.sleep(1000);  //ralenti l'affichage
      }
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

        ggwp += winner.getNom() + " a gagné.e la course grâce à ses amis :\n";
        daFast = winner.getCreaturePlusRapide();
        for (Creature c : winner.getAmis()){
          ggwp += "\t - ";
          if (c == daFast)
            ggwp += c.getNom() + " is da Speeeed";
          else
            ggwp += c.getNom();
          ggwp += "\n";
        }
        ggwp += "Iels ont parcou.e.s " + chicken + "km.\n";
      }
    }
    else {
      if (amisMario == 0 && amisLuigi == 0)
        ggwp += "La course n'a pas eu lieu car " + mario.getNom() + " et " + luigi.getNom() + " n'ont pas d'ami.e.s";
      else {  if(amisMario == 0)
          ggwp += luigi.getNom() + " a gagné.e la course car " + mario.getNom() + " n'a pas d'ami.e.s";
        else
        ggwp += mario.getNom() + " a gagné.e la course car " + luigi.getNom() + " n'a pas d'ami.e.s";
      }
    }

    System.out.println(ggwp);
  //  sc.close();

  }

  public static Avatar getCurrPlay(){
    return currentPlayer;
  }

  public static void nextPlayer(){
    currentPlayer = currentPlayer == players[0] ? players[1] : players[0];
  }

  public static boolean getInteract(){
    return interact;
  }

  public static void interact(){
    interact = ! interact;
  }
}
