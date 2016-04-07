package game.of.life;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Patryk
 */
public class Organism extends JPanel {

    private final static Color ALIVE_COLOR = new Color(40, 40, 40);
    private final static Color DEAD_COLOR = new Color(30, 30, 30);
    private final static Color ALIVE_BORDER_COLOR = new Color(30, 220, 30);
    private final static Color DEAD_BORDER_COLOR = new Color(32, 32, 32);
    private final static Color CURSOR_OVER_COLOR = new Color(110, 110, 110);
    private final static int NUMBER_OF_AGE_COLORS = 40;

    //-----------------------------------------------------------
    private int age;
    private boolean alive;

    public Organism(int x, int y, int size) {
        super();
        this.setBounds(x, y, size, size);
        this.setBackground(DEAD_COLOR);
        this.setBorder(BorderFactory.createLineBorder(DEAD_BORDER_COLOR, 1));
        this.age = 0;

        addMouseListener();
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
        setBackground(DEAD_COLOR);
        this.setBorder(BorderFactory.createLineBorder(DEAD_BORDER_COLOR, 1));
    }

    public void changeState() {
        alive = !alive;

        if (alive) {
            setBackground(ALIVE_COLOR);
            this.setBorder(BorderFactory.createLineBorder(ALIVE_BORDER_COLOR, 1));
        } else {
            setBackground(DEAD_COLOR);
            this.setBorder(BorderFactory.createLineBorder(DEAD_BORDER_COLOR, 1));
        }

    }

    public void nextGeneration() {
        if (this.alive) {
            age++;
            this.setBackground(Color.getHSBColor((float) age / NUMBER_OF_AGE_COLORS, 1, 1));
            this.setBorder(BorderFactory.createLineBorder(Color.getHSBColor((float) age / NUMBER_OF_AGE_COLORS, (float) 0.8, 1)));
        } else {
            age = 0;
        }
    }

    public int getAge() {
        return age;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    private void addMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeState();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (!alive) {
                    setBackground(CURSOR_OVER_COLOR);
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    changeState();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!alive) {
                    setBackground(DEAD_COLOR);
                }
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

}
