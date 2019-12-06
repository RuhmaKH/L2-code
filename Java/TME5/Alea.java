public class Alea{
	private Alea(){
	}
	public static char lettre(){
		 return (char)(Math.random()*('z'-'a'+1)+'a');
	}
	public static String chaine(){
		String s="";
		for(int i=0;i<10;i++){
			s+=lettre();
		}
		return s;
	}
	
	public static void main ( String []args){
		System.out.println(chaine());
		
	}
}
