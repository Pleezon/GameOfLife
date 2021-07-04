package de.techgamez.pleezon.components;

import javax.swing.*;

public class RefreshTimeSliderLabel extends JLabel{
    public static RefreshTimeSliderLabel INSTANCE;
    public RefreshTimeSliderLabel(int x, int y, int width, int height, JFrame frame){
        this.setText("Refresh: " + 250 + "ms");
        INSTANCE = this;
        super.setBounds(x,y,width,height);
        frame.add(this);
    }
}
