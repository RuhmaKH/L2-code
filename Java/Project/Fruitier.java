import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Fruitier extends Magasin {

	public Fruitier(){
	  super("Fruitier");
		initialize();
	}

	private void initialize(){
		for (int i = 0; i < Math.random() * 8 + 5; i++)
			stock.add(new Pomme());
	}

	public void acheter (Avatar avatar) {
		Scanner sc = new Scanner(System.in);
		String discution = "";
		int num, taille;
		double plus;
		do{
			if ( (taille = stock.size()) != 0){
				discution = String.format("Vous possédez %.2f €\nJe vend : \n", avatar.getMoney());

				for (int i = 0; i < taille; i++)
					discution += String.format("\t( %d )-%s : %.2f €\n", i, stock.get(i).getNom(), stock.get(i).getPrix());
			}
			else
				discution = "Je ne possède plus rien à vendre\nVoulez-vous :\n";
			discution += "\t( " + taille + " )-Vendre ?\n";
			discution += "\t( " + (taille + 1) + " )-Partir ?";
			System.out.println(discution);
			System.out.println("Choisissez l'objet que vous désirez acquérir : ");
			do {
				num = sc.nextInt();
			}while(num < 0 || num > taille + 1);
			if (num == taille){
				avatar.vendre(this);
				return;
			}
			if (num == taille + 1)
				return;
			if ( (plus = avatar.acheter(stock.get(num))) != 0.0){
				money += plus;
				stock.remove(num);
			}
		}while(true);
	}

	public double vendre (Acc acc){
		double prix = acc.getPrix() * 0.9;
		if (prix > money){
		  System.out.println("Je n'ai pas assez d'argent pour vous acheter " + acc.getNom());
		  return 0.0;
		}
		money -= prix;
		stock.add(acc);
		return prix;
	}

  public void dessiner ( Graphics g , Monde m){}
}
