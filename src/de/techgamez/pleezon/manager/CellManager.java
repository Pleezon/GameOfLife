package de.techgamez.pleezon.manager;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class CellManager {
    public static boolean mirrorMode = false;
    public static boolean[][] cells = new boolean[1000][1000];

    public static void next_gen() {
        ArrayList<Action> actions = new ArrayList<>();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Action a = checkCell(x,y);
                if(a!=null){
                    actions.add(a);
                }
            }
        }
        actions.forEach(Action::execute);
    }

    public static Action checkCell(int x, int y){
        int neighbors = getNeighborAmount(x, y);
        if (neighbors == 3 && !cells[x][y]) {
            return new Action(true,x,y);
        } else if (neighbors < 2 && cells[x][y]) {
            return new Action(false,x,y);
        } else if ((neighbors == 2 || neighbors == 3) && cells[x][y]) {
            return new Action(true,x,y);
        }else if(neighbors>3 && cells[x][y]){
            return new Action(false,x,y);
        }
        return null;
    }


    public static class Action{
        public int x;
        public int y;
        public boolean alive;
        public Action(boolean alive, int x, int y){
            this.alive = alive;
            this.x = x;
            this.y = y;
        }
        public void execute(){
            cells[x][y]=alive;
        }
    }

    public static int getNeighborAmount(int x, int y) {
        int count = 0;
        for (int xx = x - 1; xx <= x + 1; xx++) {
            for (int yy = y - 1; yy <= y + 1; yy++) {
                if(!(xx==x & yy==y)){
                    int xxx;
                    int yyy;
                    if(mirrorMode){
                        yyy = yy%CellManager.cells.length;
                        xxx = xx%CellManager.cells.length;
                        if(yyy<0){
                            yyy+=CellManager.cells.length;
                        }
                        if(xxx<0){
                            xxx+=CellManager.cells.length;
                        }
                    }else {
                        xxx = xx;
                        yyy = yy;
                    }

                    try{
                        if (cells[xxx][yyy]) {
                            count++;
                        }
                    }catch (Exception ignored){}
                }
            }
        }
        return count;
    }

}
