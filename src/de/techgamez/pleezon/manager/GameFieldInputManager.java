package de.techgamez.pleezon.manager;

import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.components.GameField;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameFieldInputManager implements MouseMotionListener, MouseListener {

    public enum Button{
        NONE,LEFT,RIGHT,BOTH;
    }

    GameField field;
    public GameFieldInputManager(GameField field){
        this.field = field;
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Button button = Button.NONE;
        if(mouseEvent.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
            button = Button.LEFT;
        }else if(mouseEvent.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK){
            button = Button.RIGHT;
        }else if(mouseEvent.getModifiersEx() == (InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK)){
            button = Button.BOTH;
        }
        int x = (int) ((mouseEvent.getX() + this.field.getOffsetX()) / this.field.getCellSize());
        int y = (int) ((mouseEvent.getY() + this.field.getOffsetY()) / this.field.getCellSize());
        if(CellManager.mirrorMode){
            double x1 =  ((mouseEvent.getX() + this.field.getOffsetX()) / (float) this.field.getCellSize());
            double y1 =  ((mouseEvent.getY() + this.field.getOffsetY()) / (float) this.field.getCellSize());
            y1 = y1%CellManager.cells.length;
            x1 = x1%CellManager.cells.length;
            if(y<0){
                y1+=CellManager.cells.length;
            }
            if(x<0){
                x1+=CellManager.cells.length;
            }
            x = (int) Math.round(x1-0.5);
            y = (int) Math.round(y1-0.5);
        }
        Random r = new Random();
        if(x<CellManager.cells.length && y<CellManager.cells.length && x>=0 && y>=0){
            if(button == Button.LEFT){
                CellManager.cells[x][y] = true;
            }else if(button == Button.RIGHT){
                CellManager.cells[x][y] = false;
            }else if(button == Button.BOTH){
                CellManager.cells[x][y] = r.nextBoolean();
            }
            GameOfLife.gameField.repaint();
        }else{
            System.out.println("GOT DRAG OUTSIDE!");
        }

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = (int) ((mouseEvent.getX() + this.field.getOffsetX()) / this.field.getCellSize());
        int y = (int) ((mouseEvent.getY() + this.field.getOffsetY()) / this.field.getCellSize());
        if(CellManager.mirrorMode){
            double x1 =  ((mouseEvent.getX() + this.field.getOffsetX()) / (float) this.field.getCellSize());
            double y1 =  ((mouseEvent.getY() + this.field.getOffsetY()) / (float) this.field.getCellSize());
            y1 = y1%CellManager.cells.length;
            x1 = x1%CellManager.cells.length;
            if(y<0){
                y1+=CellManager.cells.length;
            }
            if(x<0){
                x1+=CellManager.cells.length;
            }
            x = (int) Math.round(x1-0.5);
            y = (int) Math.round(y1-0.5);
        }

        if(x<CellManager.cells.length && y<CellManager.cells.length && x>=0 && y>=0){
            if(mouseEvent.getButton() == 1){
                CellManager.cells[x][y] = true;
            }else if(mouseEvent.getButton() == 3){
                CellManager.cells[x][y] = false;
            }
            GameOfLife.gameField.repaint();
            System.out.println("PAINTED: X: " + x + " Y: " + y);
        }else{
            System.out.println("GOT CLICK OUTSIDE!");
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
