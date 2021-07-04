package de.techgamez.pleezon.manager;

import de.techgamez.pleezon.GameOfLife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeyManager implements KeyListener {
    private static int speed = 5;
    private static final boolean[] pressed = new boolean[255];
    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);;

    public static void setSpeed(int value){
        speed = value;
        schedule();
    }
    private static ScheduledFuture<?> futureTask;
    public KeyManager(){
        schedule();
    }
    private static void schedule(){
        if(!(futureTask ==null)){
            futureTask.cancel(true);
        }
        futureTask = scheduledExecutorService.scheduleAtFixedRate(()->{
            boolean changed = false;
            if(pressed['w']){
                GameOfLife.gameField.setOffsetY(GameOfLife.gameField.getOffsetY()-1);
                changed = true;
            }
            if(pressed['a']){
                GameOfLife.gameField.setOffsetX(GameOfLife.gameField.getOffsetX()-1);
                changed = true;
            }
            if(pressed['s']){
                GameOfLife.gameField.setOffsetY(GameOfLife.gameField.getOffsetY()+1);
                changed = true;
            }
            if(pressed['d']){
                GameOfLife.gameField.setOffsetX(GameOfLife.gameField.getOffsetX()+1);

                changed = true;
            }
            if(changed) GameOfLife.gameField.repaint();
            //System.out.println("OFFSETS: " + GameOfLife.gameField.offsetX + " " + GameOfLife.gameField.offsetY);
        }, 0, (int)50D/speed, TimeUnit.MILLISECONDS);
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed[keyEvent.getKeyChar()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        pressed[keyEvent.getKeyChar()] = false;
    }
}
