# Game Of Life
My implementation of popular <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Conway's Game of Life</a>

This is just a for fun simple project, but if you want to improve it. Go ahead! :)

If You are here, please dont laugh loud enough to let me hear :D

## Documentation

### GameWindow
Class creating new window and manage communication between GamePanel and NavigationPanel.

```java
 public GameWindow(int width, int height);
```
Creates new JFrame and call **initUI()** method.

```java
 private initUI(int width, int height);
```
Set some basic values to our window, and creates *windowPane*, *navigationPanel* and *gamePanel*.

```java
 public nextGeneration(int generation, int population, int oldestOrganism, int topPopulation);
```
Recieve information about global board status, and sends information to *navigationPanel*.

### GamePanel
Class controlling game process.

```java
  private void prepareGrid();
```
Creates new 2-dimmensional table, containing information about our *Organism**s.

```java
private void clearGrid();
```
Change status, of all of our **Organism**s to *DEAD*

```java
 public void start();
```
Creates new thread, and iterate through generations. Main "game engine" method.

```java
 public void drawGrid();
```
Send's information to our organisms if their state has changed *DEAD-ALIVE | ALIVE-DEAD*, and render them again.

```java
private int countAliveNeighbours(int i, int j);
```
Check how many neighbours has our organism in (i,j) position.

### NavigationPanel
Class controlling game navigation bar, display information about current game status.

### Organism
Class representing our organisms.

```java
 public void kill();
```
Set that organism's status to *DEAD*.

```java
 public void changeState();
```
Change organism's status to the oposite one.

```java
 public void nextGeneration();
```
Called when gamePanel, iterate to next generation. Change organism's age, and color becouse of that change.

### Version
1.0

![screen 1](http://i68.tinypic.com/n6apso.jpg)
![screen 2](http://i63.tinypic.com/w8l1u9.png)
![screen 3](http://i64.tinypic.com/9rtyx0.png)


### To Do
 - [ ] Changable next generation speed
 - [ ] Changable brush size
 - [ ] Scale View
 - [ ] Move through map
