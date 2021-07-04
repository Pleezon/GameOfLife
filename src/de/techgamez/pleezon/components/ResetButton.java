package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;

public class ResetButton extends JButton {
    public ResetButton(int x, int y, int width, int height, JFrame frame){
        super("RESET");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            CellManager.cells = new boolean[CellManager.cells.length][CellManager.cells.length];
            GameOfLife.gameField.setOffsetY(0);
            GameOfLife.gameField.setOffsetX(0);
            GameOfLife.gameField.repaint();
        });
        frame.add(this);
    }
}
