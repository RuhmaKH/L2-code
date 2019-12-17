import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class FullScreen {

    private static final long serialVersionUID = 1L;
    private JButton button = new JButton("Close Meeee");
    private JPanel myPanel = new JPanel();
    private JFrame frame = new JFrame();

    public FullScreen() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        myPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }
        };
        myPanel.setFocusable(true);
        myPanel.add(button);
        frame.add(myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ENTER"), "clickENTER");
        frame.getRootPane().getActionMap().put("clickENTER", updateBol());
        frame.pack();
        frame.setVisible(true);
        updateCol().setEnabled(false);
        updateDol().setEnabled(false);
    }

    private Action updateBol() {
        return new AbstractAction("updateBol") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("updateCol is " + updateBol().isEnabled());
                System.out.println("updateBol is " + updateCol().isEnabled());
                System.out.println("updateDol is " + updateDol().isEnabled());
                updateCol().actionPerformed(e);
                updateDol().actionPerformed(e);
            }
        };
    }

    private Action updateCol() {
        return new AbstractAction("updateCol") {
            private static final long serialVersionUID = 1L;
            private Boolean _enabled = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("updateCol is " + updateCol().isEnabled());
                if (updateCol().isEnabled()) {
                } else {
                }
            }

            public void setEnabled(Boolean bol) {
                _enabled = bol;
            }

            @Override
            public boolean isEnabled() {
                return _enabled;
            }
        };
    }

    private Action updateDol() {
        return new AbstractAction("updateDol") {
            private static final long serialVersionUID = 1L;
            private Boolean _enabled = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("updateDol is " + updateDol().isEnabled());
                if (updateCol().isEnabled()) {
                } else {
                }
            }

            public void setEnabled(Boolean bol) {
                _enabled = bol;
            }

            @Override
            public boolean isEnabled() {
                return _enabled;
            }
        };
    }

    public static void main(String[] args) {
        Runnable doRun = new Runnable() {
            @Override
            public void run() {
                FullScreen fullScreen = new FullScreen();
            }
        };
        SwingUtilities.invokeLater(doRun);
    }
}