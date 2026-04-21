package org.example.lab8;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawingPanel extends JPanel {
    private Cell[][] grid;
    private int randuri, coloane;
    private final int CELL_SIZE = 30;
    private final int MARGIN = 20;

    public void initGrid(int randuri, int coloane) {
        this.randuri = randuri;
        this.coloane = coloane;
        this.grid = new Cell[randuri][coloane];

        for (int i = 0; i < randuri; i++) {
            for (int j = 0; j < coloane; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        setPreferredSize(new Dimension(coloane * CELL_SIZE + MARGIN * 2, randuri * CELL_SIZE + MARGIN * 2));
        revalidate();
        repaint();
    }

    public void createRandomMaze() {
        if (grid == null) return;
        Random rand = new Random();
        resetMaze();
        for (int i = 1; i < randuri-1; i++) {
            for (int j = 1; j < coloane-1; j++) {

                    int val = rand.nextInt(4);
                    if(val == 0){
                        grid[i][j].walls[val] = false;
                        grid[i-1][j].walls[(val+2)%4] = false;
                    }
                    else if(val == 1){

                        grid[i][j].walls[val] = false;
                        grid[i][j+1].walls[(val+2)%4] = false;
                    }
                    else if(val == 2){

                        grid[i][j].walls[val] = false;
                        grid[i+1][j].walls[(val+2)%4] = false;
                    }
                    else {

                        grid[i][j].walls[val] = false;
                        grid[i][j-1].walls[(val+2)%4] = false;
                    }
            }
        }
        repaint();
    }

    public void resetMaze() {
        if (grid != null) initGrid(randuri, coloane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.PINK);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (grid == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        for (int i = 0; i < randuri; i++) {
            for (int j = 0; j < coloane; j++) {
                int x = MARGIN + j * CELL_SIZE;
                int y = MARGIN + i * CELL_SIZE;

                g2.setColor(Color.WHITE);
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                Cell cell = grid[i][j];

                if (cell.walls[0]) g2.drawLine(x, y, x + CELL_SIZE, y); // Sus
                if (cell.walls[1]) g2.drawLine(x + CELL_SIZE, y, x + CELL_SIZE, y + CELL_SIZE); // Dreapta
                if (cell.walls[2]) g2.drawLine(x, y + CELL_SIZE, x + CELL_SIZE, y + CELL_SIZE); // Jos
                if (cell.walls[3]) g2.drawLine(x, y, x, y + CELL_SIZE); // Stânga
            }
        }
    }
}