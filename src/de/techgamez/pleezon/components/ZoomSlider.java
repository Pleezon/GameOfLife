package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameClock;
import de.techgamez.pleezon.GameOfLife;

import javax.swing.*;

public class ZoomSlider extends JSlider{
    public ZoomSlider(int x, int y, int width, int height, JFrame frame){
        super(0,1,25,16);
        this.setBounds(x,y,width,height);
        super.setPaintTicks(true);
        super.setMinorTickSpacing(1);
        super.setMajorTickSpacing(5);
        this.addChangeListener((l)->{
            GameOfLife.gameField.setCellSize(this.getValue());
            GameOfLife.gameField.repaint();
            ZoomSliderLabel.INSTANCE.setText("Zoom: " + (Math.round((GameOfLife.gameField.getCellSize()/16.0)*1000)/1000.0) + "x");
        });
        frame.add(this);
    }
}
