import java.awt.event.KeyEvent;
import java.awt.event.*;

public class MyKeyListener implements KeyListener {
    private static boolean leftHeld, rightHeld, upHeld, downHeld;

    public MyKeyListener () {
        leftHeld = false;
        rightHeld = false;
        upHeld = false;
        downHeld = false;
    }

    public static int[] getDirection(){
        int[] coord = {0, 0};
        if (leftHeld){
            coord[0] = -1;
            coord[1] = 0;
        }
        if (rightHeld){
            coord[0] = +1;
            coord[1] = 0;
        }
        if (upHeld){
            coord[0] = 0;
            coord[1] = -1;
        }
        if (downHeld){
            coord[0] = 0;
            coord[1] = +1;
        }
        return coord;
    }
    
    public void keyReleased (KeyEvent e) {
        if (Jeu.getInteract() != "play")
            return;
        char id = e.getKeyChar();
        switch (id) {
            case 'q':
                leftHeld = false;
                break;
            case 'd':
                rightHeld = false;
                break;
            case 'z':
                upHeld = false;
                break;
            case 's':
                downHeld = false;
                break;
        }
    }

    public void keyPressed (KeyEvent e) {
        if (Jeu.getInteract() != "play")
            return;
        char id = e.getKeyChar();
        switch (id) {
            case 'q':
                leftHeld = true;
                break;
            case 'd':
                rightHeld = true;
                break;
            case 'z':
                upHeld = true;
                break;
            case 's':
                downHeld = true;
                break;
        }
    }

    public void keyTyped (KeyEvent e) {
        if (Jeu.getInteract().contains("talk")){
            Jeu.interact("play");
            return;
        }
        if (Jeu.getInteract() != "play")
            return;
        Avatar player = Jeu.getCurrPlay();
        char id = e.getKeyChar();
        switch (id) {
            case 'q' :
                player.seDeplacer(-1, 0);
               break;
            case 'd' :
                player.seDeplacer(+1, 0);
                break;
            case 'z' :
                player.seDeplacer(0, -1);
                break;
            case 's' :
                player.seDeplacer(0, +1);
                break;
            case ' ' :
                id = '\n';
            case '\n' :
                player.rencontrerVoisins();
                break;
        }
    }
}