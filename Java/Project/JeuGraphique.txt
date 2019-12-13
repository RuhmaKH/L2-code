import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JeuGraphique extends JFrame implements MouseListener{
	private static final int TAILLE_CASE=40;
	@SuppressWarnings("unused")
	private static final int NB_CASES=40;
	private Monde m;
	private Avatar player1;
	private Avatar player2;
	private boolean swap;
	private boolean phaseDeTour;
	private int nbTour;
	private int nbTourMax;
	private GUI g;
	
	
	public JeuGraphique() {
		super("TohadanLand");
	}
	
	public static void main(String[] args) {
		JeuGraphique j =new JeuGraphique();
		j.setupScreen();
		j.setupGame();
	}
	
	public void setupScreen() {
		this.addMouseListener((MouseListener) this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		//initialisation de monde et l'interface graphique par rapport a la taille de l'ecran
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		m = new Monde((tailleEcran.width/2)/TAILLE_CASE,TAILLE_CASE);
		g = new GUI((tailleEcran.width)/3,(tailleEcran.width/2) );
		
		
		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau, BoxLayout.LINE_AXIS));
		panneau.add(m);
		panneau.add(g);
		add(panneau);

		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void  setupGame() {
		nbTour = 0;
		swap = true;
		
		String nom1 = JOptionPane.showInputDialog("Nom du joueur 1 :");
		String nom2 = JOptionPane.showInputDialog("Nom du joueur 2 :");
		if(nom1 == "" || nom1 == null) {
			nom1 = "Joueur1";
		}
		if(nom2 == "" || nom2 == null) {
			nom2 = "Joueur2";
		}
		
		try {
			nbTourMax = Integer.parseInt(JOptionPane.showInputDialog("Nombre de Tour (PAR JOUEUR) :"))*2;
		}catch(NumberFormatException e) {
			nbTourMax = 10;
			JOptionPane.showMessageDialog(null, "Erreur dans la saisie du Nombre de Tour, valeur initialisee a 10");
		}
		
		player1 = new Avatar ( nom1 ,(Math.random()*100)+20,m,"RED");
		player2 = new Avatar ( nom2 ,(Math.random()*100)+20 ,m,"PURPLE");
		
		for (int i =0; i < 100;i++){
			
			if (Math.random()<0.4) m.ajouterItem(new Pomme());
			if (Math.random()<0.6) m.ajouterItem(new Creature());
			if (Math.random()<0.2) m.ajouterItem(new Fou());
			
			if (Math.random()<0.01) m.ajouterItem(new Sac());
			if (Math.random()<0.05) m.ajouterItem(new Poison());
			if (Math.random()<0.05) m.ajouterItem(new Botte());
			
			if (Math.random()<0.03) m.ajouterItem(new BonusCreature());
			if (Math.random()<0.03) m.ajouterItem(new MalusCreature());
		}
		
		phaseDeTour = true;
		m.repaint();
		g.update(nbTourMax,nbTour,player1.getNom(),player1.getTexteEvenement(),player1.getTexteAmis(),player1.getTexteAcc());
		g.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 if(phaseDeTour){
			    if(swap) {
			    	
				    player1.setX(e.getX()/m.getTailleCase());
				    player1.setY((e.getY()/m.getTailleCase()));
				    player1.setY(player1.getY()-1);
				    player1.rencontrerVoisins();
				    
				    g.update(nbTourMax,nbTour,player2.getNom(),player2.getTexteEvenement(),player2.getTexteAmis(),player2.getTexteAcc());
				    g.repaint();
				   
				    
				    swap = !swap;
			    }else {

				    player2.setX(e.getX()/m.getTailleCase());
				    player2.setY((e.getY()/m.getTailleCase()));
				    player2.setY(player2.getY()-1);
				    player2.rencontrerVoisins();
				    
				    g.update(nbTourMax,nbTour,player1.getNom(),player1.getTexteEvenement(),player1.getTexteAmis(),player1.getTexteAcc());
				    g.repaint();
				    
				    
				    swap = !swap;
			    }
			    nbTour++;
			    m.repaint();
			    if(nbTour > nbTourMax){
			        phaseDeTour = false;
			        //avatar gagant la course
			        Avatar ga = m.endGame(player1,player2);
			        if(ga != null){
			            g.update(nbTourMax,nbTourMax, ga.getNom(), ga.getTexteEvenement(), ga.getTexteAmis(), ga.getTexteAcc());
			        }else{
			            g.update(nbTourMax,nbTourMax, "", "","","");
			        }
			        g.repaint();
			    }
		    }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}

