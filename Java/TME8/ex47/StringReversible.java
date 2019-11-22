public class StringReversible implements Reversible{
  private String str;

  public StringReversible(String str){
    this.str = str;
  }
  public void reverse(){
    String str2 ="";
    for(int i=str.length-1;i>=0;i--){
      str2 += str.charAt(i);
    }
    st = str2;
  }

  public String toString(){
    return str;
  }
}
