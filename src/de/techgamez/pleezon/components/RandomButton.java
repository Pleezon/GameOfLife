package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;
import java.util.Random;

public class RandomButton extends JButton{
    public RandomButton(int x, int y, int width, int height, JFrame frame){
        super("RANDOM");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            Random r = new Random();
            for(int x1=0; x1<CellManager.cells.length; x1++){
                for(int y1=0; y1<CellManager.cells[x1].length; y1++){
                    CellManager.cells[x1][y1 ] = r.nextBoolean();
                }
            }
            GameOfLife.gameField.repaint();
        });
        frame.add(this);
    }
}
