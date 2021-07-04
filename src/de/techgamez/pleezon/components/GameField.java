package de.techgamez.pleezon.components;

import de.techgamez.pleezon.manager.CellManager;
import de.techgamez.pleezon.manager.GameFieldInputManager;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private int offsetY = 0;
    private int offsetX = 0;
    private int cellSize;
    public boolean doMesh = true;

    public void setCellSize(int size) {
        double fac = (double) size / this.cellSize;
        this.offsetX *= fac;
        this.offsetY *= fac;
        this.cellSize = size;
    }

    public void setOffsetX(int value) {
        if ((float) value > Integer.MAX_VALUE) {
            return;
        }
        if (CellManager.mirrorMode) {
            this.offsetX += value;
            this.offsetX %= CellManager.cells.length*cellSize+this.getWidth();
            if(this.offsetX<0){
                this.offsetX+=CellManager.cells.length*cellSize+this.getWidth();
            }
        }

        this.offsetX = value;
    }

    public void setOffsetY(int value) {
        if ((float) value > Integer.MAX_VALUE) {
            return;
        }
        if (CellManager.mirrorMode) {
            this.offsetY += value;
            this.offsetY %= CellManager.cells.length*cellSize;
            if(this.offsetY<0){
                this.offsetY+=CellManager.cells.length*cellSize;
            }
        }

        this.offsetY = value;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public long getCellSize() {
        return this.cellSize;
    }

    public GameField(int cellSize, int sizeX, int sizeY, JFrame frame) {
        this.cellSize = cellSize;
        this.setSize(sizeX, sizeY);
        GameFieldInputManager manager = new GameFieldInputManager(this);
        this.addMouseMotionListener(manager);
        this.addMouseListener(manager);
        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int amoCellsX = this.getWidth() / cellSize + 2;
        int amoCellsY = this.getHeight() / cellSize + 2;
        for (int x = -2; x < amoCellsX; x++) {
            for (int y = -2; y < amoCellsY; y++) {

                int x1 = (x * cellSize) - (offsetX % cellSize);
                int y1 = (y * cellSize) - (offsetY % cellSize);
                int xx = (offsetX / cellSize) + x;
                int yy = (offsetY / cellSize) + y;
                if (CellManager.mirrorMode) {
                    xx = xx % (CellManager.cells.length);
                    yy = yy % (CellManager.cells.length);
                    if (yy < 0) {
                        yy += CellManager.cells.length;
                    }
                    if (xx < 0) {
                        xx += CellManager.cells.length;
                    }
                }
                if (xx >= CellManager.cells.length || yy >= CellManager.cells.length || xx < 0 || yy < 0) {
                    drawAt(x1, y1, CellManager.mirrorMode ? Color.ORANGE : Color.RED, g);
                } else if (CellManager.cells[xx][yy]) {
                    drawAt(x1, y1, Color.BLACK, g);
                } else {
                    drawAt(x1, y1, Color.WHITE, g);
                }
            }

        }
    }

    private void drawAt(int x, int y, Color color, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
        if (doMesh) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(x, y, cellSize, cellSize);
        }
    }

}
