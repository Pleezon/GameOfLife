package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameClock;

import javax.swing.*;

public class RefreshTimeSlider extends JSlider {
    public RefreshTimeSlider(int x, int y, int width, int height, JFrame frame){
        super(0,1,5000,250);
        this.setBounds(x,y,width,height);
        super.setPaintTicks(true);
        super.setMinorTickSpacing(100);
        super.setMajorTickSpacing(1000);
        this.addChangeListener((l)->{
            GameClock.changeReadInterval(this.getValue());
            RefreshTimeSliderLabel.INSTANCE.setText("Refresh: " + this.getValue() + "ms");
        });
        frame.add(this);
    }

}
