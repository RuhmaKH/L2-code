public class TestVillageois{
	public static void main ( String [] args ){ 
		
		Villageois v1=new Villageois ("Julien");
		Villageois v2=new Villageois ("Nidhal");
		Villageois v3=new Villageois ("Ali");
		Villageois v4=new Villageois ("Mathu");

		System.out.println(v1.toString());
		System.out.println(v2.toString());
		System.out.println(v3.toString());
		System.out.println(v4.toString());

		if(v1.poidsSouleve()+v2.poidsSouleve()+v3.poidsSouleve()+v4.poidsSouleve()<100){
			System.out.println("LE VILLAGE EST MORT");
		}
		else
		{ System.out.println("LE VILLAGE SURVIT");
		}
	}
}
