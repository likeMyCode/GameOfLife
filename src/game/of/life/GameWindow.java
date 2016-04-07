package game.of.life;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Patryk
 */
public class GameWindow {

    private final static int NAVIGATION_WIDTH = 180;
    private final static int NAVIGATION_HEIGHT = 300;

    //----------------------------------------------------
    private JFrame window;
    private GamePanel gamePanel;
    private NavigationPanel navigationPanel;
    private int width, height;

    public GameWindow(int width, int height) {
        this.width = width;
        this.height = height;
        initUI();
    }

    private void initUI() {
        window = new JFrame();
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        JLayeredPane windowPane = new JLayeredPane();
        windowPane.setBounds(0, 0, width, height);
        gamePanel = new GamePanel(0, 0, width, height, this);
        navigationPanel = new NavigationPanel(20, 0, NAVIGATION_WIDTH, NAVIGATION_HEIGHT, this);

        window.add(windowPane);
        windowPane.add(navigationPanel, new Integer(2));
        windowPane.add(gamePanel, new Integer(1));

        window.show();
        window.repaint();
    }

    public void nextGeneration(int generation, int population, int oldestOrganism, int topPopulation) {
        navigationPanel.setGeneration(generation);
        navigationPanel.setPopulationSize(population);
        navigationPanel.setOldestOrganism(oldestOrganism);
        navigationPanel.setTopPopulationSize(topPopulation);
    }

    public void startGame() {
        gamePanel.start();
    }

    public void pauseGame() {
        gamePanel.pause();
    }

    public void resetGame() {
        gamePanel.reset();
    }

    public boolean isGameRunning() {
        return gamePanel.isRunning();
    }
}
