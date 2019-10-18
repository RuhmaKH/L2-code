public class Departement{
	private static int comptDepartement=1;
	private final int numDepartement;
	private int num;
	private char lettre1='A';
	private char lettre2='A';

	public Departement(){
		numDepartement = comptDepartement++;
	}

	public String getPlaque(){
		num++;
		if(num>999){
			num=0;
			lettre2++;
			if(lettre2>'Z'){
				lettre2='A';
				lettre1++;
				if(lettre1>'Z'){
					num=999;
					lettre1 = 'Z';
					lettre2 = 'Z';
				}
			}
		}
		return num+" "+lettre1+lettre2+" "+numDepartement;
	}
}
