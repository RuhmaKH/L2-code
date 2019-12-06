public class Animal{
    private int x,y;
    private String nom;

    public Animal(int x, int y, String nom){
        this.x=x;
        this.y=y;
        this.nom=nom;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getNom(){
        return nom;
    }

    public String getCoordonnees(){
        return nom + "(" + x + " , " + y + ")";
    }

    public double distance(Animal a){
      return Math.sqrt((x-a.x)*(x-a.x)+(y-a.y)*(y-a.y));
    }

    void fait_le_move(int i, int j){
      x=x+i;
      y=y+j;
    }

    void move(Animal a){

    }
}
