public class TestSegment{
	public static void main(String[] args){
		Segment s1= new Segment(6,8);
		Segment s2= new Segment(12,5);
	
	
		if( s1.longueur()>s2.longueur()){
			System.out.println("Le segment le plus long est s1");
			} 
		else{
			System.out.println("Le segment le plus long est s2");
		}
	}
}
