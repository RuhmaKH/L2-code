public class ArbreMagique extends Arbre{
  private Item contenu;

  public ArbreMagique (){
    super("ArbreMagique");
    contenu= null;
  }

  public Item getContenu(){
    return contenu;
  }

  public void ajouter(Item item){
      contenu= item;
  }

  public void changeImage(){
    String nom =contenu.getNom();
    if (nom== "Gobelin")
      super.image= Images.getImage("Gobelin");
    if (nom== "Sonic")
      super.image= Images.getImage("Sonic");
    if (nom== "Yoda")
      super.image= Images.getImage("Yoda");
  }
}
