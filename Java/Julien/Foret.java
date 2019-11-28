public class Foret extends El_pays{
	private final int surface=(int)(Math.random()*499+1);
	private int nb_arbres;
	private static int cpt_foret=0;
	
	
	public Foret(String nom){
		super(nom);
		nb_arbres=10000*surface;
		cpt_foret++;
	} 
	public int nbAbres(){
		return nb_arbres;
	}
	public double getsurface(){
		return this.surface;
	}
	public static int nbForet(){
		return cpt_foret;
	}
	public String toString(){
		return super.toString()+this.nbAbres()+" arbres";
	}
}
