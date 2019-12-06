public class Pile{
	private Machin p[];
	private int sommet;
	public Pile(int n){
		p = new Machin[n];
		sommet =0;
	}
	
	/*public void empiler(Machin m){
		for(int i=0;i<p.length;i++){
			if(p[i]==null){
				p[i]=m;
				return ;
			}
		}
		System.out.println("La pile est pleine");
	}
	
	public Machin depiler(){
		Machin temp;
		for(int i=0;i<p.length-1;i++){
			if(p[i+1]==null){
				temp=p[i];
				p[i]=null;
				return temp;
			}
		}
		temp = p[p.length-1];
		p[p.length-1]=null;
		return temp;
	}
	
	public boolean estVide(){
		for(int i=0;i<p.length;i++){
			if(p[i]!=null){
				return false;
			}
		}
		return true;
	}
	
	public boolean estPleine(){
		for(int i=0;i<p.length;i++){
			if(p[i]==null){
				return false;
			}
		}
		return true;
	}*/
	
	public void empiler(Machin m){
		if(! (sommet>=p.length)){
			p[sommet]=m;
			sommet++;
		}
		else{
			System.out.println("Pas de place");
		}
	}
	
	public Machin depiler(){
		Machin m=null;
		if(sommet!=0){
			sommet--;
			m = p[sommet];
			p[sommet]=null;
		}
		else{
			System.out.println("Aucun element a depiler");
		}
		return m;
	}
	
	public boolean estVide(){
		if(sommet==0)
			return true;
		return false;
	}
	public boolean estPleine(){
		if(sommet==p.length)
			return true;
		return false;
	}
	
	public String toString(){
		String s="";
		for(Machin m : p){
			s+= m+"\n";
		}
		return s;
	}
}
