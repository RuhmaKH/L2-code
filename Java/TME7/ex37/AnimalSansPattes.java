public abstract class AnimalSansPattes extends Animal{

  protected AnimalSansPattes( String nom, int age){
      super(nom, age);
  }

  protected AnimalSansPattes(String nom ){
      super(nom);
  }

  public String toString(){
      return super.toString();
  }

  public abstract void crier();

}
