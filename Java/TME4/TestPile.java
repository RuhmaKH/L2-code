public class TestPile{
	public static void main(String[]args){
		Pile p = new Pile(2);
		Machin m1 = new Machin("Ugo",19);
		Machin m2 = new Machin("Mathuran",19);
		Machin m3 = new Machin("Julien",8);
		p.empiler(m1);
		System.out.println(p);
		p.empiler(m2);
		System.out.println(p);
		p.empiler(m3);
		System.out.println(p);
		p.depiler();
		System.out.println(p);
		p.depiler();
		System.out.println(p);
		p.empiler(new Machin("Nidhal",56));
		System.out.println(p);
	}
}
