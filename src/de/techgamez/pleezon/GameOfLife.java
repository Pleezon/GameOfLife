package de.techgamez.pleezon;

import de.techgamez.pleezon.components.*;
import de.techgamez.pleezon.manager.CellManager;
import de.techgamez.pleezon.manager.KeyManager;
import de.techgamez.pleezon.manager.PicImportManager;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

public class GameOfLife {
    public static GameField gameField;
    public static boolean isRunning = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Of Life");
        frame.setSize(1450,1024);
        frame.setLayout(null);
        gameField = new GameField(16,1024,1024,frame);
        new ResetButton(1024,0,100,30,frame);
        new StartStopButton(1024,30,100,30,frame);
        new RefreshTimeSliderLabel(1125,0,300,15,frame);
        new RefreshTimeSlider(1124,15,300,60,frame);
        new ZoomSliderLabel(1125,75,300,15,frame);
        new ZoomSlider(1124,90,300,60,frame);
        new SpeedSliderLabel(1125,150,300,15,frame);
        new SpeedSlider(1124,165,300,60,frame);
        new CellAmountSliderLabel(1125,225,300,15,frame);
        new CellAmountSlider(1124,240,300,60,frame);
        new PicImportSensitivitySliderLabel(1125,300,300,15,frame);
        new PicImportSensitivitySlider(1124,315,300,60,frame);
        new RandomButton(1024,60,100,30,frame);
        new DoGridButton(1024,90,100,30,frame);
        new DoMirrorButton(1024,120,100,30,frame);
        new NextGenButton(1024,150,100,30,frame);

        frame.setVisible(true);
        frame.addKeyListener(new KeyManager());
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setAutoRequestFocus(true);
        new Thread(()->{
            while(true){
                frame.requestFocus();
            }
        }).start();
        frame.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {

                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if(droppedFiles.get(0).getName().endsWith(".png") || droppedFiles.get(0).getName().endsWith(".jpg")){
                        PicImportManager.processToBoard(droppedFiles.get(0));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        GameClock.start();
    }
    public static void tick(){
        if(isRunning){
            CellManager.next_gen();
            gameField.repaint();
        }
    }
}
