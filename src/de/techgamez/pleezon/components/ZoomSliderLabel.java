package de.techgamez.pleezon.components;

import javax.swing.*;

public class ZoomSliderLabel extends JLabel{
    public static ZoomSliderLabel INSTANCE;
    public ZoomSliderLabel(int x, int y, int width, int height, JFrame frame){
        INSTANCE = this;
        this.setText("Zoom: 1x");
        INSTANCE = this;
        super.setBounds(x,y,width,height);
        frame.add(this);
    }
}
