import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.awt.event.*;

public class Jeu extends JFrame implements ActionListener {

  public void ActionPerformed (ActionEvent e){
        System.out.println( e);
    }

  public static void main (String [] args) throws InterruptedException{
    System.setProperty("file.encoding", "UTF-8");
    int taille = 20;
    Monde m = new Monde (taille,40);
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
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    m.setLayout(new BorderLayout());
 		f.add(m,BorderLayout.CENTER);

    JPanel f1 = new JPanel( new FlowLayout());
    JButton b1 = new JButton ("Gauche");
    b1.addActionListener(this);



  f1.add(b1);
    b1.setPreferredSize( new Dimension( 100, 60) );
    JButton b2 = new JButton ("Haut");
    b2.setPreferredSize( new Dimension( 100, 60) );
    f1.add(b2);
    JButton b3 = new JButton ("Droite");
    b3.setPreferredSize( new Dimension( 100, 60) );
    f1.add(b3);
    JButton b4 = new JButton ("BAS");
    b4.setPreferredSize( new Dimension( 100, 60) );
    f1.add(b4);
    JButton b5 = new JButton ("STOP ICI");
    b5.setPreferredSize( new Dimension( 100, 60) );
    f1.add(b5);
    f1.setBackground(Color.RED);
    f.add(f1,BorderLayout.SOUTH);


    //f.setOpacity(0);
    f.setLocationRelativeTo(null);
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
