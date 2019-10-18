public class TestComplexe{
	public static void main(String[]args){
		Complexe c1 = new Complexe(2,7);
		Complexe c2 = new Complexe(1,7);
		Complexe c3 = new Complexe(4.5,7);
		System.out.println("c1 ="+c1.toString());
		System.out.println("c2 ="+c2.toString());
		System.out.println("c3 ="+c3.toString());
		System.out.println("c1 est réel : "+c1.estReel());
		System.out.println("c2 est réel : "+c2.estReel());
		System.out.println("c3 est réel : "+c3.estReel());
		Complexe c4 = c1.addition(c3);
		System.out.println("c1+c3="+c4.toString());
		Complexe c5 = c1.multiplication(c2);
		System.out.println("c1*c2="+c5.toString());
	}
}
