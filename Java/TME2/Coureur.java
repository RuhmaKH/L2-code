public class Coureur{
	private int nuDossard;
	private double tempsau100;
	private boolean aLeTemoin;

	public Coureur (int n){
		nuDossard=n;
		tempsau100=Math.random()*4+12;
		aLeTemoin=false;
	}
	public Coureur(){
		this((int) (Math.random()*1000+5));
	}

	public int getNuDossard(){
		return nuDossard;
	}	
	
	public double getTempsau100(){
		return tempsau100;
	}

	public boolean getALeTemoin(){
		return aLeTemoin;
	}
	
	public void setAletemoin(boolean b){
		aLeTemoin = ! b;
	}

	public String toString(){
		if (aLeTemoin){
			return "Coureur : "+nuDossard+" tempsAu100 : "+tempsau100+" aLeTemoin: oui";
		}
		return "Coureur : "+nuDossard+" tempsAu100 : "+tempsau100+" aLeTemoin: non";
	}

	public void passeTemoin (Coureur c){
		setAletemoin(aLeTemoin);
		c.setAletemoin(c.getALeTemoin());
		System.out.println("Moi, coureur "+nuDossard+", je passe le témoin au coureur "+c.getNuDossard());
	}
	public void courir(){
		System.out.println("Je suis le coureur "+nuDossard+" et je cours");
	}
}

	









