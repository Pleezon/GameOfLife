package de.techgamez.pleezon.components;

import javax.swing.*;

public class SpeedSliderLabel extends JLabel {
    public static SpeedSliderLabel INSTANCE;
    public SpeedSliderLabel(int x, int y, int width, int height, JFrame frame){
        INSTANCE = this;
        this.setText("Move-Speed: 5");
        INSTANCE = this;
        super.setBounds(x,y,width,height);
        frame.add(this);
    }
}
