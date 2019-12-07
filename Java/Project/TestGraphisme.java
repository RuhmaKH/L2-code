import java.awt.*;
import javax.swing.*;
public class TestGraphisme{
	private static final int TAILLE_CASE=10;
	private static final int NB_CASES=20;

	public static void main (String []args) throws InterruptedException{
		// Création de la fenetre graphique et ses caracteristiques
		JFrame f=new JFrame();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Création du monde (qui est un panneau)
		Monde m=new Monde(NB_CASES,TAILLE_CASE);
		f.setContentPane(m);//ajout du monde a la fenetre
		f.pack(); //adaptation de la fenetre au panneau
		f.setVisible(true);

		Avatar jake =new Avatar("Jake", 79.5, m); //ajoute Jake dans le monde
		for(int i=0; i<10;i++){
			Thread.sleep(1000);  //ralenti l'affichage
			jake.seDeplacer();
			m.repaint(); // Redessine le graphique
		}

	}

}
