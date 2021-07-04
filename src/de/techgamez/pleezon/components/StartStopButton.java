package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;

import javax.swing.*;

public class StartStopButton extends JButton {
    public StartStopButton(int x, int y, int width, int height, JFrame frame){
        super("START");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            GameOfLife.isRunning = !GameOfLife.isRunning;
            if(GameOfLife.isRunning){
                super.setText("STOP");
            }else{
                super.setText("START");
            }
        });
        frame.add(this);

    }
}
