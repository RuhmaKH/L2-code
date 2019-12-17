public class TabPleinException extends Exception{

    public TabPleinException(String msg){
        super(msg);
    }
    
    public TabPleinException(){
        this("MonTableauOutOfRange");
        
    }
}
