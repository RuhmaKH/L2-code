public class Main{

    public static void main(String[] args){

        MonTableau tab = new MonTableau(5);
        try{
            for (int i =0 ; i<10; i++){
                tab.ajouter(i);
            }
        }catch(TabPleinException e){
            //System.out.println(e.getMessage());
            System.out.println(e);
            //e.printStackTrace(System.out);
        }

    }
}
