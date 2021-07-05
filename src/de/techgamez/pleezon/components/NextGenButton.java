package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;
import java.util.Random;

public class NextGenButton extends JButton {
    public NextGenButton(int x, int y, int width, int height, JFrame frame){
        super("GEN+1");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            CellManager.next_gen();
            GameOfLife.gameField.repaint();
        });
        frame.add(this);
    }
}
