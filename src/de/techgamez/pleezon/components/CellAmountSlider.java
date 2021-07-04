package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameClock;
import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;

public class CellAmountSlider extends JSlider {
    public CellAmountSlider(int x, int y, int width, int height, JFrame frame){
        super(0,1, 5000,3000);
        this.setBounds(x,y,width,height);
        super.setPaintTicks(true);
        super.setMinorTickSpacing(500);
        super.setMajorTickSpacing(5000);
        this.addChangeListener((l)->{
            CellManager.cells = new boolean[this.getValue()][this.getValue()];
            GameOfLife.gameField.repaint();
            CellAmountSliderLabel.INSTANCE.setText("Cells: "+this.getValue());
        });
        frame.add(this);
    }
}
