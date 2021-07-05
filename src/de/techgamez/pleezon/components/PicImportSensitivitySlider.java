package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameClock;
import de.techgamez.pleezon.manager.PicImportManager;

import javax.swing.*;

public class PicImportSensitivitySlider extends JSlider{
    public PicImportSensitivitySlider(int x, int y, int width, int height, JFrame frame){
        super(0,100,10000,2000);
        this.setBounds(x,y,width,height);
        super.setPaintTicks(true);
        super.setMinorTickSpacing(250);
        super.setMajorTickSpacing(1000);
        this.addChangeListener((l)->{
            PicImportManager.sensitivity = this.getValue()/100000D;
            PicImportSensitivitySliderLabel.INSTANCE.setText("Import Sensitivity: " + PicImportManager.sensitivity);
        });
        frame.add(this);
    }
}
