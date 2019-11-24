public class Faune{
  public static final int taille;
  private Animal[][] terrain;

  public Faune(int taille){
    this.taille = taille;
    terrain = new Animal[taille][taille];
    int x0, y0,x1,y1,x2,y2;
    for( int i = 0; i < 7; i++){
      do{
        x0 = (int) (Math.random()*(taille-1));
        y0 = (int)(Math.random()*(taille-1));

      } while(terrain[x0][y0] != null);
      terrain[x0][y0] = new Poule(x0, y0);

      do{
        x1 = (int) (Math.random()*(taille-1));
        y1 = (int) (Math.random()*(taille-1));
      } while(terrain[x1][y1] != null);
      terrain[x1][y1] = new Renard(x1, y2);

      do{
        x2 = (int)(Math.random()*(taille-1));
        y2 = (int)(Math.random()*(taille-1));
      } while(terrain[x2][y2] != null);
      terrain[x2][y2] = new Vipere(x2, y2);
   }  
  }
}
