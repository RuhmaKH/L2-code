import javax.swing.*;
import java.awt.*;

public class Jeu extends JFrame{
  private static Avatar currentPlayer;
  private static Avatar[] players = new Avatar[2];
  private static int NB_TOUR_MAX;
  private static int NB_TOUR;
  private static final int RATE = 1000;

  public static void main (String [] args) throws InterruptedException{
    //System.setProperty("file.encoding", "UTF-8");

    String nom1 = JOptionPane.showInputDialog("Nom du joueur 1 :");
    String nom2 = JOptionPane.showInputDialog("Nom du joueur 2 :");
    
		if(nom1 == "" || nom1 == null) {
			nom1 = "J1";
    }
    
		if(nom2 == "" || nom2 == null) {
			nom2 = "J2";
		}

		try {
			NB_TOUR_MAX = Integer.parseInt(JOptionPane.showInputDialog("Nombre de Tour :"));
		} catch (NumberFormatException e) {
			NB_TOUR_MAX = 10;
			JOptionPane.showMessageDialog(null, "Erreur : Nombre de tour 10");
		}

    Avatar mario = new Avatar(nom1, 60.5, "Avatar1");
    Avatar luigi = new Avatar(nom2, 100.5, "Avatar2");

    //*********************************************** ITEMS *********************************************** */
    Monde.ajouterItem(mario);
    players[0] = mario;
    Monde.ajouterItem(luigi);
    players[1] = luigi;

    //m.afficher();

    ///// Test Graphique
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Monde.world.setLayout(new BorderLayout());
 		f.add(Monde.world, BorderLayout.CENTER);
    MenuDroite menuDroite = new MenuDroite();
    f.add(menuDroite, BorderLayout.EAST);
 		f.pack();
    f.setVisible(true);
    f.setResizable(false);
    f.setFocusable(true);
    f.setLocationRelativeTo(null);
    f.addKeyListener(new MyKeyListener());

    //*********************************************** JEU *********************************************** */
    currentPlayer = mario;
    Interact.play();
    for (int i = 0; i < NB_TOUR_MAX; i++) {
      Monde.world.repaint();
      menuDroite.repaint();
      while (currentPlayer == mario){
        Monde.world.repaint();
        Thread.sleep(RATE);  //ralenti l'affichage
        mario.update();
      }
      //Interact.talk(mario.toString());
      //System.out.println(mario);
      //m.afficher();
      Monde.world.repaint();
      menuDroite.repaint();
      while (currentPlayer == luigi){
        Thread.sleep(RATE);  //ralenti l'affichage
        luigi.update();
      }
      //Interact.talk(luigi.toString());
      //System.out.println(luigi);
      Monde.deplacerCreature();
      NB_TOUR++;
    }
    Interact.end();

    //*********************************************** GAGNANT *********************************************** */
    int amisMario = mario.getAmis().size();
    int amisLuigi = luigi.getAmis().size();
    double distMario;
    double distLuigi;
    String ggwp = "";
    double chicken;
    Avatar winner;
    Creature daFast;
    if (amisMario != 0 && amisLuigi != 0) {
      distMario = mario.course();
      distLuigi = luigi.course();
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
        ggwp += "Ils ont parcou.e.s " + chicken + "km.\n";
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
    Interact.talk(ggwp);
    //System.out.println(ggwp);

  }

  public static Avatar getCurrPlay(){
    return currentPlayer;
  }

  public static void nextPlayer(){
    currentPlayer = currentPlayer == players[0] ? players[1] : players[0];
  }

  public static Avatar[] getPlayers(){
    return players;
  }

  public static int getNb_tours(){
    return NB_TOUR;
  }

  public static int getNb_tours_max(){
    return NB_TOUR_MAX;
  }
}
