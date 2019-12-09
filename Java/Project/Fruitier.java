import java.util.Scanner;

public class Fruitier extends Magasin {

    public Fruitier(){
        super("Fruitier", 10);
    }

    public void acheter (Avatar avatar) {
        Scanner sc = new Scanner(System.in);
        Acc[] stock = super.stock.getTab();
        String mag = "Le magasin vend : \n";
        int num, taille;
        do{
            taille = stock.length;
            for (int i = 0; i < taille; i++)
                mag += "\t( " + i + " )-" + stock[i].getNom() + " : " + stock[i].getPrix() + "\n";
            mag += "\t( " + taille + " )-Vendre";
            mag += "\t( " + (taille + 1) + " )-Partir";
            System.out.println(mag);
            System.out.println("Choisissez l'objet que vous désirez acquérir : ");
            num = sc.nextInt();
            if (num == taille)
                break;
            if (num == taille + 1)
                
            super.money += avatar.acheter(stock[num]);
            (super.stock).obtenir(num);
            System.out.println("Voulez-vous acheter autre chose ? [o/n]");
        }while( sc.nextLine() != "n");
    }

    public void vendre (Avatar avatar) {

    }
}