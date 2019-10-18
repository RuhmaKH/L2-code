public class AdresseIP{
	private static int []tab={192,168,0,0};
	
	public static String getAdressIP(){
		if (tab[3]<255){
			tab[3]++;
		}
		else {
			tab[2]++;
			tab[3]=0;
		}
			return tab[0]+"."+tab[1]+"."+tab[2]+"."+tab[3];
	}
}
