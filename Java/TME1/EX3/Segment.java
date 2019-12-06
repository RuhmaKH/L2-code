public class Segment{
	private int x;
	private int y;
	
	public Segment ( int extX, int extY){
		extX=x;
		extY=y;
	}
			
	public int longueur (){
		if (x-y>0){
			return x-y;
		}
		else{
			return y-x;
		}
	}

	public String toString (){
		return "Segment ["+x+",["+y+"]"; 	
	}

}

