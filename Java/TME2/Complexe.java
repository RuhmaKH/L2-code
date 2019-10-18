public class Complexe{
	private double reelle;
	private double imag;

	public Complexe(double reelle, double imag){
		this.reelle=reelle;
		this.imag=imag;
	}

	public Complexe(){
		this(Math.random()*4-2, Math.random()*4-2);
	}
	
	public String toString(){
		return "( "+ reelle+" + "+ imag+" i )";
	}
	
	public Complexe addition( Complexe m){
		return new Complexe( reelle+m.reelle, imag+m.imag);
	}

	public Complexe multiplication(Complexe m){
		return new Complexe(reelle*m.reelle-imag*m.imag,reelle*m.imag+imag*m.reelle);
	}

	public boolean estReel(){
		return (imag==0);
	}
}
