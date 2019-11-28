public class Capital extends Ville{
	private String gourvernement;
	
	public Capital(String nom){
		super(nom);
		gourvernement="gouv";
	}
	public String get_gouv(){
		return gourvernement;
	} 
	
	public String toString(){
		return super.toString()+"("+"captial"+this.get_gouv()+")";
	}
	
}
