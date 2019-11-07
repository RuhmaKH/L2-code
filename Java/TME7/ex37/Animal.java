public abstract class Animal{
  private String nom;
  private int age;

  protected Animal(String nom, int age){
      this.nom=nom;
      this.age=age;
  }

  protected Animal(String nom){
      this.nom=nom;
      age=1;
  }

public String toString(){
  return nom + " : " + age;
}

public void vieillir(){
  age++;
}

public abstract void crier();


}
