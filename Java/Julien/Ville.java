public class Ville extends El_pays{
	private int nbHabitants;
	private String label;
	
	public Ville(String nom,int nbHabitants){
		super(nom);
		this.nbHabitants=nbHabitants;
		label=Label.getLabel();
		
	}
	public Ville(String nom){
		this(nom, (int)(Math.random()*2000+1));
	}
	public double getsurface(){
		return 	nbHabitants/20;
	}
	public void renouvellerLabel(){
		this.label=Label.getLabel();
		
	}
	public String toString(){
		return super.toString()+" pop= "+this.nbHabitants+" "+label;
	}
}
