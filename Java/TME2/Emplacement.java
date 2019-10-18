public class Emplacement{
	private Carte carte;
	private String nom;
	
	public String toString(){
		return nom+" : "+carte.toString();
	}
	
	public boolean estVide(){
		return carte ==null;
	}
	public boolean poser(Carte carte){
		if(! estVide()){
			return false;
		}
		this.carte = carte;
		return true;
	}
	
	public Carte enlever(){
		if (est)
		}
}
