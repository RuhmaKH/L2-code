import java.util.ArrayList;

public class ArrayRev extends ArrayList<Object> implements Reversible{
  private boolean rev;
  public ArrayRev(){
    super();
    rev = false;
  }
  public void reverse(){
    rev = !rev;
  }
  public Object get(int i){
    return rev ? super.get(size(-1-i)) : super.get(i);
  }
}
