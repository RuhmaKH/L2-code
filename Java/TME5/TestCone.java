public class TestCone{
	public static void main ( String [ ] args ) {
	
		Cone c1=new Cone ( ) ;
		Cone c2=new Cone ( 5.4 , 7.2 ) ;
		
		
		System.out.println( c1+"\n"+c2 ) ;
		System.out.println ( "Le nombre de cones crees est : "+ Cone.getNbCones () ) ;
		System.out.println( "Le nombre de cones crees est : "+ c1.getNbCones () ) ;
		System.out.println( "Le nombre de cones crees est : "+ c2.getNbCones ()) ;
	}
}
