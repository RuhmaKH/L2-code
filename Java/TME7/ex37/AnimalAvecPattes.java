public abstract class AnimalAvecPattes extends Animal{
  private int nbPattes;

  protected AnimalAvecPattes( String nom, int age, int nbPattes){
      super(nom, age);
      this.nbPattes=nbPattes;
  }

  protected AnimalAvecPattes(String nom , int nbPattes){
      super(nom);
      this.nbPattes=nbPattes;
  }

  public String toString(){
      return super.toString()+ " avec "+ nbPattes+ " pattes";
  }

  public abstract void crier();

}
