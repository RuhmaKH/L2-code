public class Pills extends Acc implements Mangeable {
    private final static String[] box = {"Bleue", "Rouge", "Verte"};
    private static int id = 0;
    private double poids;

    public Pills(){
        super("Pills " + box[id++]);
        poids = Math.random();
        if(id == box.length - 1)
            id = 0;
    }

    public double getPoids(){
        return poids;
    }

    public double getPrix(){
        return 10 * poids + 8; 
    }

    public String toString(){
        return super.toString();
      }
}