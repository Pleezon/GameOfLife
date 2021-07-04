package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;
import java.util.Random;

public class DoGridButton extends JButton {
    public DoGridButton(int x, int y, int width, int height, JFrame frame){
        super("GRID");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            GameOfLife.gameField.doMesh = !GameOfLife.gameField.doMesh;
            GameOfLife.gameField.repaint();
        });
        frame.add(this);
    }
}
