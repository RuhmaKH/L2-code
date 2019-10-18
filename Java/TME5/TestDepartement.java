public class TestDepartement{
  public static void main(String[]args){
    Departement d1 = new Departement();
    Departement d2 = new Departement();
    for(int i=0;i<1000000;i++){
      d1.getPlaque();

    }
    System.out.println(d1.getPlaque());
    System.out.println(d2.getPlaque());
  }
}
