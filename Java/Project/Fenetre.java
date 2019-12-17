import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Fenetre extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    JFrame frame = new JFrame();
    Monde monde = Monde.world;
    MenuDroite menuDroite = new MenuDroite();

    public Fenetre(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Monde.world.setLayout(new BorderLayout());
        frame.add(Monde.world, BorderLayout.CENTER);
        frame.add(menuDroite, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("a"), "clickA"
        );
        frame.getRootPane().getActionMap().put("clickA", Interact.updateState());
    }

    public void paintComponent(Graphics g){
        paintComponent(g);
        monde.repaint();
        menuDroite.repaint();
    }
    
    public void keyReleased (KeyEvent e) { }

    public void keyPressed (KeyEvent e) { }

    public void keyTyped (KeyEvent e) {
        Avatar player = Jeu.getCurrPlay();
        char id = e.getKeyChar();
        switch ( Interact.getState() ) {

            case "talk" :
                Interact.play();
                break;

            case "shop" :
                switch (id) {
                    case 'z' :
                        if (Interact.getCursor() > 0)
                            Interact.cursorUp();
                        break;
                    case 's' :
                        if (Interact.getCursor() < 3)
                            Interact.cursorDown();
                        break;
                    case ' ' :
                        id = '\n';
                    case '\n' :
                        Interact.choose();
                        break;
                }

            case "play" :
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
        repaint();
    }
}