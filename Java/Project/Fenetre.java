import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

public class Fenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    JFrame frame = new JFrame();
    Monde monde = Monde.world;
    MenuDroite menuDroite = new MenuDroite();
    MenuGauche menuGauche = new MenuGauche();

    public Fenetre() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Monde.world.setLayout(new BorderLayout());
        frame.add(monde, BorderLayout.CENTER);
        frame.add(menuDroite, BorderLayout.EAST);
        frame.add(menuGauche, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        addKeyBinding(KeyEvent.VK_Q, "left", (evt) -> {
            if (Interact.getState() == "play"){
                Jeu.getCurrPlay().seDeplacer(-1, 0);
                repaint();
            }
        });
        addKeyBinding(KeyEvent.VK_D, "right", (evt) -> {
            if (Interact.getState() == "play"){
                Jeu.getCurrPlay().seDeplacer(+1, 0);
                repaint();
            }
        });
        addKeyBinding(KeyEvent.VK_Z, "forward", (evt) -> {
            switch (Interact.getState()) {
                case "play" :
                    Jeu.getCurrPlay().seDeplacer(0, -1);
                    break;
                case "shop" :
                    if (Interact.getCursor() > 0)
                    Interact.cursorUp();
                    break;
            }
            repaint();
        });
        addKeyBinding(KeyEvent.VK_S, "down", (evt) -> {
            switch (Interact.getState()) {
                case "play" :
                    Jeu.getCurrPlay().seDeplacer(0, +1);
                    break;
                case "shop" :
                    if (Interact.getCursor() < Interact.getMaxCursor())
                        Interact.cursorDown();
                    break;
            }
            repaint();
        });
        addKeyBinding(KeyEvent.VK_SPACE, "action", (evt) -> {
            switch (Interact.getState()) {
                case "play" :
                    Jeu.getCurrPlay().rencontrerVoisins();
                    break;
                case "shop" :
                    Interact.choose();
                    break;
                case "talk" :
                    Interact.play();
                    break;
            }
            repaint();
        });
    }

    public void paintComponent(Graphics g) {
        paintComponent(g);
        monde.repaint();
        menuDroite.repaint();
    }

    private void addKeyBinding(int keyCode, String id, ActionListener listener) {
        InputMap im = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);

        am.put(id, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });

    }
}
