public class Cone{
	private double r ;
	private double h ;
	public static final double PI=3.1415;
	private static int nbCones=0;
 	
 	
 	public Cone (double r , double h) {
		this.r=r ;
		this.h=h ;
		nbCones++;
	}
	
	
	public Cone(){
		this (Math . random( ) *10 ,Math . random( ) *10) ;
	}
	
	public double getVolume ( ) {
		 return 1.0/3* PI* r * r *h ;
 	}
 	
 	public String toString (){
		return "Cone r="+r+" h="+h+" V="+getVolume () ;
		}
		
	public static int getNbCones (){
		return nbCones ; 
	}
}
