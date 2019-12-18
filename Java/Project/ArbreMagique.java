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
      super.image = Gobelin.gobelin.getImage();
    if (contenu instanceof Sonic)
      super.image = Sonic.sonic.getImage();
    if (contenu instanceof Yoda)
      super.image = Yoda.yoda.getImage();
  }
}
