import java.util.Scanner;

public class Fruitier extends Magasin {

    public Fruitier(){
        super("Fruitier", 10);
    }

    public void acheter (Avatar avatar) {
        Scanner sc = new Scanner(System.in);
        String discution = String.format("Vous possédez %.2f € \nJe vend : \n",avatar.getMoney());
        int num, taille;
        do{
            taille = stock.size();
            for (int i = 0; i < taille; i++)
                discution += "\t( " + i + " )-" + stock.get(i).getNom() + " : " + stock.get(i).getPrix() + "\n";
            discution += "\t( " + taille + " )-Vendre";
            discution += "\t( " + (taille + 1) + " )-Partir";
            System.out.println(discution);
            System.out.println("Choisissez l'objet que vous désirez acquérir : ");
            num = sc.nextInt();
            if (num == taille)
                return;
            if (num == taille + 1){
                avatar.vendre(this);
                return;
            }
            money += avatar.acheter(stock.get(num));
            stock.remove(num);
            System.out.println("Voulez-vous acheter autre chose ? [o/n]");
        }while( sc.nextLine() != "n");
    }

    public double vendre (Acc acc){
        double prix = acc.getPrix();
        if (prix > money){
          System.out.println("Je n'ai pas assez d'argent pour vous acheter " + acc.getNom());
          return 0.0;
        }
        money -= prix;
        stock.add(acc);
        return prix;
    }
}