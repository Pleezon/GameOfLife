package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.KeyManager;

import javax.swing.*;

public class SpeedSlider extends JSlider {
    public SpeedSlider(int x, int y, int width, int height, JFrame frame){
        super(0,1,15,5);
        this.setBounds(x,y,width,height);
        super.setPaintTicks(true);
        super.setMinorTickSpacing(1);
        super.setMajorTickSpacing(5);
        this.addChangeListener((l)->{
            KeyManager.setSpeed(this.getValue());
            SpeedSliderLabel.INSTANCE.setText("Move-Speed: "+this.getValue());
        });
        frame.add(this);
    }
}
