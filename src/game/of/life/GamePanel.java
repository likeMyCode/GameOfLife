package game.of.life;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Patryk
 */
public class GamePanel extends JPanel {

    private final static int GRID_SIZE = 15;
    private final static int MAX_GENERATION = Integer.MAX_VALUE;
    private final static int GENERATION_SPEED = 160;

    //-----------------------------------------------------------
    private int width, height;
    private int population, topPopulation, oldestOrganism;
    private boolean isRunning, pause, reset;
    private Organism[][] organismGrid;
    private GameWindow gWindow;

    public GamePanel(int x, int y, int width, int height, GameWindow gWindow) {
        super();
        this.gWindow = gWindow;
        this.width = width;
        this.height = height;

        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(240, 240, 240));

        prepareGrid();
    }

    private void prepareGrid() {
        organismGrid = new Organism[width / GRID_SIZE][height / GRID_SIZE];
        for (int i = 0; i < width / GRID_SIZE; i++) {
            for (int j = 0; j < height / GRID_SIZE; j++) {
                organismGrid[i][j] = new Organism(i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE);
                this.add(organismGrid[i][j]);
            }
        }
    }

    private void clearGrid() {
        population = 0;
        topPopulation = 0;
        oldestOrganism = 0;

        for (Organism[] organismList : organismGrid) {
            for (Organism organism : organismList) {
                organism.kill();
            }
        }
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                pause = false;
                reset = false;

                for (int generation = 1; generation < MAX_GENERATION; generation++) {
                    population = 0;
                    drawGrid();
                    gWindow.nextGeneration(generation, population, oldestOrganism, topPopulation);

                    try {
                        Thread.sleep(GENERATION_SPEED);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (reset) {
                        isRunning = false;
                        clearGrid();
                        gWindow.nextGeneration(0, population, oldestOrganism, topPopulation);
                        drawGrid();
                        return;
                    } else if (pause) {
                        isRunning = false;
                        return;
                    }
                }
            }
        }).start();
    }

    private void drawGrid() {
        int[][] newGrid = new int[width / GRID_SIZE][height / GRID_SIZE];

        for (int i = 0; i < width / GRID_SIZE; i++) {
            for (int j = 0; j < height / GRID_SIZE; j++) {
                int aliveNeighbours = countAliveNeighbours(i, j);

                if (aliveNeighbours == 3 && !organismGrid[i][j].isAlive()) {
                    newGrid[i][j] = 1;
                } else if (organismGrid[i][j].isAlive() && (aliveNeighbours == 3 || aliveNeighbours == 2)) {
                    newGrid[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < width / GRID_SIZE; i++) {
            for (int j = 0; j < height / GRID_SIZE; j++) {
                if ((organismGrid[i][j].isAlive() && newGrid[i][j] != 1) || (!organismGrid[i][j].isAlive() && newGrid[i][j] == 1)) {
                    organismGrid[i][j].changeState();
                }

                organismGrid[i][j].nextGeneration();

                if (organismGrid[i][j].isAlive()) {
                    population++;
                    if (organismGrid[i][j].getAge() > oldestOrganism) {
                        oldestOrganism = organismGrid[i][j].getAge();
                    }
                }
            }
        }

        if (population > topPopulation) {
            topPopulation = population;
        }

    }

    private int countAliveNeighbours(int i, int j) {
        int neighbours = 0;

        if (i - 1 >= 0) {
            if (organismGrid[i - 1][j].isAlive()) {
                neighbours++;
            }

            if (j - 1 >= 0) {
                if (organismGrid[i - 1][j - 1].isAlive()) {
                    neighbours++;
                }
            }

            if (j + 1 < height / GRID_SIZE) {
                if (organismGrid[i - 1][j + 1].isAlive()) {
                    neighbours++;
                }
            }
        }

        if (i + 1 < width / GRID_SIZE) {
            if (organismGrid[i + 1][j].isAlive()) {
                neighbours++;
            }

            if (j - 1 >= 0) {
                if (organismGrid[i + 1][j - 1].isAlive()) {
                    neighbours++;
                }
            }

            if (j + 1 < height / GRID_SIZE) {
                if (organismGrid[i + 1][j + 1].isAlive()) {
                    neighbours++;
                }
            }
        }

        if (j - 1 >= 0) {
            if (organismGrid[i][j - 1].isAlive()) {
                neighbours++;
            }
        }

        if (j + 1 < height / GRID_SIZE) {
            if (organismGrid[i][j + 1].isAlive()) {
                neighbours++;
            }
        }

        return neighbours;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void pause() {
        this.pause = true;
    }

    public void reset() {
        this.reset = true;
    }
}
