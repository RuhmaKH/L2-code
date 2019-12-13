import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Jeu{
  private static Avatar currentPlayer;
  private static Avatar[] players = new Avatar[2];
  private static boolean interact;
  public static void main (String [] args)throws InterruptedException{
    System.setProperty("file.encoding", "UTF-8");
    int taille = 20;
    Monde monde = new Monde (taille,30);
    Scanner sc = new Scanner(System.in);
    System.out.println("Nom du joueur 1 :");
    Avatar mario = new Avatar(sc.nextLine(), 60.5, monde);
    System.out.println("Nom du joueur 2 :");
    Avatar luigi = new Avatar(sc.nextLine(), 100.50, monde);
		int NB_TOUR = 3;
		
    //*********************************************** ITEMS *********************************************** */
    monde.ajouterItem(mario);
    players[0] = mario;
    monde.ajouterItem(luigi);
    players[1] = luigi;
    monde.initialize();

    ///// Test Graphique
    JFrame f = new JFrame();
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 		f.setContentPane(monde);
 		f.pack();
 		f.setVisible(true);
    f.setFocusable(true);
    f.addKeyListener(new MyKeyListener());

    //*********************************************** JEU *********************************************** */
    currentPlayer = mario;
    interact = false;
    for (int i = 0; i < NB_TOUR; i++) {
      System.out.println(mario);
      monde.repaint();
      while (currentPlayer == mario){
        Thread.sleep(700);  //ralenti l'affichage
      }
      System.out.println(luigi);
      monde.repaint();
      while (currentPlayer == luigi){
        Thread.sleep(700);  //ralenti l'affichage
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
