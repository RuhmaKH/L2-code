import java.awt.event.*;

public class MyKeyListener implements KeyListener {
    private boolean leftHeld, rightHeld, upHeld, downHeld;

    public MyKeyListener () {
        leftHeld = false;
        rightHeld = false;
        upHeld = false;
        downHeld = false;
    }

    
  public void keyReleased (KeyEvent e){
    int id = e.getKeyCode();
    switch (id){
        case KeyEvent.VK_LEFT:
            id = KeyEvent.VK_Q;
        case KeyEvent.VK_Q:
            leftHeld = false;
            break;

        case KeyEvent.VK_RIGHT:
            id = KeyEvent.VK_D;
        case KeyEvent.VK_D:
            rightHeld = false;
            break;

        case KeyEvent.VK_UP:
            id = KeyEvent.VK_Z;
        case KeyEvent.VK_Z:
            upHeld = false;
            break;

        case KeyEvent.VK_DOWN:
            id = KeyEvent.VK_S;
        case KeyEvent.VK_S:
            downHeld = false;
            break;
        }
  }

  public void keyPressed (KeyEvent e){
    int id = e.getKeyCode();
    switch (id){
        case KeyEvent.VK_LEFT:
            id = KeyEvent.VK_Q;
        case KeyEvent.VK_Q:
            leftHeld = true;
            break;

        case KeyEvent.VK_RIGHT:
            id = KeyEvent.VK_D;
        case KeyEvent.VK_D:
            rightHeld = true;
            break;

        case KeyEvent.VK_UP:
            id = KeyEvent.VK_Z;
        case KeyEvent.VK_Z:
            upHeld = true;
            break;

        case KeyEvent.VK_DOWN:
            id = KeyEvent.VK_S;
        case KeyEvent.VK_S:
            downHeld = true;
            break;
        }
    }

  public void keyTyped (KeyEvent e){
    Avatar player = Jeu.getCurrent();
    int id = e.getKeyCode();
    switch (id){
        case KeyEvent.VK_LEFT:
            id = KeyEvent.VK_Q;
        case KeyEvent.VK_Q:
            player.seDeplacer(-1, 0);
            break;

        case KeyEvent.VK_RIGHT:
            id = KeyEvent.VK_D;
        case KeyEvent.VK_D:
            player.seDeplacer(+1, 0);
            break;

        case KeyEvent.VK_UP:
            id = KeyEvent.VK_Z;
        case KeyEvent.VK_Z:
            player.seDeplacer(0, +1);
            break;

        case KeyEvent.VK_DOWN:
            id = KeyEvent.VK_S;
        case KeyEvent.VK_S:
            player.seDeplacer(0, -1);
            break;

        case KeyEvent.VK_ENTER:
            player.rencontrerVoisins();
            break;
        }
    }
}