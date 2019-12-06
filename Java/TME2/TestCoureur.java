public class TestCoureur{
	public static void main ( String [] args){
	Coureur c1= new Coureur(1);
	Coureur c2= new Coureur(2);
	Coureur c3= new Coureur();
	Coureur c4= new Coureur();
	
	//System.out.println(c1.getNuDossard()+" "+c1.getTempsau100()+" "+c1.getALeTemoin());
	
	c1.setAletemoin(c1.getALeTemoin());
	c1.courir();
	System.out.println(c1.toString());
	c1.passeTemoin(c2);
	System.out.println(c1.toString());
	
	c2.courir();
	System.out.println(c2.toString());
	c2.passeTemoin(c3);
	
	c3.courir();
	System.out.println(c3.toString());
	c3.passeTemoin(c4);
	
	c4.courir();
	System.out.println(c4.toString());
	
	System.out.println("Le temps pour le 400m : "+ (c1.getTempsau100()+c2.getTempsau100()+c3.getTempsau100()+c4.getTempsau100()) );
	}
}
	
