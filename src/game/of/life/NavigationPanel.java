package game.of.life;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Patryk
 *
 */
public class NavigationPanel extends JPanel {

    private final static Color PANEL_COLOR = new Color(50, 50, 50);
    private final static Color PANEL_BORDER_COLOR = new Color(52, 52, 52);

    //--------------------------------------------------
    private GameWindow gWindow;
    private JLabel generationLabel, populationSizeLabel, oldestOrganismLabel, topPopulationSizeLabel,
            infoLabel, generationInfoLabel, populationSizeInfoLabel, topPopulationSizeInfoLabel, oldestOrganismInfoLabel;
    private JButton startPauseButton, resetButton;

    public NavigationPanel(int x, int y, int width, int height, GameWindow gWindow) {
        super();
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(PANEL_COLOR);
        this.setBorder(BorderFactory.createLineBorder(PANEL_BORDER_COLOR, 1));
        this.gWindow = gWindow;

        initUI();
    }

    private void initUI() {
        infoLabel = new HeaderLabel("Game of Life", 0, 15, this.getWidth(), 30);

        generationInfoLabel = new InfoLabel("Generation:", 25, 60, 130, 30);
        populationSizeInfoLabel = new InfoLabel("Population:", 25, 90, 130, 30);
        topPopulationSizeInfoLabel = new InfoLabel("Top Pop:", 25, 120, 130, 30);
        oldestOrganismInfoLabel = new InfoLabel("Oldest:", 25, 150, 130, 30);

        generationLabel = new ValueLabel("0", 132, 60, this.getWidth(), 30);
        populationSizeLabel = new ValueLabel("0", 132, 90, this.getWidth(), 30);
        topPopulationSizeLabel = new ValueLabel("0", 132, 120, this.getWidth(), 30);
        oldestOrganismLabel = new ValueLabel("0", 132, 150, this.getWidth(), 30);

        startPauseButton = new ActionButton("Start", 42, 220, 100, 25);
        resetButton = new ActionButton("Reset", 42, 255, 100, 25);
        
        this.add(infoLabel);
        this.add(generationInfoLabel);
        this.add(generationLabel);
        this.add(populationSizeInfoLabel);
        this.add(populationSizeLabel);
        this.add(topPopulationSizeInfoLabel);
        this.add(topPopulationSizeLabel);
        this.add(oldestOrganismInfoLabel);
        this.add(oldestOrganismLabel);
        this.add(startPauseButton);
        this.add(resetButton);
    }

    public void setGeneration(int generation) {
        generationLabel.setText(Integer.toString(generation));
    }

    public void setPopulationSize(int populationSize) {
        populationSizeLabel.setText(Integer.toString(populationSize));
    }

    public void setOldestOrganism(int oldestOrganism) {
        oldestOrganismLabel.setText(Integer.toString(oldestOrganism));
    }

    public void setTopPopulationSize(int topPop) {
        topPopulationSizeLabel.setText(Integer.toString(topPop));
    }

    //------------------ ADDITIONAL CLASSES --------------------//
    class InfoLabel extends JLabel {

        public InfoLabel(String title, int x, int y, int width, int height) {
            initUI(title, x, y, width, height);
        }

        private void initUI(String title, int x, int y, int width, int height) {
            this.setText(title);
            this.setBounds(x, y, width, height);
            this.setForeground(new Color(210, 210, 210));
            this.setFont(new Font("Calibri", Font.PLAIN, 18));
        }
    }

    class ValueLabel extends JLabel {

        public ValueLabel(String title, int x, int y, int width, int height) {
            initUI(title, x, y, width, height);
        }

        private void initUI(String title, int x, int y, int width, int height) {
            this.setText(title);
            this.setBounds(x, y, width, height);
            this.setFont(new Font("Calibri", Font.BOLD, 18));
            this.setForeground(new Color(60, 120, 250));
        }
    }

    class HeaderLabel extends JLabel {

        public HeaderLabel(String title, int x, int y, int width, int height) {
            initUI(title, x, y, width, height);
        }

        private void initUI(String title, int x, int y, int width, int height) {
            this.setHorizontalAlignment(SwingUtilities.CENTER);
            this.setText(title);
            this.setOpaque(true);
            this.setBackground(new Color(230, 230, 230));
            this.setBounds(x, y, width, height);
            this.setFont(new Font("Calibri", Font.PLAIN, 20));
            this.setForeground(new Color(0, 0, 0));
        }
    }

    class ActionButton extends JButton {

        private final Color BG_COLOR = new Color(55, 55, 55);
        private final Color HOVER_COLOR = new Color(50, 90, 170);
        public ActionButton(String title, int x, int y, int width, int height) {
            initUI(title, x, y, width, height);
        }

        private void initUI(final String title, int x, int y, int width, int height) {
            this.setText(title);
            this.setBounds(x, y, width, height);
            this.setBackground(BG_COLOR);
            this.setForeground(new Color(230, 230, 230));
            this.setFont(new Font("Calibri", Font.PLAIN, 17));
            this.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
            this.setFocusPainted(false);

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (title == "Start") {
                        if (gWindow.isGameRunning()) {
                            startPauseButton.setText("Start");
                            gWindow.pauseGame();
                        } else {
                            startPauseButton.setText("Pause");
                            gWindow.startGame();
                        }
                    } else {
                        gWindow.resetGame();
                        startPauseButton.setText("Start");
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(HOVER_COLOR);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(BG_COLOR);
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
    }
}
