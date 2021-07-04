package de.techgamez.pleezon.components;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.manager.CellManager;

import javax.swing.*;
import java.util.Random;

public class DoMirrorButton extends JButton {
    public DoMirrorButton(int x, int y, int width, int height, JFrame frame){
        super("MIRROR");
        super.setBounds(x,y,width,height);
        this.addActionListener((a)->{
            CellManager.mirrorMode = !CellManager.mirrorMode;
            GameOfLife.gameField.repaint();
        });
        frame.add(this);
    }
}
