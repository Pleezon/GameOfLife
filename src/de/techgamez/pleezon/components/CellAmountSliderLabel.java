package de.techgamez.pleezon.components;

import javax.swing.*;

public class CellAmountSliderLabel extends JLabel{
    public static CellAmountSliderLabel INSTANCE;
    public CellAmountSliderLabel(int x, int y, int width, int height, JFrame frame){
        this.setText("Cells: "+3000);
        INSTANCE = this;
        super.setBounds(x,y,width,height);
        frame.add(this);
    }
}
