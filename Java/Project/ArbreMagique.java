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
    if (contenu instanceof Gobelin)
      super.image = Gobelin.image;
    if (contenu instanceof Sonic)
      super.image = Sonic.image;
    if (contenu instanceof Yoda)
      super.image = Yoda.image;
  }
}
