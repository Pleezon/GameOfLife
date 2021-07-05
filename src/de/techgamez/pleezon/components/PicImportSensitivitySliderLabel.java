package de.techgamez.pleezon.components;

import javax.swing.*;

public class PicImportSensitivitySliderLabel extends JLabel {
    public static PicImportSensitivitySliderLabel INSTANCE;
    public PicImportSensitivitySliderLabel(int x, int y, int width, int height, JFrame frame){
        this.setText("Import Sensitivity: " + 0.02);
        INSTANCE = this;
        super.setBounds(x,y,width,height);
        frame.add(this);
    }
}
