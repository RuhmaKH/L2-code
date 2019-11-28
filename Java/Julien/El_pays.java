public abstract class El_pays{
	private static int cpt_elem;
	private final int numero;
	private final String nom;
	
	public El_pays(String nom){
		this.nom = nom;
		numero=cpt_elem;
		cpt_elem++;
	}
	public abstract double getsurface();
	
	public String toString(){
		String s= String.format("%.2f",getsurface());
		return "no "+this.numero+" "+this.nom+"surface "+s+" km2";
	}
}
