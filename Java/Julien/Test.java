public class Test{
	public static void main(String[]args){
		System.out.println(Foret.nbForet());
		Foret foret1=new Foret("petitbois");
		System.out.println(foret1.toString());
		Ville ville1=new Ville("lyon");
		System.out.println(ville1.toString());
		Capital capital1=new Capital("paris");
		System.out.println(capital1.toString());
		
	}
}
